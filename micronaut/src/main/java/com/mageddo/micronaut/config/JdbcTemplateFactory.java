package com.mageddo.micronaut.config;

import io.micronaut.context.annotation.Factory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.inject.Singleton;
import javax.sql.DataSource;

@Factory
public class JdbcTemplateFactory {

	@Singleton
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Singleton
	public NamedParameterJdbcTemplate namedJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Singleton
	public DataSourceTransactionManager DataSourceTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
