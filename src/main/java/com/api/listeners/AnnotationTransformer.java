package com.api.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.api.enums.ExcelFile;
import com.api.enums.ExcelSheet;
import com.api.utils.ExcelUtils;

public class AnnotationTransformer implements IAnnotationTransformer {

	private static final List<Map<String, String>> tcList = ExcelUtils.read(ExcelFile.RUNNER, ExcelSheet.TESTCASES);

	private static List<Map<String, String>> getTestCases() {
		return tcList;
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setEnabled(false);
		for (Map<String, String> map : getTestCases()) {
			if (testMethod.getName().equalsIgnoreCase(map.get("TestCaseName"))) {
				annotation.setDescription(map.get("TestCaseDescription"));
				annotation.setEnabled(Boolean.parseBoolean(map.get("Execute")));
//				annotation.setInvocationCount(Integer.parseInt(map.get("InvocationCount")));
				annotation.setPriority(Integer.parseInt(map.get("Priority")));
				annotation.setGroups(map.get("Group").split(","));
//				annotation.setRetryAnalyzer(RetryAnalyzer.class);
				
			}
		}
	}
}
