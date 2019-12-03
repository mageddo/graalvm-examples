package com.mageddo.graalvm.okhttp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		LOG.info("starting up ....");
		System.out.println(OkHttpContributors.PARSER.writeValueAsString(new OkHttpContributors().findContributors()));
	}

}
