package com.mageddo.graalvm.bouncycastleoverec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class Application {

	static {
		setupBC();
//		Security.addProvider(new BouncyCastleProvider());
	}

	private static void setupBC() {
		Security.removeProvider("SunEC");
		Security.removeProvider("SunJSSE");

		Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
		Security.insertProviderAt(new BouncyCastleProvider(), 1);
		Security.removeProvider(BouncyCastleJsseProvider.PROVIDER_NAME);
		Security.insertProviderAt(new BouncyCastleJsseProvider(), 2);
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		final String in = args.length > 0 ? args[0] : "ABC";
		messageDigest.update(in.getBytes());
		System.out.printf("hash=%s%n", toHex(messageDigest.digest()));
		System.out.printf("provider=%s", messageDigest.getProvider());
	}

	static String toHex(byte[] bytes){
		final StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

}
