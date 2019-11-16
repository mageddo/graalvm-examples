package com.mageddo.micronaut.controller;

import com.mageddo.rawstringliterals.RawString;
import com.mageddo.rawstringliterals.RawStrings;
import com.mageddo.rawstringliterals.Rsl;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.annotation.MicronautTest;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

@Rsl
@TestInstance(Lifecycle.PER_CLASS)
@MicronautTest(environments = "test")
class FruitsControllerTest {

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
