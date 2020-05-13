package com.mageddo.jdbi;

public class DuplicatedStockException extends RuntimeException {
  public DuplicatedStockException(Throwable e) {
    super(e);
  }
}
