#!/bin/bash

cd $(dirname $0)

rm -rf src/main/resources/lib/
mkdir -p src/main/resources/lib/include

echo "building hello lib"
gcc -I/usr/include -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -fPIC -shared -o src/main/resources/lib/Sample1.so src/main/c/Sample1.c
