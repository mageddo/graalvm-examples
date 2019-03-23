package com.mageddo.resources.resolution;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.mageddo.resources.resolution.App.getResources;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppTest {
	@Test
	public void shouldSolveDirectoryResource() throws IOException {

		// act
		final List<URL> resources = getResources("folder/subfolder");

		// assert
		assertFalse(resources.isEmpty());
		assertTrue(resources.get(0).toString().contains("subfolder"));
	}

	@Test
	public void shouldSolveFileResource() throws IOException {

		// act
		final List<URL> resources = getResources("folder/subfolder/resource-001.txt");

		// assert
		assertFalse(resources.isEmpty());
		assertTrue(resources.get(0).toString().contains("resource-001.txt"));
	}
}
