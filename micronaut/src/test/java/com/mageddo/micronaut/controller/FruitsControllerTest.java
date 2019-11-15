package com.mageddo.micronaut.controller;

import com.mageddo.rawstringliterals.RawString;
import com.mageddo.rawstringliterals.RawStrings;
import com.mageddo.rawstringliterals.Rsl;
import io.micronaut.context.env.Environment;
import io.micronaut.http.HttpStatus;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.zonky.test.db.postgres.junit.EmbeddedPostgresRules;
import io.zonky.test.db.postgres.junit5.EmbeddedPostgresExtension;
import io.zonky.test.db.postgres.junit5.SingleInstancePostgresExtension;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.inject.Inject;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

@Rsl
@TestInstance(Lifecycle.PER_CLASS)
@MicronautTest(environments = "test")
@ExtendWith({RestAssuredExtension.class})
class FruitsControllerTest {

	@RegisterExtension
	public static final SingleInstancePostgresExtension postgres = EmbeddedPostgresExtension.singleInstance()
		.customize(customizer -> {
			customizer.setPort(5431);
		});

	@Inject
	private EmbeddedServer embeddedServer;

	@Test
	void mustStartupMicronautAndGetFruits(){


		// arrange

		/*
		[{"name":"GRAPE"},{"name":"STRAWBERRY"},{"name":"APPLE"},{"name":"ORANGE"},{"name":"BLUEBERRY"}]
		 */
		@RawString
		final var expectedJson = RawStrings.lateInit();

		// act
		get("/fruits")
			.then()
			.assertThat()
			.statusCode(HttpStatus.OK.getCode())
			.body(equalTo(StringUtils.trim(expectedJson)))
		;

		// assert


	}

}
