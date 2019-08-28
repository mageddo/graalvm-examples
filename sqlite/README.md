This is an example of how to use sqlite with graalvm native image

### Using on standard JVM 

```
./gradlew -q run
It Works from SQLITE!!!
```


### Building and running from binary file

```
$ ./gradlew clean build nativeImage && ./build/graal/sqlite
native-image available at build/graal/sqlite (25MB)
BUILD SUCCESSFUL in 2m 7s
11 actionable tasks: 11 executed
It Works from SQLITE!!!
```
