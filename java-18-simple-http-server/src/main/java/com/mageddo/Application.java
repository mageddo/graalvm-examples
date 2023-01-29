package com.mageddo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mageddo.entrypoint.FruitResource;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;
import nativeimage.Reflection;

import java.net.InetSocketAddress;

@Slf4j
@Reflection(declaredConstructors = true, declaredMethods = true, scanPackage = "com.mageddo.entrypoint")
public class Application {

  public static void main(String[] args) throws Exception {
    final var objectMapper = new ObjectMapper()
      .enable(SerializationFeature.INDENT_OUTPUT);
    final var fruitsResource = new FruitResource();

    final var port = new InetSocketAddress(8000);

    log.info("starting.., port={}", port.getPort());
    final var server = HttpServer.create(
      port,
      0,
      "/",
      exchange -> {
        log.info("requestedPath={}", exchange.getRequestURI().getPath());
        final var msg = objectMapper.writeValueAsBytes(fruitsResource.get());
        exchange.sendResponseHeaders(200, msg.length);
        exchange.getResponseBody().write(msg);
      }
    );
    server.start();


//
//    log.info("status=someFruits, fruits={}", fruitsJson);
  }
}
