package com.mageddo.entrypoint;


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
}
