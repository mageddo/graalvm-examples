./gradlew build fatJar
native-image --no-server -cp ./build/libs/micronaut-all.jar
