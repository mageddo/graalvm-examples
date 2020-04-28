This is an example of how to use sqlite with graalvm 20.0.0 native image

### Using on standard JVM 

```
./gradlew -q run
It Works from SQLITE!!!
```


### Building and running from binary file

```
$ ./native-image.sh
> building jar
> compiling binary
...
> running binary
msg=It Works from SQLITE!!!, active=true
```
