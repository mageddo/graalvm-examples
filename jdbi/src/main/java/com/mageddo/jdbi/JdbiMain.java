package com.mageddo.jdbi;

import java.math.BigDecimal;

import org.jdbi.v3.core.Jdbi;

public class JdbiMain {
  public static void main(String[] args) {
    final var jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/db", "root", "root");
    final var stockPriceDao = new StockPriceDaoPostgres(jdbi);
    stockPriceDao.createTable();
    stockPriceDao.truncate();
    stockPriceDao.createStock(new Stock()
        .setSymbol("PAGS")
        .setPrice(BigDecimal.TEN)
    );
    final var stocks = stockPriceDao.find();
    System.out.println(stocks);
    assert !stocks.isEmpty();
  }
}
