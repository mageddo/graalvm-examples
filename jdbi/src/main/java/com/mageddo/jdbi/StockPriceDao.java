package com.mageddo.jdbi;

import java.util.List;

public interface StockPriceDao {

  void updateStockPrice(Stock stock);

  Stock getStock(String symbol);

  void createStock(Stock stock);

  List<Stock> find();

  void createTable();
}
