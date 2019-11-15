package com.mageddo.micronaut.controller;

import io.zonky.test.db.postgres.junit5.SingleInstancePostgresExtension;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmbeddedPostgresExtension {
	public static SingleInstancePostgresExtension instance() {
		return io.zonky.test.db.postgres.junit5.EmbeddedPostgresExtension
			.singleInstance()
			.customize(customizer -> {
				customizer.setPort(5431);
			});
	}
}
