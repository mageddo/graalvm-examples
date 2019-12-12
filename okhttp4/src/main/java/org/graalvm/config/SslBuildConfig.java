//package org.graalvm.config;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.UncheckedIOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.Objects;
//
//public class SslBuildConfig {
//	private static byte[] TRUST_STORE;
//	static {
//		if(System.getProperty("org.graalvm.nativeimage.imagecode") != null){
//			System.out.println(">>>>>>>>>>>>>>>>>> build initialized <<<<<<<< ");
//			System.loadLibrary("sunec");
//			TRUST_STORE = caCertsToByArray();
//		}
//	}
//
//	/**
//	 * Configures current truststore based on the settled on {@link #TRUST_STORE}
//	 */
//	public static void installTrustStore() {
//		try {
//			if(TRUST_STORE == null){
//				return;
//			}
//			final Path trustStorePath = Files.createTempFile("cacerts", ".tmp");
//			Files.copy(new ByteArrayInputStream(TRUST_STORE), trustStorePath, StandardCopyOption.REPLACE_EXISTING);
//			System.setProperty("javax.net.ssl.trustStore", trustStorePath.toString());
//			trustStorePath.toFile().deleteOnExit();
//		} catch (IOException e){
//			throw new UncheckedIOException(e);
//		}
//	}
//
//	static byte[] caCertsToByArray() {
//		try {
//			return Files.readAllBytes(getCaCertsPath());
//		} catch (IOException e) {
//			return null;
//		}
//	}
//
//	/**
//	 * @return cacerts path from compiling jdk
//	 */
//	static Path getCaCertsPath() {
//		final String javaHome = Objects.requireNonNull(System.getProperty("java.home"), "java home is required");
//		return Paths.get(javaHome,"/lib/security/cacerts");
//	}
//}
