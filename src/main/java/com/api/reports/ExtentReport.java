package com.api.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.api.constants.Constants;
import com.api.enums.ConfigProperty;
import com.api.utils.PropertyUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ExtentReport {

	private ExtentReport() {

	}
	private static final ExtentReports report = new ExtentReports();
	private static final String documentTitle = PropertyUtils.get(ConfigProperty.DOCUMENT_TITLE);
	private static final String reportName = PropertyUtils.get(ConfigProperty.REPORT_NAME);
	private static final Theme theme = Theme.DARK;
	private static final String reportFilePath = Constants.getReportFilePath();

	public static void createReport() {

		ExtentSparkReporter spark = new ExtentSparkReporter(reportFilePath);
		report.attachReporter(spark);
		spark.config().setDocumentTitle(documentTitle);
		spark.config().setReportName(reportName);
		spark.config().setTheme(theme);
		report.setSystemInfo("reporter", "Ashwini");
		report.setSystemInfo("os", "MAC-OS");
		report.setSystemInfo("device", "pc");
	}

	public static void flushReport() {
		report.flush();
	}

	public static void publishReport() {
		try {
			Desktop.getDesktop().browse(new File(reportFilePath).toURI());
		} catch (IOException e) {
			System.out.println("Report could not be opened");
			e.printStackTrace();
		}
	}

	public static void createTest(String testName) {
		ExtentTestManager.set(report.createTest(testName));
	}
	
	public static void setGroups(String[] groups) {
		getTest().assignCategory(groups);
	}

	public static void setAuthors(String[] authors) {
		getTest().assignAuthor(authors);
	}

	public static ExtentTest getTest() {
		return ExtentTestManager.get();
	}

	public static void removeTest() {
		ExtentTestManager.remove();
	}

}
