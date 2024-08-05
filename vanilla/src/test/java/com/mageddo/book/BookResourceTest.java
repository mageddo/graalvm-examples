package com.mageddo.book;

import com.fasterxml.jackson.databind.util.NativeImageUtil;
import org.junit.jupiter.api.Test;

import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BookResourceTest {

  final BookResource resource = new BookResource();

  @Test
  void mustSerializeBookAsJsonWhenInJarMode() {
    // arrange
    assumeTrue(!NativeImageUtil.isInNativeImage());

    // act
    final var json = this.resource.getAsJson();

    // assert
    assertEquals(
      """
      [ {
        "name" : "Como fazer amigos e influenciar pessoas"
      }, {
        "name" : "Comunicação não violenta"
      } ]""", json
    );
  }

  @Test
  void wontBeCapableToSerializeAsJsonInNativeImageModeWhenNotMappedTheReflectionResources() {
    // arrange
    assumeTrue(NativeImageUtil.isInNativeImage());

    // act
    final var ex = assertThrows(UncheckedIOException.class, () -> this.resource.getAsJson());

    // assert
    assertTrue(ex.getMessage().contains("No serializer found for class com.mageddo.book.Book"));
  }
}
