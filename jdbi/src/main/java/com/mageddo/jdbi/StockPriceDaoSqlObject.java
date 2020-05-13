package com.mageddo.jdbi;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterRowMapper(StockRowMapper.class)
public interface StockPriceDaoSqlObject {
  @SqlQuery("SELECT * FROM STOCK")
  List<Stock> find();
}
