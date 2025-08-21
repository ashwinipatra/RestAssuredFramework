package com.api.reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentTestManager {

	private ExtentTestManager() {

	}

	private static ThreadLocal<ExtentTest> extentTL = new ThreadLocal<>();

	protected static void set(ExtentTest test) {
		extentTL.set(test);
	}

	protected static ExtentTest get() {
		return extentTL.get();
	}

	protected static void remove() {
		extentTL.remove();
	}

}
