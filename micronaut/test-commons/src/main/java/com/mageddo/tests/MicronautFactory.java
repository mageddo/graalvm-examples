package com.mageddo.tests;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Factory;
import io.micronaut.runtime.event.ApplicationStartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.EmbeddedServer;
import io.restassured.RestAssured;
import io.zonky.test.db.postgres.junit5.EmbeddedPostgresExtension;
import io.zonky.test.db.postgres.junit5.SingleInstancePostgresExtension;
import lombok.SneakyThrows;

@Factory
public class MicronautFactory {

	private static ApplicationContext context;

	@EventListener
	public void eventListener(ApplicationStartupEvent startupEvent){
		setupContext(startupEvent);
		setupEmbeddedDatabase(startupEvent);
		setRestAssured(startupEvent);
	}

	private void setRestAssured(ApplicationStartupEvent startupEvent) {
		final EmbeddedServer server = context().getBean(EmbeddedServer.class);
		RestAssured.port = server.getPort();
		RestAssured.baseURI = String.format("%s://%s", server.getScheme(), server.getHost());
	}

	@SneakyThrows
	private void setupEmbeddedDatabase(ApplicationStartupEvent startupEvent) {
		final SingleInstancePostgresExtension extension = EmbeddedPostgresExtension
			.singleInstance()
			.customize(customizer -> {
				customizer.setPort(5431);
			});
		extension.beforeTestExecution(null);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			extension.afterTestExecution(null);
		}));
	}

	private void setupContext(ApplicationStartupEvent startupEvent) {
		context = startupEvent.getSource().getApplicationContext();
	}

	public static ApplicationContext context() {
		return context;
	}
}
