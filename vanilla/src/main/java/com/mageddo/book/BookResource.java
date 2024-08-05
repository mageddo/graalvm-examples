package com.mageddo.book;

import com.mageddo.json.JsonUtils;

import java.util.List;

public class BookResource {

  public List<Book> get() {
    return List.of(
      Book
        .builder()
        .name("Como fazer amigos e influenciar pessoas")
        .build(),
      Book
        .builder()
        .name("Comunicação não violenta")
        .build()
    );
  }

  public String getAsJson() {
    final var items = this.get();
    return JsonUtils.writeValueAsString(items);
  }
}
