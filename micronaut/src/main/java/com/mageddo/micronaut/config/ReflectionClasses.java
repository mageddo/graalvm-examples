package com.mageddo.micronaut.config;

import com.oracle.svm.core.annotate.AutomaticFeature;
import org.graalvm.nativeimage.hosted.Feature;

//@Reflection(declaredConstructors = true, declaredMethods = true, scanPackage = "com.mageddo.micronaut.entity")
@AutomaticFeature
class ReflectionClasses implements Feature {
}
