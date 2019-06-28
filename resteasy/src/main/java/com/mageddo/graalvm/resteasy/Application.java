package com.mageddo.graalvm.resteasy;

import com.mageddo.common.resteasy.RestEasy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		LOG.info("starting up ....");
		curl("http://acme.com");
		curl("https://www.mocky.io/v2/5185415ba171ea3a00704eed");
	}

	public static void curl(String url){
		String res = RestEasy
			.newClient(1, true)
			.target(url)
			.request()
			.get(String.class)
			;
		System.out.printf("> %s%n%.250s%n...%n%n", url, res);
	}
}
