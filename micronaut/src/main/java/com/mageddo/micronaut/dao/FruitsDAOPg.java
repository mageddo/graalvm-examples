package com.mageddo.micronaut.dao;

import com.mageddo.micronaut.entity.FruitEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.inject.Singleton;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyMap;

@Singleton
@RequiredArgsConstructor
public class FruitsDAOPg implements FruitsDAO {

	private final NamedParameterJdbcTemplate parameterJdbcTemplate;
	private final DataSource dataSource;

	@Override
	public void traceSelect() {
		System.out.println("connection=" + DataSourceUtils.getConnection(dataSource));
		this.parameterJdbcTemplate.update(
			"INSERT INTO FRUITS_TRACE VALUES (:v)",
			new MapSqlParameterSource().addValue("v", LocalDateTime.now().toString())
		);
	}

	@Override
	public List<FruitEntity> getFruits() {
		System.out.println("connection=" + DataSourceUtils.getConnection(dataSource));
		return this.parameterJdbcTemplate.query("SELECT NAM_FRUIT FROM FRUITS", FruitEntity.mapper());
	}

	@Override
	public int countTraces() {
		return this.parameterJdbcTemplate.queryForObject("SELECT COUNT(1) FROM FRUITS_TRACE", emptyMap(), Integer.class);
	}
}
