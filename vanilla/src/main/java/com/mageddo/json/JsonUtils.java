package com.mageddo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.UncheckedIOException;

public class JsonUtils {
  private static final ObjectMapper objectMapper = JsonMapper.builder()
    .enable(SerializationFeature.INDENT_OUTPUT)
    .enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
    .build()
  ;

  public static ObjectMapper getInstance() {
    return objectMapper;
  }

  public static String writeValueAsString(Object o) {
    try {
      return objectMapper.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      throw new UncheckedIOException(e);
    }
  }
}
