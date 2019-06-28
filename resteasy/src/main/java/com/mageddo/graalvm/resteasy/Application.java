package com.mageddo.graalvm.resteasy;

import com.mageddo.common.resteasy.RestEasy;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.NoOpLog;
import org.apache.commons.logging.impl.SimpleLog;
import org.jboss.resteasy.client.jaxrs.i18n.LogMessages;
import org.jboss.resteasy.client.jaxrs.i18n.LogMessages_$logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		LOG.info("starting up ....");
		curl("http://acme.com");
		curl("https://google.com");
	}

	public static void curl(String url){
		String res = RestEasy
			.newClient(1)
			.target("http://acme.com")
			.request()
			.get(String.class)
			;
		System.out.printf("> %s%n%150s%n...%n%n", url, res);
	}
}
