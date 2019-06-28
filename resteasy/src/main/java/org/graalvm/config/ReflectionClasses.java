package org.graalvm.config;

import com.oracle.svm.core.annotate.AutomaticFeature;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.apache.commons.logging.impl.NoOpLog;
import org.apache.commons.logging.impl.SimpleLog;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeReflection;

import javax.net.ssl.SSLContext;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AutomaticFeature
class ReflectionClasses implements Feature {

	@Override
	public void beforeAnalysis(BeforeAnalysisAccess access) {
		setupClasses();
	}

	/**
	 * All classes defined here will have reflection support and be registered as spring beans
	 */
	static Class<?>[] getBeans(){
		return new Class[]{
		};
	}

	/**
	 * All classes defined here will have reflection support
	 */
	static Class<?>[] getClasses(){
		return new Class[]{
			Statement[].class,
			LogFactory.class,
			LogFactoryImpl.class,
			SimpleLog.class,
			String.class,
			Thread.class,
			SSLContext.class,
			org.jboss.resteasy.client.jaxrs.i18n.LogMessages_$logger.class,
			org.jboss.resteasy.resteasy_jaxrs.i18n.LogMessages_$logger.class
		};
	}

	static void setupClasses() {
		try {
			System.out.println("> Loading classes for future reflection support");
			for (final Class<?> clazz : getBeans()) {
				process(clazz);
			}
			for (final Class<?> clazz : getClasses()) {
				process(clazz);
			}
		} catch (Error e){
			if(!e.getMessage().contains("The class ImageSingletons can only be used when building native images")){
				throw e;
			}
		}
	}

	/**
	 * Register all constructors and methods on graalvm to reflection support at runtime
	 */
	private static void process(Class<?> clazz) {
		try {
			System.out.println("> Declaring class: " + clazz.getCanonicalName());
			RuntimeReflection.register(clazz);
			for (final Method method : clazz.getMethods()) {
				System.out.println("\t> method: " + toSignature(method));
				RuntimeReflection.register(method);
			}
			for (final Constructor<?> constructor : clazz.getDeclaredConstructors()) {
				System.out.println("\t> constructor: " + toSignature(constructor));
				RuntimeReflection.register(constructor);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public static String toSignature(Executable executable){
		final String parameters = Stream
			.of(executable.getParameterTypes())
			.map(Class::getSimpleName)
			.collect(Collectors.toList())
			.toString();
		return String.format(
			"%s(%s)", executable.getName(), parameters.substring(1, parameters.length() - 1)
		);
	}
}

