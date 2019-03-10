package com.sample;

import org.graalvm.nativeimage.RuntimeReflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectionClasses {

	static Class<?>[] getBeans(){
		return new Class[]{
			ApplicationContextProvider.class,
			SampleHandler.class,
			SampleService.class
		};
	}

	static Class<?>[] getClasses(){
		return new Class[]{
			Sample.class
		};
	}

	static void setupClasses() {
		for (final Class<?> clazz : getBeans()) {
			process(clazz);
		}
		for (final Class<?> clazz : getClasses()) {
			process(clazz);
		}
	}

	private static void process(Class<?> clazz) {
		try {
			System.out.println("> Loading classes for future reflection support");
			System.out.println("> Declaring class: " + clazz.getCanonicalName());
			for (final Method method : clazz.getMethods()) {
				System.out.println("\t> method: " + method.getName() + "(" + method.getParameterCount() + ")");
				RuntimeReflection.register(method);
			}
			for (final Constructor<?> constructor : clazz.getDeclaredConstructors()) {
				System.out.println("\t> constructor: " + constructor.getName() + "(" + constructor.getParameterCount() + ")");
				RuntimeReflection.register(constructor);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
