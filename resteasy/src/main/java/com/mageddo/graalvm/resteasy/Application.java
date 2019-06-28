package com.mageddo.graalvm.resteasy;

import com.mageddo.common.resteasy.RestEasy;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.NoOpLog;
import org.apache.commons.logging.impl.SimpleLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {

		Class.forName(SimpleLog.class.getName()).getConstructor(String.class).newInstance("xyz");
		System.setProperty(LogFactory.DIAGNOSTICS_DEST_PROPERTY, "STDOUT");
		LOG.info("starting up ....");

		String res = RestEasy
			.newClient(1)
			.target("http://acme.com")
			.request()
			.get(String.class)
		;
		System.out.println(res);
	}
}
