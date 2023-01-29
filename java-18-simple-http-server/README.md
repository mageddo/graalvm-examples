Building a full automated graalvm binary using gradle

```bash
$ ./gradlew build nativeCompile &&\
  ./build/native/nativeCompile/java-18-simple-http-server
2023-01-29 12:37:30.505 [main           ] INF   com.mageddo.Application m=main l=23 starting.., port=8000

$ curl -w '\n' localhost:8000/some-path
[ {
  "name" : "Apple",
  "weight" : 150.0
}, {
  "name" : "Orange",
  "weight" : 245.3
} ]
```
