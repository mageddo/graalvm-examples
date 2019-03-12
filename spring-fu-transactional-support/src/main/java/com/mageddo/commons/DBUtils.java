package com.mageddo.commons;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import static com.mageddo.demo.ApplicationContextProvider.context;

public final class DBUtils {

	private DBUtils() {
	}

	public static PlatformTransactionManager tx(){
		return context().getBean(PlatformTransactionManager.class);
	}

	public static JdbcTemplate template(){
		return context().getBean(JdbcTemplate.class);
	}

	public static NamedParameterJdbcTemplate namedTemplate(){
		return context().getBean(NamedParameterJdbcTemplate.class);
	}


}
