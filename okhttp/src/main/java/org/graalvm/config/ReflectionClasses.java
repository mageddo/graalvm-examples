package org.graalvm.config;

import com.oracle.svm.core.annotate.AutomaticFeature;
import org.graalvm.nativeimage.hosted.Feature;

@AutomaticFeature
class ReflectionClasses implements Feature {

	@Override
	public void duringSetup(DuringSetupAccess access) {
		System.out.println("java.library.path=" + System.getProperty("java.library.path"));
		System.loadLibrary("sunec");
	}
}

