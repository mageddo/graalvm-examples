package com.mageddo.graalvm.okhttp;

import org.graalvm.config.SslBuildConfig;

public class Application {
	public static void main(String[] args) throws Exception {
		SslBuildConfig.installTrustStore();
		System.out.println(OkHttpContributors.PARSER.writeValueAsString(new OkHttpContributors().findContributors()));
	}
}
