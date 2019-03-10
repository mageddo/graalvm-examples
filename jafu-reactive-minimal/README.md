
Building and running 

```bash
$ ./gradlew nativeImage
$ ./build/graal/jafu-reactive-minimal
...
11:34:28.821 [reactor-http-nio-1] DEBUG reactor.netty.tcp.TcpServer - [id: 0x5f527ce6, L:/0:0:0:0:0:0:0:0:8080] Bound new server
11:34:28.821 [main] INFO org.springframework.boot.web.embedded.netty.NettyWebServer - Netty started on port(s): 8080
```

Testing 
```
$ curl -X GET -w '\n' localhost:8080
Hello world!
```
