package com.mageddo.graalvm.bc;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class Application {

	static {
		setupBC();
//		Security.addProvider(new BouncyCastleProvider());
	}

	static void setupBC() {
		Security.removeProvider("SunEC");
		Security.removeProvider("SunJSSE");

		Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
		Security.insertProviderAt(new BouncyCastleProvider(), 1);
		Security.removeProvider(BouncyCastleJsseProvider.PROVIDER_NAME);
		Security.insertProviderAt(new BouncyCastleJsseProvider(), 2);
	}

	public static void main(String[] args) throws Exception {
		hashCalc(args);
		doGet("https://api.github.com/repos/square/okhttp/contributors");
		doGet("https://acme.com/robots.txt");
	}

	private static void doGet(String url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("GET");
		conn.setUseCaches(false);
		conn.connect();
		System.out.println(conn.getResponseCode());
		IOUtils.copy(conn.getInputStream(), System.out);
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

}
