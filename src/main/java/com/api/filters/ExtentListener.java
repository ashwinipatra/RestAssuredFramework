package com.api.filters;

import java.util.Calendar;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.annotations.Authors;
import com.api.loggers.Log4jLogger;
import com.api.reports.ExtentReport;

public class ExtentListener implements ISuiteListener, ITestListener {
	
	
	private final Log4jLogger logger = new Log4jLogger(ExtentListener.class);
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
	    ExtentReport.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
	    ExtentReport.setAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Authors.class).value());
	    String qualifiedName = result.getMethod().getQualifiedName();
	    int last = qualifiedName.lastIndexOf(".");
	    int mid = qualifiedName.substring(0, last).lastIndexOf(".");
	    String className = qualifiedName.substring(mid + 1, last);
	
	    ExtentReport.setGroups(className);
	    ExtentReport.setGroups(result.getMethod().getGroups());

	    
	  }

	  @Override
	  public void onTestSuccess(ITestResult result) {
	    logger.info("Test case: " + result.getName() + " has passed");
	    ExtentReport.getTest().pass("pass");
	    ExtentReport.getTest().getModel().setEndTime(getTime(result.getEndMillis()));

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
	  
	  private Date getTime(long millis) {
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTimeInMillis(millis);
		    return calendar.getTime();
		  }
}
