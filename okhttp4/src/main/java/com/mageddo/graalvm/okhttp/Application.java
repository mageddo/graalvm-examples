package com.mageddo.graalvm.okhttp;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.graalvm.config.SslBuildConfig;

import java.security.Provider;
import java.security.Security;

public class Application {

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	public static void main(String[] args) {
		SslBuildConfig.installTrustStore();

		System.out.print("providers=");
		for (Provider provider : Security.getProviders()) {
			System.out.printf("%s, ", provider.getName());
		}
		System.out.println();

		System.out.println(OkHttpGetter.get("https://google.com/robots.txt"));;
		System.out.println(OkHttpGetter.get("https://acme.com/robots.txt"));;
		System.out.println(OkHttpGetter.get("https://api.github.com/repos/square/okhttp/contributors"));;

	}
}
