package com.mageddo.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class StockRowMapper implements RowMapper<Stock> {
  @Override
  public Stock map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new Stock()
        .setSymbol(rs.getString("IDT_STOCK"))
        .setPrice(rs.getBigDecimal("NUM_PRICE"))
        ;
  }
}
