package com.mageddo.micronaut.config;

import io.micronaut.context.ApplicationContext;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationContextUtils {

	private static ApplicationContext context;

	public static ApplicationContext context(){
		return context;
	}

	public static void context(ApplicationContext context){
		ApplicationContextUtils.context = context;
	}
}
