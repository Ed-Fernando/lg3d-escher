#include <jni.h>
#include <unistd.h>
#include "gnu_x11_EscherOutputStream.h"

extern void JNU_ThrowByName (JNIEnv *env, const char *excepName, const char *msg);

JNIEXPORT void JNICALL Java_gnu_x11_EscherOutputStream_write
  (JNIEnv *env, jobject obj , jint fd, jbyteArray b, jint off, jint len)
{
    jbyte *pBytes;

    pBytes = (jbyte *) (*env)->GetPrimitiveArrayCritical(env, b, 0);

    /* For Debug
    { int i;
      fprintf(stderr, "EscherOutputStream_write:\n");
      for (i = 0; i < len; i++) {
          unsigned char c = *(pBytes + off + i);
	  fprintf(stderr, "%d ('%c')\n", c, c);
      }
    }
    */

    if (write(fd, pBytes + off, len) < 0) {
	JNU_ThrowByName(env, "java/io/IOException", "Error writing to X server");
	return;
    }

    (*env)->ReleasePrimitiveArrayCritical(env, b, pBytes, JNI_ABORT);
}

