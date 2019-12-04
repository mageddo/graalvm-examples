#!/bin/bash

set -e
./gradlew clean build fatJar
native-image --no-fallback --no-server -cp ./build/libs/graalvm-okhttp4-all.jar com.mageddo.graalvm.okhttp.Application
