package com.mageddo.micronaut;

import com.mageddo.micronaut.config.ApplicationContextUtils;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Factory;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;

import javax.annotation.PostConstruct;

public class Application {

	public static void main(String[] args) {
		Micronaut.run(Application.class);
	}

	@EventListener
	public void onStartup(ServerStartupEvent event){
		ApplicationContextUtils.context(event.getSource().getApplicationContext());
	}
}
