package com.mageddo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mageddo.entrypoint.FruitResource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

  public static void main(String[] args) throws Exception {

    final var fruits = new FruitResource().get();
    final var fruitsJson = new ObjectMapper()
      .enable(SerializationFeature.INDENT_OUTPUT)
      .writeValueAsString(fruits);

    log.info("status=someFruits, fruits={}", fruitsJson);
  }
}
