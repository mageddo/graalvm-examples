package org.graalvm.config;

import com.oracle.svm.core.annotate.AutomaticFeature;
import nativeimage.Reflection;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeReflection;

import java.util.function.Function;

//@Reflection(scanPackage = "org.bouncycastle.jcajce.provider", declaredMethods = true, declaredConstructors = true)
@AutomaticFeature
class ReflectionClasses implements Feature {

	@Override
	public void duringSetup(DuringSetupAccess access) {
//		System.out.println("java.library.path=" + System.getProperty("java.library.path"));
//		System.loadLibrary("sunec");
//		access.registerObjectReplacer(o -> null);
	}

	@Override
	public void beforeAnalysis(BeforeAnalysisAccess access) {

//		try {
//			RuntimeReflection.register(Class.forName("org.bouncycastle.jcajce.provider.drbg.DRBG$Default"));
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	}
}

