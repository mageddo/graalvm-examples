#!/bin/bash

set -e

./gradlew clean build
mkdir -p build/graal
native-image --no-server \
  -H:Path=./build/graal \
  -H:Name=graalvm-ec-issue \
  -cp ./build/libs/graalvm-ec-issue.jar
./build/graal/graalvm-ec-issue
