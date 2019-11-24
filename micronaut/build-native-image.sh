./gradlew build fatJar
native-image -J-Xmx5G --no-server -cp ./build/libs/micronaut-all.jar
