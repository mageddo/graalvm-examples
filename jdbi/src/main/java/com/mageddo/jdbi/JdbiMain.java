package com.mageddo.jdbi;

import java.math.BigDecimal;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class JdbiMain {
  public static void main(String[] args) {

    final var jdbi = Jdbi
        .create("jdbc:postgresql://localhost:5432/db", "root", "root")
        .installPlugin(new SqlObjectPlugin())
        ;
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

    System.out.println();
    System.out.println("SQL OBJECT");
    final var stockPriceDaoSqlObject = jdbi.onDemand(StockPriceDaoSqlObject.class);
    final var stocks2 = stockPriceDaoSqlObject.find();
    System.out.println(stocks2);
    assert !stocks.isEmpty();
  }
}
