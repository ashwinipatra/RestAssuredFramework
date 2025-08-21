package com.api.loggers;

import com.api.reports.ExtentReport;
import com.aventstack.extentreports.markuputils.Markup;

public final class ExtentLogger {

	private ExtentLogger() {

	}

	public static void pass(String testCase) {
		ExtentReport.getTest().pass("Test Case: " + testCase + " passed");
	}

	public static void fail(String testCase, Throwable throwable) {
		ExtentReport.getTest().fail("Test Case: " + testCase + " failed");
		ExtentReport.getTest().fail(throwable);

	}

	public static void skip(String testCase) {
		ExtentReport.getTest().skip("Test Case: " + testCase + " skipped");

	}

	public static void info(String message) {
		ExtentReport.getTest().info(message);
	}

	public static void info(Markup markup) {
		ExtentReport.getTest().info(markup);
	}

}
