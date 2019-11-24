package com.mageddo.micronaut.config;

import com.mageddo.common.graalvm.SubstrateVM;
import com.mageddo.micronaut.entity.FruitEntity;
import com.oracle.svm.core.annotate.AutomaticFeature;
import org.graalvm.nativeimage.hosted.Feature;

@AutomaticFeature
class ReflectionClasses implements Feature {

	@Override
	public void duringSetup(DuringSetupAccess access) {
		System.loadLibrary("sunec");
	}

	@Override
	public void beforeAnalysis(BeforeAnalysisAccess access) {

		// jdbc support
		SubstrateVM
			.builder()
			.constructors()
			.clazz(java.sql.Statement[].class)
			.build();

		// vos

		SubstrateVM
			.builder()
			.constructors()
			.methods()
			.fields()
			.clazz(FruitEntity.class)
			.build();
	}

}
