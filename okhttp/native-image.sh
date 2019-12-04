#!/bin/bash

set -e
./gradlew clean build fatJar
native-image --no-server -cp ./build/libs/graalvm-okhttp-all.jar
