./gradlew clean build
native-image --no-fallback --no-server -cp ./build/libs/graalvm-okhttp4.jar com.mageddo.graalvm.okhttp.Application
