# jdbi-native-image

Use JDBI on native-image binaries, tested with `GraalVM CE 19.3.1 (Java 11)`.


# Running

Set GraalVM on JAVA_HOME, then run

```bash
$ ./gradlew nativeImage
$ ./build/native-image/jdbi

[Stock(symbol=PAGS, price=10.00)] 
```
