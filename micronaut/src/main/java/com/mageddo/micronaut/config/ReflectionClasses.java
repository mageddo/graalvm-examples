package com.mageddo.micronaut.config;

import com.mageddo.common.graalvm.SubstrateVM;
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

		SubstrateVM
			.builder()
			.fields()
			.clazz(org.springframework.transaction.TransactionDefinition.class)
			.build();

		// thymeleaf
		SubstrateVM
			.builder()
			.constructors()
			.clazz(org.thymeleaf.standard.expression.AdditionExpression.class)
			.clazz(org.thymeleaf.standard.expression.EqualsExpression.class)
			.clazz(org.thymeleaf.standard.expression.NotEqualsExpression.class)
			.build();
	}

}
