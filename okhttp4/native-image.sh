#!/bin/bash

set -e

./gradlew clean build fatJar
mkdir -p build/graal
native-image --no-server \
  -H:Path=./build/graal \
  -H:Name=graalvm-okhttp4 \
  -cp ./build/libs/graalvm-okhttp4-all.jar
./build/graal/graalvm-okhttp4  -Djava.library.path=${JAVA_HOME}/lib/
