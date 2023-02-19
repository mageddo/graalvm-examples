Working jna with native image sample

```bash
$ ./gradlew build nativeCompile && ./build/native/nativeCompile/graalvm-jna

Aqui começa mais uma história de amor!
hostname=typer-pc - 0
```

## References
* Credits to [amahfouz1][1]
* [JNA fails to load with GraalVM throwing JNA: Problems loading core IDs: java.lang.Object][2]

[1]: https://github.com/amahfouz1/jna-graalvm
[2]: https://github.com/oracle/graal/issues/2261
