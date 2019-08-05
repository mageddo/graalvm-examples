package com.mageddo.micronaut.config;

import com.mageddo.bookmarks.utils.ThymeleafUtils;
import com.mageddo.common.graalvm.SubstrateVM;
import com.oracle.svm.core.annotate.AutomaticFeature;
import org.flywaydb.core.internal.logging.javautil.JavaUtilLogCreator;
import org.graalvm.nativeimage.hosted.Feature;
import org.springframework.transaction.TransactionDefinition;
import org.thymeleaf.standard.expression.AdditionExpression;
import org.thymeleaf.standard.expression.EqualsExpression;
import org.thymeleaf.standard.expression.NotEqualsExpression;

import java.sql.Statement;

@AutomaticFeature
class ReflectionClasses implements Feature {

	@Override
	public void duringSetup(DuringSetupAccess access) {
		System.loadLibrary("sunec");
	}

	@Override
	public void beforeAnalysis(BeforeAnalysisAccess access) {

		// hikari datasource
		SubstrateVM
			.builder()
			.constructors()
			.clazz(Statement[].class)
			.build();

		// flyway migration
		SubstrateVM
			.builder()
			.constructors()
			.clazz(JavaUtilLogCreator.class)
			.build();

		SubstrateVM
			.builder()
			.fields()
			.clazz(TransactionDefinition.class)
			.build();

		// thymeleaf
		SubstrateVM
			.builder()
			.constructors()
			.clazz(AdditionExpression.class)
			.clazz(EqualsExpression.class)
			.clazz(NotEqualsExpression.class)
			.build();

	}

}
