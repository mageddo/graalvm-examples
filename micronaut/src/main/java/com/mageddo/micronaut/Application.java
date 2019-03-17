package com.mageddo.micronaut;

import com.mageddo.micronaut.config.ApplicationContextUtils;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;

public class Application {

	public static void main(String[] args) {
//		ReflectionClasses.setupClasses();
//		RuntimeReflection.register(java.sql.Statement[].class);
		ApplicationContext ctx = Micronaut.run(Application.class);
		ApplicationContextUtils.context(ctx);
	}
}
