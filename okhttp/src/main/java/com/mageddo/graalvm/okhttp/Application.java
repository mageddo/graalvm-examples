package com.mageddo.graalvm.okhttp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LOG.info("starting up ....");
		System.out.println(new OkHttpContributors().findContributors());
	}

}
