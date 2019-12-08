#!/bin/bash

set -e

./gradlew clean build
mkdir -p build/graal
native-image --no-server \
  -H:Path=./build/graal \
  -H:Name=graalvm-dns-resolution \
  -cp ./build/libs/graalvm-dns-resolution.jar

echo "> works on my machine"
./build/graal/graalvm-dns-resolution

echo "> but not at alpine image"
docker run --rm -it --name tmp -v $PWD/build/graal/graalvm-dns-resolution:/graalvm-dns-resolution alpine:latest /graalvm-dns-resolution
