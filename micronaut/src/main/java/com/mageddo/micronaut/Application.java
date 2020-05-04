package com.mageddo.micronaut;

import com.mageddo.micronaut.config.ApplicationContextUtils;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import nativeimage.Reflection;

@Reflection(declaredConstructors = true, declaredMethods = true, scanPackage = "com.mageddo.micronaut.entity")
public class Application {

	public static void main(String[] args) {
		setupProperties();
		Micronaut.run(Application.class);
	}

	@EventListener
	public void onStartup(ServerStartupEvent event){
		ApplicationContextUtils.context(event.getSource().getApplicationContext());
	}

	static void setupProperties() {
		commonsLoggingFix();
	}

	private static void commonsLoggingFix() {
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		System.setProperty("org.apache.commons.logging.diagnostics.dest", "STDOUT");
	}

}
