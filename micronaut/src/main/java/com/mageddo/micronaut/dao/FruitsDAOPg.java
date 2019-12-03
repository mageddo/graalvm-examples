package com.mageddo.micronaut.dao;

import com.mageddo.micronaut.entity.FruitEntity;
import lombok.RequiredArgsConstructor;
import lombok.TextBlock;
import lombok.TextBlocks;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.inject.Singleton;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class FruitsDAOPg implements FruitsDAO {

	private final NamedParameterJdbcTemplate parameterJdbcTemplate;
	private final DataSource dataSource;

	@Override
	public void traceSelect() {
		System.out.println("connection=" + DataSourceUtils.getConnection(dataSource));
		parameterJdbcTemplate.update(
			"INSERT INTO FRUITS_TRACE VALUES (:v)",
			new MapSqlParameterSource().addValue("v", LocalDateTime.now().toString())
		);
	}

	@Override
	public List<FruitEntity> getFruits() {
		System.out.println("connection=" + DataSourceUtils.getConnection(dataSource));
		/*
		SELECT NAM_FRUIT FROM FRUITS
		*/
		@TextBlock
		final String sql = TextBlocks.lazyInit();
		return parameterJdbcTemplate.query(sql, FruitEntity.mapper());
	}
}
