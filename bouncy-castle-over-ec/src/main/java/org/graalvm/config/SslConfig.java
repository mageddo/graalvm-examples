package org.graalvm.config;

import org.graalvm.nativeimage.ImageInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class SslConfig {
	public static String msg;
	static {
		if(ImageInfo.inImageBuildtimeCode()){
			setup();
		}
	}

	private static void setup() {
		System.loadLibrary("sunec");
		msg = String.valueOf(Math.random());
		System.out.println(">>>>>>>>>>>>>>>>>> initialized <<<<<<<< " + msg);
//		System.out.println(System.getProperties().toString().replaceAll(",", "\n"));
	}

	private static void installTrustStore() {
		try {
			final Path tempCacerts = Files.createTempFile("cacerts", ".tmp");
			InputStream in = null;
			Files.copy(in, tempCacerts, StandardCopyOption.REPLACE_EXISTING);
			System.setProperty("javax.net.ssl.trustStore", tempCacerts.toString());
		} catch (IOException e){
			throw new UncheckedIOException(e);
		}
	}
}
