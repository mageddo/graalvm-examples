package com.mageddo.micronaut.dao;


import com.mageddo.micronaut.entity.FruitEntity;
import com.mageddo.rawstringliterals.RawString;
import com.mageddo.rawstringliterals.Rsl;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.List;

import static com.mageddo.rawstringliterals.RawStrings.lateInit;

@Rsl
@Singleton
public class FruitsDAOPg implements FruitsDAO {

	private final NamedParameterJdbcTemplate parameterJdbcTemplate;

	public FruitsDAOPg(NamedParameterJdbcTemplate parameterJdbcTemplate) {
		this.parameterJdbcTemplate = parameterJdbcTemplate;
	}

	@Override
	public void traceSelect() {
		parameterJdbcTemplate.update(
			"INSERT INTO FRUITS_TRACE VALUES (:v)",
			new MapSqlParameterSource().addValue("v", LocalDateTime.now().toString())
		);
	}

	@Override
	public List<FruitEntity> getFruits() {
		/*
		SELECT NAM_FRUIT FROM FRUITS
		*/
		@RawString
		final String sql = lateInit();
		return parameterJdbcTemplate.query(sql, FruitEntity.mapper());
	}
}
