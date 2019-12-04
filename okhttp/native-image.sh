#!/bin/bash

set -e
./gradlew clean build fatJar
native-image --no-server -cp ./build/libs/graalvm-okhttp-all.jar
./build/graal/graalvm-okhttp -Djava.library.path=$JAVA_HOME/lib/
