package org.graalvm.config;

import com.oracle.svm.core.annotate.AutomaticFeature;
import okhttp3.Authenticator;
import okhttp3.CookieJar;
import okhttp3.Dns;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeReflection;

@AutomaticFeature
class ReflectionClasses implements Feature {

	@Override
	public void duringSetup(DuringSetupAccess access) {
		System.out.println("java.library.path=" + System.getProperty("java.library.path"));
		System.loadLibrary("sunec");
	}

}

