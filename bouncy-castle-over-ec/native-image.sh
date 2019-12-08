#!/bin/bash

set -e

./gradlew clean build fatJar
mkdir -p build/graal
native-image --no-server \
  -H:Path=./build/graal \
  -H:Name=graalvm-bouncy-castle-over-ec \
  -cp ./build/libs/graalvm-bouncy-castle-over-ec-all.jar

./build/graal/graalvm-bouncy-castle-over-ec
