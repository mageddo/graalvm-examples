package org.graalvm.config;

import com.oracle.svm.core.annotate.AutomaticFeature;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeReflection;

@AutomaticFeature
class ReflectionClasses implements Feature {

	@Override
	public void duringSetup(DuringSetupAccess access) {
		System.out.println("java.library.path=" + System.getProperty("java.library.path"));
		System.loadLibrary("sunec");
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

