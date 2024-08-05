package com.mageddo.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class MockitoBookResourceTest {

  @Spy
  BookResource resource;

  @Test
  void mockitoMustWorkInNativeTest() {

    final var expectedMsg = "abc";

    doReturn(expectedMsg)
      .when(this.resource)
      .getAsJson()
    ;

    assertEquals(expectedMsg, this.resource.getAsJson());
  }
}
