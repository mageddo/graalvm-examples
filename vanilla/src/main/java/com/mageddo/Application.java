package com.mageddo;

import com.mageddo.fruit.FruitResource;
import com.mageddo.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import nativeimage.Reflection;

@Slf4j
@Reflection(declaredConstructors = true, declaredMethods = true, scanPackage = "com.mageddo.entrypoint")
public class Application {

  public static void main(String[] args) {

    final var fruits = new FruitResource().get();
    final var fruitsJson = JsonUtils.writeValueAsString(fruits);

    log.info("status=someFruits, fruits={}", fruitsJson);
  }
}
