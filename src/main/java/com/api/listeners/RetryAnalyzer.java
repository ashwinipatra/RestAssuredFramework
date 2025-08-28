package com.api.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.api.enums.ConfigProperty;
import com.api.utils.PropertyUtils;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int retries = Integer.parseInt(PropertyUtils.get(ConfigProperty.RETRIES));

	@Override
	public boolean retry(ITestResult result) {
		if (retries >= 0) {
			retries--;
			return true;
		}
		return false;
	}

}
