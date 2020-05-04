package com.mageddo.micronaut.controller;

import io.micronaut.http.HttpStatus;
import io.micronaut.test.annotation.MicronautTest;
import lombok.TextBlock;
import lombok.TextBlocks;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

@TestInstance(Lifecycle.PER_CLASS)
@MicronautTest(environments = "test", transactional = false)
class FruitsControllerTest {

	@Test
	void mustStartupMicronautAndGetFruits(){

		// arrange

		/*
		[{"name":"GRAPE"},{"name":"STRAWBERRY"},{"name":"APPLE"},{"name":"ORANGE"},{"name":"BLUEBERRY"}]
		 */
		@TextBlock
		final var expectedJson = TextBlocks.lazyInit();

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
