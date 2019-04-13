#include "include/Sample1.h"
#include <ctype.h>
#include <string.h>

// Mutate array to uppercase
void uppercase(char* str) {
    size_t n = strlen(str);
    for (size_t i = 0; i < n; i++) {
        str[i] = toupper(str[i]);
    }
}

JNIEXPORT jint JNICALL Java_Sample1_intMethod
(JNIEnv* env, jobject obj, jint num) {
    return num * num;
}


JNIEXPORT jboolean JNICALL Java_Sample1_booleanMethod
(JNIEnv* env, jobject obj, jboolean boolean) {
    return !boolean;
}

JNIEXPORT jstring JNICALL Java_Sample1_stringMethod
(JNIEnv* env, jobject obj, jstring string) {
    return (*env)->NewStringUTF(env,"Hello World");
}

JNIEXPORT jint JNICALL Java_Sample1_intArrayMethod
(JNIEnv* env, jobject obj, jintArray array) {
    return sizeof(array);
}
