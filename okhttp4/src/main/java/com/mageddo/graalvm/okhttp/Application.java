package com.mageddo.graalvm.okhttp;

import org.graalvm.config.SslBuildConfig;

public class Application {
	public static void main(String[] args) {
		SslBuildConfig.installTrustStore();
		System.out.println(OkHttpGetter.get("https://google.com/robots.txt"));;
		System.out.println(OkHttpGetter.get("https://acme.com/robots.txt"));;
		System.out.println(OkHttpGetter.get("https://api.github.com/repos/square/okhttp/contributors"));;
	}
}
