package com.mageddo.micronaut.commons;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.platform.commons.support.AnnotationSupport;

public class DatabaseConfiguratorExtension implements TestInstancePostProcessor, BeforeTestExecutionCallback {
	@Override
	public void postProcessTestInstance(Object o, ExtensionContext extensionContext) throws Exception {
		AnnotationSupport.findAnnotation(extensionContext.getRequiredTestClass(), MicronautTest.class);
	}

	@Override
	public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
		MicronautFactory.context()
		.getBean(DatabaseConfigurator.class)
		.truncateTables()
		;
	}
}
