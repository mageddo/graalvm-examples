package com.mageddo.sqlite;

import com.oracle.svm.core.annotate.AutomaticFeature;
import com.oracle.svm.core.jni.JNIRuntimeAccess;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeReflection;
import org.sqlite.BusyHandler;
import org.sqlite.Function;
import org.sqlite.ProgressHandler;
import org.sqlite.core.NativeDB;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.Arrays;

@AutomaticFeature
class JNIReflectionClasses implements Feature {

	@Override
	public void beforeAnalysis(BeforeAnalysisAccess access) {
		try {
			JNIRuntimeAccess.register(NativeDB.class.getDeclaredMethod("_open_utf8", byte[].class, int.class));
		} catch (Exception e){
			e.printStackTrace();
		}
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
			org.sqlite.core.DB.class,
			NativeDB.class,
			BusyHandler.class,
			Function.class,
			ProgressHandler.class,
			Function.Aggregate.class,
			Function.Window.class,
			org.sqlite.core.DB.ProgressObserver.class,
			java.lang.Throwable.class,
			boolean[].class
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
			for (final Method method : clazz.getDeclaredMethods()) {
				System.out.println("\t> method: " + method.getName() + "(" + Arrays.toString(method.getParameterTypes()) + ")");
				JNIRuntimeAccess.register(method);
				RuntimeReflection.register(method);
			}
			for (final Field field : clazz.getDeclaredFields()) {
				System.out.println("\t> field: " + field.getName());
				JNIRuntimeAccess.register(field);
				RuntimeReflection.register(field);
			}
			for (final Constructor<?> constructor : clazz.getDeclaredConstructors()) {
				System.out.println("\t> constructor: " + constructor.getName() + "(" + constructor.getParameterCount() + ")");
				JNIRuntimeAccess.register(constructor);
				RuntimeReflection.register(constructor);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

