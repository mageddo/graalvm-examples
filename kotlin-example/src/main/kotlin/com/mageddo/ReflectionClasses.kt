package com.mageddo;

import com.oracle.svm.core.annotate.AutomaticFeature;
import org.graalvm.nativeimage.hosted.Feature
import org.graalvm.nativeimage.hosted.Feature.BeforeAnalysisAccess
import org.graalvm.nativeimage.hosted.Feature.DuringSetupAccess
import org.graalvm.nativeimage.hosted.RuntimeReflection

@AutomaticFeature
class ReflectionClasses : Feature {
    override fun duringSetup(access: DuringSetupAccess) {

    }

    override fun beforeAnalysis(access: BeforeAnalysisAccess) {
        RuntimeReflection.register(Stuff.javaClass.getDeclaredField("DEFAULT_STUFF"))
    }
}
