package com.mageddo.graalvm.dns.resolution;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Application {
	public static void main(String[] args) throws UnknownHostException {
		System.out.println(Arrays.toString(InetAddress.getAllByName("google.com")));;
	}
}
