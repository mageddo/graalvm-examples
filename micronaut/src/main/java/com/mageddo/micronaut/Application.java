package com.mageddo.micronaut;

import io.micronaut.runtime.Micronaut;

public class Application {

	public static void main(String[] args) {
		System.out.println(">>>> hi");
		Micronaut.run(Application.class);
	}
}
