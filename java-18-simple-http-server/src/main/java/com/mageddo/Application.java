package com.mageddo;

import lombok.extern.slf4j.Slf4j;
import nativeimage.Reflection;

import java.net.InetSocketAddress;
import java.nio.file.Path;

@Slf4j
@Reflection(declaredConstructors = true, declaredMethods = true, scanPackage = "com.mageddo.entrypoint")
public class Application {

  public static void main(String[] args) throws Exception {

    var port = 8000;
    var rootDirectory = Path.of("C:/Users/Mahozad/Desktop/");
    var outputLevel = OutputLevel.VERBOSE;
    var server = SimpleFileServer.createFileServer(
      new InetSocketAddress(port),
      rootDirectory,
      outputLevel
    );
    server.start();


//    final var fruits = new FruitResource().get();
//    final var fruitsJson = new ObjectMapper()
//      .enable(SerializationFeature.INDENT_OUTPUT)
//      .writeValueAsString(fruits);
//
//    log.info("status=someFruits, fruits={}", fruitsJson);
  }
}
