Working jna with native image sample

GraalVM 22.3 Java 17: Native Image compile and run 

```bash
$ ./gradlew build nativeCompile && ./build/native/nativeCompile/graalvm-jna

Aqui começa mais uma história de amor!
hostname=typer-pc - 0
```

Java 8: Jar building and running using Docker

```bash
$ docker-compose build jdk8 --progress=plain
$ docker run -it --rm -v /var/run/docker.sock:/var/run/docker.sock docker.io/library/jna-jdk8
```

GraalVM 22.3 Java 17: Native Image compile and run using Docker

```bash
$ docker-compose build debian-9 --progress=plain
$ docker run --rm -v /var/run/docker.sock:/var/run/docker.sock docker.io/library/jna-debian-9
```

## References
* Credits to [amahfouz1][1]
* [JNA fails to load with GraalVM throwing JNA: Problems loading core IDs: java.lang.Object][2]

[1]: https://github.com/amahfouz1/jna-graalvm
[2]: https://github.com/oracle/graal/issues/2261
