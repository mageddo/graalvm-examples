### Building binary and running

```bash
$ ./gradlew clean nativeImage
```

### Developing

```bash
echo $'version: \'3\'\nservices:\n  web:\n    image: nginx' > /tmp/stack.yml &&\
./gradlew run --debug-jvm --args "stack-deploy -u admin -p 12345678 -s reverse-proxy /tmp/stack.yml"
```
