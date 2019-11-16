package com.mageddo.tests;

import io.micronaut.runtime.server.EmbeddedServer;
import io.restassured.RestAssured;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Field;

public class RestAssuredExtension implements BeforeEachCallback {

	private EmbeddedServer server;

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		setupRestAssuredPort(context);
	}

	private void setupRestAssuredPort(ExtensionContext context) throws IllegalAccessException {
		findServer(context);
		RestAssured.port = server.getPort();
		RestAssured.baseURI = String.format("%s://%s", server.getScheme(), server.getHost());
	}

	private void findServer(ExtensionContext context) throws IllegalAccessException {
		final Field[] fields = FieldUtils.getAllFields(context.getRequiredTestInstance().getClass());
		for (Field field : fields) {
			if(field.getType().equals(EmbeddedServer.class)){
				this.server = (EmbeddedServer) FieldUtils.readField(
					field, context.getRequiredTestInstance(), true
				);
				return ;
			}
		}
		Assertions.fail("Missing bean of type EmbeddedServer on the test class");
	}

}
