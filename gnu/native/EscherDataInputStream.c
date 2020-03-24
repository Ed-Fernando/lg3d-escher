#include <unistd.h>
#include <jni.h>
#include "gnu_x11_EscherDataInputStream.h"

#define SKIP_BUFFER_SIZE 64

#define MIN(a,b) (((a)<(b))?(a):(b))

extern void JNU_ThrowByName (JNIEnv *env, const char *excepName, const char *msg);

JNIEXPORT jlong JNICALL Java_gnu_x11_EscherDataInputStream_skip
  (JNIEnv *env, jobject obj, jint fd, jlong n)
{
    unsigned char localSkipBuffer[SKIP_BUFFER_SIZE];
    long remaining = n;
    int nr;
		
    if (n <= 0) {
	return 0;
    }

    while (remaining > 0) {
	/*fprintf(stderr, "EDIS read\n");*/
        int bytesToRead = (SKIP_BUFFER_SIZE, remaining);
	nr = read(fd, localSkipBuffer, bytesToRead);
	if (nr < 0) {
	    break;
	}
	remaining -= nr;

	/* For Debug 
	{ int i;
	  fprintf(stderr, "EscherDataInputStream_skip: Skipped these chars: \n");
	  for (i = 0; i < bytesToRead; i++) {
    	      unsigned char c = localSkipBuffer[i];
	      fprintf(stderr, "%d ('%c')\n", c, c);
	  }
	}
	*/
    }
	
    return n - remaining;
}

JNIEXPORT jint JNICALL Java_gnu_x11_EscherDataInputStream_available
  (JNIEnv *env, jobject obj, jint fd)
{
    /* Always returns zero on Solaris and Linux */
    return 0;
}

JNIEXPORT jint JNICALL Java_gnu_x11_EscherDataInputStream_readUnsignedByte
  (JNIEnv *env, jobject obj, jint fd)
{
    unsigned char b;

    if (read(fd, &b, 1) < 0) {
	JNU_ThrowByName(env, "java/io/IOException", "Error reading from X server");
	return -1;
    }

    /*fprintf(stderr, "EscherDataInputStream_readUnsignedByte: b = 0x%x ('%c')\n", b, b);*/

    return (jint) b;
}

JNIEXPORT jint JNICALL Java_gnu_x11_EscherDataInputStream_readUnsignedShort
  (JNIEnv *env, jobject obj, jint fd)
{
    unsigned short s;

    if (read(fd, &s, 2) < 0) {
	JNU_ThrowByName(env, "java/io/IOException", "Error reading from X server");
	return -1;
    }

    /*fprintf(stderr, "EscherDataInputStream_readUnsignedByte: s = 0x%x (%d)\n", s, s);*/

    return (jint) s;
}

JNIEXPORT jint JNICALL Java_gnu_x11_EscherDataInputStream_read
  (JNIEnv *env, jobject obj, jint fd)
{
    unsigned char b;

    if (read(fd, &b, 1) < 0) {
	JNU_ThrowByName(env, "java/io/IOException", "Error reading from X server");
	return -1;
    }

    /*fprintf(stderr, "EscherDataInputStream_read: b = 0x%x (%d)\n", b, b);*/

    return (jint) b;
}

JNIEXPORT void JNICALL Java_gnu_x11_EscherDataInputStream_readFully__I_3BII
  (JNIEnv *env, jobject obj, jint fd, jbyteArray b, jint off, jint len)
{
    int n;
    jbyte *pBytes, *pB;

    if (len < 0) {
	JNU_ThrowByName(env, "java/lang/IndexOutOfBoundsException", "Invalid buffer length");
	return;
    }

    // special case -- if 0 bytes are requested, just return
    if (len == 0) {
        return;
    }

    pBytes = (jbyte *) (*env)->GetPrimitiveArrayCritical(env, b, 0);
    pB = pBytes + off;
    while (len > 0) {
	int count = read(fd, pB, len);
	/*fprintf(stderr, "EDIS readfully, count = %d\n", count);*/
	if (count == 0) {
            JNU_ThrowByName(env, "java/io/EOFException", "No bytes to be read from X server");
	    (*env)->ReleasePrimitiveArrayCritical(env, b, pBytes, JNI_ABORT);
	    return;
	} else if (count < 0) {
            JNU_ThrowByName(env, "java/io/IOException", "Read error");
            (*env)->ReleasePrimitiveArrayCritical(env, b, pBytes, JNI_ABORT);
            return;
        }
	len -= count;
	pB += count;
    }

    /* For Debug
    { int i;
      fprintf(stderr, "EscherDataInputStream_readFully:\n");
      for (i = 0; i < len; i++) {
          unsigned char c = *(pBytes + off + i);
	  fprintf(stderr, "%d ('%c')\n", c, c);
      }
    }
    */

    (*env)->ReleasePrimitiveArrayCritical(env, b, pBytes, JNI_ABORT);
}
