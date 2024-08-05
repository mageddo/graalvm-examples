package com.mageddo.fruit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fruit {
  String name;
  double weight;
}
