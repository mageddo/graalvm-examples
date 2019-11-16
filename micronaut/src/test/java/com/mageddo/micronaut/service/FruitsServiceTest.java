package com.mageddo.micronaut.service;

import com.mageddo.rawstringliterals.Rsl;
import com.mageddo.tests.DatabaseConfigurator;
import com.mageddo.tests.DatabaseConfiguratorExtension;
import com.mageddo.tests.EmbeddedPostgresExtension;
import com.mageddo.tests.RestAssuredExtension;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.zonky.test.db.postgres.junit5.SingleInstancePostgresExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.inject.Inject;

import java.util.List;

import static io.micronaut.core.util.CollectionUtils.mapOf;
import static org.junit.jupiter.api.Assertions.*;

@Rsl
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MicronautTest(environments = "test")
@ExtendWith({DatabaseConfiguratorExtension.class})
class FruitsServiceTest {

	@Inject
	private DatabaseConfigurator databaseConfigurator;

	@Test
	void mustReturn5Fruits(){

		// arrange
		databaseConfigurator.execute("/fruits-service-test/001.sql");

		// act
		final List fruits = databaseConfigurator.getJdbcTemplate().queryForList("SELECT * FROM FRUITS_TRACE", mapOf());

		// assert
		assertEquals(2, fruits.size());

	}

}
