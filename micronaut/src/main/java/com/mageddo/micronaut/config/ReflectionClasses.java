package com.mageddo.micronaut.config;

import com.oracle.svm.core.annotate.AutomaticFeature;
import nativeimage.Reflection;
import org.graalvm.nativeimage.hosted.Feature;

@Reflection(declaredConstructors = true, declaredMethods = true, scanPackage = "com.mageddo.micronaut.entity")
@AutomaticFeature
class ReflectionClasses implements Feature {
	@Override
	public void duringAnalysis(DuringAnalysisAccess access) {
//		RuntimeReflection.register(com.zaxxer.hikari.util.ConcurrentBag.IConcurrentBagEntry[].class);
	}

}
