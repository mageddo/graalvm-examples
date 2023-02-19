Working jna with native image sample

```bash
$ ./gradlew build nativeCompile && ./build/native/nativeCompile/graalvm-jna

Aqui começa mais uma história de amor!
hostname=typer-pc - 0
```

Testing jar using Docker

```bash
$ ./gradlew clean build shadowJar
$ docker run --rm -v $PWD/build/libs/graalvm-jna-all.jar:/app.jar adoptopenjdk/openjdk8:x86_64-ubuntu-jre8u362-b09 java -jar /app.jar
```

Building and running using Docker

```bash
$ docker-compose build debian-9 --progress=plain
$ docker run --rm -v /var/run/docker.sock:/var/run/docker.sock docker.io/library/jna-debian-9
```

## References
* Credits to [amahfouz1][1]
* [JNA fails to load with GraalVM throwing JNA: Problems loading core IDs: java.lang.Object][2]

[1]: https://github.com/amahfouz1/jna-graalvm
[2]: https://github.com/oracle/graal/issues/2261
