By some reason native-image won't generate binary when generating a Proxy for `com.sun.jna.platform.win32.Advapi32` while
it works great on x64.

```bash
$ docker run --rm --privileged multiarch/qemu-user-static --reset -p yes
$ docker-compose build aarch64-build --progress=plain
```


But on Linux x64 works great
```bash
$ uname -sm
Linux x86_64

$ ./gradlew build nativeCompile
Finished generating 'graalvm-jna-adv32-arm-usecase' in 29,8s.
```
