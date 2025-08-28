package com.api.listeners;


import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.annotations.Authors;
import com.api.loggers.Log4jLogger;
import com.api.reports.ExtentReport;

public class Listener implements ISuiteListener, ITestListener {

	private final Log4jLogger logger = new Log4jLogger(Listener.class);
	@Override
	public void onStart(ISuite suite) {
		logger.info("Started running suite: " + suite.getName());
		logger.info("Started creating report");
		ExtentReport.createReport();
	}

	@Override
	public void onFinish(ISuite suite) {
		logger.info("Flushing report");
		ExtentReport.flushReport();
		logger.info("Publishing report");
		ExtentReport.publishReport();
		logger.info("Finished running suite: " + suite.getName());
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("Starting test case: " + result.getName());
		ExtentReport.createTest(result.getName());
		ExtentReport.setAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Authors.class).value());
		ExtentReport.setGroups(result.getMethod().getGroups());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("Test case: " + result.getName() + " has passed");
		ExtentReport.getTest().pass("pass");
		ExtentReport.removeTest();
	}

	@Override

	public void onTestFailure(ITestResult result) {
		logger.error("Test case: " + result.getName() + " has failed", result.getThrowable());
		ExtentReport.getTest().fail("fail");
		ExtentReport.removeTest();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("Test case: " + result.getName() + " was skipped");
		ExtentReport.getTest().skip("skip");
		ExtentReport.removeTest();
	}
}
