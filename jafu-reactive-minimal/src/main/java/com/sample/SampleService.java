package com.sample;

import static com.sample.ApplicationContextProvider.context;

public class SampleService {

	public String generateMessage() {
		System.out.println("self was called " + self());
		return "Hello world!";
	}

	/**
	 * Just making sure programmatic injection still work
	 */
	private SampleService self(){
		return context().getBean(SampleService.class);
	}
}
