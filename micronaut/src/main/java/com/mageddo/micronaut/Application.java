package com.mageddo.micronaut;

import io.micronaut.runtime.Micronaut;

public class Application {

	public static void main(String[] args) {
//		ReflectionClasses.setupClasses();
//		RuntimeReflection.register(java.sql.Statement[].class);
		Micronaut.run(Application.class);
	}
}
