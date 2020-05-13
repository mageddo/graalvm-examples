package com.mageddo.jdbi;

import java.math.BigDecimal;

public class Stock {

  private String symbol;
  private BigDecimal price;

  public String getSymbol() {
    return symbol;
  }

  public Stock setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Stock setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  @Override
  public String toString() {
    return "Stock(" +
        "symbol=" + symbol +
        ", price=" + price +
        ')';
  }
}
