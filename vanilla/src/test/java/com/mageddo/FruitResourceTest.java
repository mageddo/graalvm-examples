package com.mageddo;

import com.mageddo.fruit.FruitResource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FruitResourceTest {

  final FruitResource resource = new FruitResource();

  @Test
  void mustSerializeAsJson() {
    // arrange

    // act
    final var json = this.resource.getSerializedAsJson();

    // assert
    assertEquals("""
      [ {
        "name" : "Apple",
        "weight" : 150.0
      }, {
        "name" : "Orange",
        "weight" : 245.3
      } ]""", json);
  }
}
