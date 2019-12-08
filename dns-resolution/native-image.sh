#!/bin/bash

set -e

./gradlew clean build
mkdir -p build/graal
native-image --no-server \
  -H:Path=./build/graal \
  -H:Name=graalvm-dns-resolution \
  -cp ./build/libs/graalvm-dns-resolution.jar

./build/graal/graalvm-dns-resolution
