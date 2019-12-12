#!/bin/bash

set -e

./gradlew clean build
mkdir -p build/graal
native-image --no-server \
  -H:Path=./build/graal \
  -H:Name=graalvm-ec-issue \
  --enable-all-security-services \
  -cp ./build/libs/graalvm-ec-issue.jar
./build/graal/graalvm-ec-issue -Djava.library.path=${JAVA_HOME}/lib/
