package com.mageddo.micronaut.dao;

import com.mageddo.micronaut.entity.FruitEntity;
import com.mageddo.rawstringliterals.RawString;
import com.mageddo.rawstringliterals.Rsl;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.inject.Singleton;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

import static com.mageddo.rawstringliterals.RawStrings.lateInit;

@Rsl
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
		@RawString
		final String sql = lateInit();
		return parameterJdbcTemplate.query(sql, FruitEntity.mapper());
	}
}
