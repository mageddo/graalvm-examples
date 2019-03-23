package com.mageddo.resources.resolution;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class App {

	public static void main(String[] args) throws Exception {
		System.out.println(getResources("folder/subfolder"));
		System.out.println(getResources("folder/subfolder/resource-001.txt"));
	}

	public static List<URL> getResources(String name) throws IOException {
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return Collections.list(classLoader.getResources(name));
	}
}
