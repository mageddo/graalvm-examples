#!/bin/bash

set -e
./gradlew clean build fatJar
native-image --no-server -cp ./build/libs/graalvm-okhttp-all.jar
./com.mageddo.graalvm.okhttp.application -Djava.library.path=$JAVA_HOME/lib/
