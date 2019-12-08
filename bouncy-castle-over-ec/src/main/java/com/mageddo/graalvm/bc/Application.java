package com.mageddo.graalvm.bc;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;
import sun.security.jca.ProviderList;
import sun.security.jca.Providers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.*;

public class Application {

	static {
//		setupBC();
	}

	public static void main(String[] args) throws Exception {
		try {
			hashCalc(args);
			doGet("https://acme.com/robots.txt");
			doGet("https://api.github.com/repos/square/okhttp/contributors");
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void doGet(String url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("GET");
		conn.setUseCaches(false);
		conn.connect();
		System.out.println(conn.getResponseCode());
		conn.disconnect();
	}

	private static void hashCalc(String[] args) throws NoSuchAlgorithmException {
		final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		final String in = args.length > 0 ? args[0] : "ABC";
		messageDigest.update(in.getBytes());
		System.out.printf("hash=%s%n", toHex(messageDigest.digest()));
		System.out.printf("provider=%s", messageDigest.getProvider());
	}

	static String toHex(byte[] bytes) {
		final StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	static void setupBC() {

//		Providers.getProviderList().providers().add(new BouncyCastleProvider());
		final BouncyCastleProvider provider = new BouncyCastleProvider();
//		provider.addAlgorithm("SecureRandom.DEFAULT", "org.bouncycastle.jcajce.provider.drbg.DRBG$Default");
		System.out.println(Providers.getProviderList().toString());
		for (Object key : provider.keySet()) {
			System.out.printf("k=%s, v=%s%n", key, provider.get(String.valueOf(key)));
		}
		System.out.printf("secure provider = %s%n", provider.getService("SecureRandom", "DEFAULT"));

		ProviderList providers = ProviderList.newList();
		providers = ProviderList.insertAt(
			Providers.getProviderList(), provider, 0
		);
		providers = ProviderList.remove(providers, "SunEC");
		providers = ProviderList.remove(providers, "SunJSSE");
		providers = ProviderList.remove(providers, "SunPKCS11");
//		[BC, SUN, SunRsaSign, SunJCE, SunJGSS, SunSASL, XMLDSig, SunPCSC, JdkLDAP, JdkSASL, SunPKCS11]

		Providers.setProviderList(providers);

		System.out.println(Providers.getProviderList().toString());
//		providers.add(new BouncyCastleProvider());

		Security.removeProvider("SunEC");
		Security.removeProvider("SunJSSE");

		Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
		Security.insertProviderAt(provider, 1);
		Security.removeProvider(BouncyCastleJsseProvider.PROVIDER_NAME);
		Security.insertProviderAt(new BouncyCastleJsseProvider(), 2);


		Security.setProperty("ssl.KeyManagerFactory.algorithm", "X509");
		System.setProperty("jdk.tls.trustNameService", "true");
	}
}
