package com.api.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.api.enums.ExcelFile;
import com.api.enums.ExcelSheet;
import com.api.models.Runner;
import com.api.utils.ExcelUtils;

public class AnnotationTransformer2 implements IAnnotationTransformer {

	private static final List<Runner> tcList = ExcelUtils.read(ExcelFile.RUNNER, ExcelSheet.TESTCASES,Runner.class);

	private static List<Runner> getTestCases() {
		return tcList;
	}

	@Override
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setEnabled(false);
		for (Runner testcase : getTestCases()) {
			if (testMethod.getName().equalsIgnoreCase(testcase.getTestCaseName())) {
				annotation.setDescription(testcase.getTestCaseDescription());
				annotation.setEnabled(testcase.isExecute());
				annotation.setInvocationCount(testcase.getInvocationCount());
				annotation.setPriority(testcase.getPriority());
				annotation.setGroups(testcase.getGroup());
//				annotation.setRetryAnalyzer(RetryAnalyzer.class);
				
			}
		}
	}
}
