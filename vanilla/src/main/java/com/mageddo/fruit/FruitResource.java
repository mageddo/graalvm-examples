package com.mageddo.fruit;


import com.mageddo.json.JsonUtils;
import com.mageddo.json.UnsortedJsonUtils;

import java.util.List;

public class FruitResource {

  public List<Fruit> get(){
    return List.of(
      Fruit
        .builder()
        .name("Apple")
        .weight(150.0)
        .build(),
      Fruit
        .builder()
        .name("Orange")
        .weight(245.3)
        .build()
    );
  }

  public String getAsJson(){
    final var fruits = this.get();
    return JsonUtils.writeValueAsString(fruits);
  }

  public String getAsUnsortedJson(){
    final var fruits = this.get();
    return UnsortedJsonUtils.writeValueAsString(fruits);
  }
}
