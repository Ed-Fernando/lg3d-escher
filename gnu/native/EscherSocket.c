#include <stdio.h>
#include <unistd.h>
#include <jni.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/un.h>
#include <sys/socket.h>
#include <time.h>
#include "gnu_x11_EscherSocket.h"
#ifdef SOLARIS
#include <iso/string_iso.h>
#endif /* SOLARIS */

/* in seconds */
#define X_CONNECTION_RETRY_DURATION 60  	/* One minute */

/* This is the same for both Solaris and Linux */
#define UNIX_PATH "/tmp/.X11-unix/X"

static int tryToConnectToXServer (int displayNum);

void JNU_ThrowByName (JNIEnv *env, const char *excepName, const char *msg) 
{
    jclass clazz = (*env)->FindClass(env, excepName);
    /* If clazz is NULL, an exception has already been thrown */
    if (clazz != NULL) {
	(*env)->ThrowNew(env, clazz, msg);
    }
    (*env)->DeleteLocalRef(env, clazz);
}

JNIEXPORT jint JNICALL Java_gnu_x11_EscherSocket_socketCreateAndConnect
  (JNIEnv *env, jobject obj, jint displayNum) 
{
    int fd;

    fd = tryToConnectToXServer(displayNum);
    if (fd == -1) {
	JNU_ThrowByName(env, "java/io/IOException", "Cannot connect to X server");
	return -1;
    }

    return fd;
}

JNIEXPORT void JNICALL Java_gnu_x11_EscherSocket_socketClose
  (JNIEnv *env, jobject obj, jint fd)
{
    close(fd);
}

/*
** Try several times to connect to the X server via an AF_UNIX
** socket. Returns the socket file descriptor on success. Returns
** -1 on failure.
*/

static int
tryToConnectToXServer (int displayNum)
{
    struct sockaddr_un	sockname;
    int			namelen;
    int                 fd;
    time_t              start, curTime;
    
    /* Build the socket name */
    sockname.sun_family = AF_UNIX;
    sprintf(sockname.sun_path, "%s%d", UNIX_PATH, displayNum);
    /* fprintf(stderr, "sun_path = %s\n", sockname.sun_path); */
    namelen = strlen (sockname.sun_path) + sizeof (sockname.sun_family);

    fprintf(stderr, "Escher attempting to connect to X server ...\n");

    start = curTime = time(NULL);
    while (curTime < start + X_CONNECTION_RETRY_DURATION) {
	    
	/* Create the socket */
	fd = socket(PF_UNIX, SOCK_STREAM, 0);
	if (fd < 0) {
    	    return -1;
	}

	/*  Try to open the connection */
	if (connect(fd, (struct sockaddr *) &sockname, namelen) < 0) {
	    /*
	     * If the error was ENOENT, the server may be starting up
	     * and we should try again.
	     *
	     * If the error was EINTR, the connect was interrupted and we
	     * should try again.
	     */
	    if (errno == ENOENT || errno == EINTR || errno == ECONNREFUSED) {
		/* Try to connect again */
		close(fd);
		usleep(10);
#ifdef SOLARIS
	    } else if (errno != 0) {
		/* Solaris Note: when 0 is returned this is okay */
#else
	    } else {
#endif /* SOLARIS */
		fprintf(stderr, "Attempt to connect failed, errno = %d\n", errno);
		return -1;
	    }
	} else {
	    /*fprintf(stderr, "X connection established on socket %d\n", fd);*/
	    fprintf(stderr, "Escher connected\n");
	    return fd;
	}

	curTime = time(NULL);
    }

    fprintf(stderr, "Attempt to connect to X server timed out after %d seconds\n", 
	    X_CONNECTION_RETRY_DURATION);

    return -1;
}
