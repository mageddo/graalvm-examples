package com.mageddo.demo.dao;

import com.mageddo.demo.entity.FruitEntity;
import com.mageddo.commons.Maps;
import com.mageddo.rawstringliterals.RawString;
import com.mageddo.rawstringliterals.Rsl;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static com.mageddo.rawstringliterals.RawStrings.lateInit;

@Rsl
public class FruitsDAOPg implements FruitsDAO {

	private final NamedParameterJdbcTemplate parameterJdbcTemplate;

	public FruitsDAOPg(NamedParameterJdbcTemplate parameterJdbcTemplate) {
		this.parameterJdbcTemplate = parameterJdbcTemplate;
	}

	@Override
	public void traceSelect() {
		parameterJdbcTemplate.update("INSERT INTO FRUITS_TRACE VALUES (:v)", Maps.of("v", LocalDateTime.now().toString()));
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
