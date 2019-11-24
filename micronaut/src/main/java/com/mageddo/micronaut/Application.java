package com.mageddo.micronaut;

import com.mageddo.micronaut.config.ApplicationContextUtils;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;

public class Application {

	public static void main(String[] args) {
		Micronaut.run(Application.class);
	}

	@EventListener
	public void onStartup(ServerStartupEvent event){
		ApplicationContextUtils.context(event.getSource().getApplicationContext());
	}
}
