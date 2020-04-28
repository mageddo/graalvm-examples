#!/bin/bash

set -e

echo "> building jar"

./gradlew clean shadow
mkdir -p build/graal

echo "> compiling binary"
native-image --no-server \
  -H:Path=./build/graal \
  -H:Name=sqlite \
  -cp ./build/libs/sqlite-all.jar

echo "> running binary"
./build/graal/sqlite
