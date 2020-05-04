package com.mageddo.micronaut.service;

import com.mageddo.micronaut.commons.DatabaseConfigurator;
import com.mageddo.micronaut.commons.DatabaseConfiguratorExtension;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;
import java.util.List;

import static io.micronaut.core.util.CollectionUtils.mapOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MicronautTest(environments = "test")
@ExtendWith({DatabaseConfiguratorExtension.class})
class FruitsServiceTest {

	@Inject
	private DatabaseConfigurator databaseConfigurator;

	@Test
	void mustFind2FruitsTraces(){

		// arrange
		databaseConfigurator.execute("/fruits-service-test/001.sql");

		// act
		final List fruits = databaseConfigurator.getJdbcTemplate().queryForList("SELECT * FROM FRUITS_TRACE", mapOf());

		// assert
		assertEquals(2, fruits.size());

	}

	@Test
	void mustFind2FruitsTracesAgainBecauseOfDatabaseConfiguratorExtension(){
		mustFind2FruitsTraces();
	}

}
