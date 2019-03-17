package com.mageddo.micronaut;

import com.mageddo.micronaut.config.ApplicationContextUtils;
import io.micronaut.runtime.Micronaut;

public class Application {

	public static void main(String[] args) {
		ApplicationContextUtils.context(Micronaut.run(Application.class));
	}
}
