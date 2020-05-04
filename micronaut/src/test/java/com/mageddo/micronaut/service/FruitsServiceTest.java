package com.mageddo.micronaut.service;

import com.mageddo.micronaut.commons.DatabaseConfigurator;
import com.mageddo.micronaut.commons.DatabaseConfiguratorExtension;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MicronautTest(environments = "test", transactional = false)
@ExtendWith({DatabaseConfiguratorExtension.class})
class FruitsServiceTest {

	@Inject
	private DatabaseConfigurator databaseConfigurator;

	@Inject
	private FruitsService fruitsService;

	@Test
	void shouldFindFruitsAndRegisterTrace(){
		// arrange

		// act
		final var fruits = this.fruitsService.getFruits();

		// assert
		assertEquals(5, fruits.size());
		assertEquals(1, this.fruitsService.countTraces());
	}

	@Test
	void mustFind2FruitsTraces(){

		// arrange

		this.databaseConfigurator.execute("/fruits-service-test/001.sql");

		// act
		final int traces = this.fruitsService.countTraces();

		// assert
		assertEquals(2, traces);

	}

	@Test
	void mustFind2FruitsTracesAgainBecauseOfDatabaseConfiguratorExtension(){
		mustFind2FruitsTraces();
	}

}
