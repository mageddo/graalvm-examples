Building a full automated graalvm binary using gradle

```bash
$ ./gradlew build nativeCompile &&\
  ./build/native/nativeCompile/graalvm-vanilla 
08:20:02.617 [main] INFO com.mageddo.Application - Hello World!!!
```

## Testing the native Image

```bash
./gradlew nativeTest
```

