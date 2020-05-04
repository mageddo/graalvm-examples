package com.mageddo.micronaut.commons;

import io.micronaut.core.io.IOUtils;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@UtilityClass
public class TestUtils {

	@SneakyThrows
	public static String getResourceAsString(String path) {
		return IOUtils.readText(new BufferedReader(new InputStreamReader(TestUtils.class.getResourceAsStream(path))));
	}
}
