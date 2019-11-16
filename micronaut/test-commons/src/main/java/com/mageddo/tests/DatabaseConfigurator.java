package com.mageddo.tests;

import io.micronaut.core.util.CollectionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static io.micronaut.core.util.CollectionUtils.mapOf;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class DatabaseConfigurator {

	private final NamedParameterJdbcTemplate namedJdbcTemplate;
	private final PlatformTransactionManager platformTransactionManager;

	public void execute(String sqlFile){
		namedJdbcTemplate.update(TestUtils.getResourceAsString(sqlFile), CollectionUtils.mapOf());
	}

	public void truncateTables() {
		new TransactionTemplate(platformTransactionManager).execute((st) -> {
			log.info("status=schema-truncating");
			final StringBuilder sql = new StringBuilder()
				.append("SELECT  \n")
				.append("	CONCAT(TABLE_SCHEMA, '.', TABLE_NAME) \n")
				.append("FROM INFORMATION_SCHEMA.TABLES \n")
				.append("WHERE TABLE_SCHEMA = CURRENT_SCHEMA() \n")
				.append("AND TABLE_NAME NOT IN (:tables) \n")
				.append("ORDER BY TABLE_NAME \n")
				;
			final List<String> tables = namedJdbcTemplate.query(
				sql.toString(),
				CollectionUtils.mapOf("tables", skipTables()),
				(rs, i) -> rs.getString(1)
			);
			namedJdbcTemplate.update("SET CONSTRAINTS ALL DEFERRED", CollectionUtils.mapOf());
			for (final String table : tables) {
				namedJdbcTemplate.update("DELETE FROM " + table, CollectionUtils.mapOf());
			}
			namedJdbcTemplate.update("SET CONSTRAINTS ALL IMMEDIATE", CollectionUtils.mapOf());
			log.info("status=schema-truncated");
			return null;
		});
	}

	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return namedJdbcTemplate;
	}

	Collection<String> skipTables(){
		return Arrays.asList(
			"flyway_schema_history".toLowerCase(),
			"fruits".toLowerCase()
		);
	}

}
