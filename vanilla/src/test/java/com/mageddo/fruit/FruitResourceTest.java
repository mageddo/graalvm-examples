package com.mageddo.fruit;

import com.fasterxml.jackson.databind.util.NativeImageUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class FruitResourceTest {

  final FruitResource resource = new FruitResource();

  @Test
  void mustSerializeAsJson() {
    // arrange

    // act
    final var json = this.resource.getAsJson();

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

  @Test
  void unsortedSerializedJsonCanBeDifferentOnNativeImageVersion() {
    // arrange
    assumeTrue(NativeImageUtil.isInNativeImage());

    // act
    final var json = this.resource.getAsUnsortedJson();

    // assert
    assertNotEquals("""
      [ {
        "name" : "Apple",
        "weight" : 150.0
      }, {
        "name" : "Orange",
        "weight" : 245.3
      } ]""", json);
  }

}
