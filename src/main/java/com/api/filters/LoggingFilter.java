package com.api.filters;

import java.util.List;
import java.util.Objects;

import com.api.loggers.ExtentLogger;
import com.api.loggers.Log4jLogger;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class LoggingFilter implements Filter {

	private static final Log4jLogger logger = new Log4jLogger(LoggingFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		logRequest(requestSpec);
		Response resp = ctx.next(requestSpec, responseSpec);
		logResponse(resp);
		
		return resp;
	}

	private  static void logRequest(FilterableRequestSpecification requestSpec) {
		String requestMessage = "*********** Request Details *********** ";
		String requestMethod = requestSpec.getMethod();
		String requestUrl = requestSpec.getURI();
		Headers requestHeaders = requestSpec.getHeaders();
		String requestBody = requestSpec.getBody();

		logger.info(requestMessage);
		ExtentLogger.info(MarkupHelper.createLabel(requestMessage, ExtentColor.GREEN));

		logger.info("Method: " + requestMethod);
		ExtentLogger.info(MarkupHelper.createLabel("Method: ", ExtentColor.BLUE));
		ExtentLogger.info(requestMethod);
		

		logger.info("Url: " + requestUrl);
		ExtentLogger.info(MarkupHelper.createLabel("Url: ", ExtentColor.BLUE));
		ExtentLogger.info(requestUrl);

		logger.info("Headers: " + requestHeaders);
		ExtentLogger.info(MarkupHelper.createLabel("Headers: ", ExtentColor.BLUE));
		ExtentLogger.info(MarkupHelper.createTable(getTable(requestHeaders)));

		if (Objects.nonNull(requestSpec.getBody())) {
			logger.info("Payload: " + requestBody);
			ExtentLogger.info(MarkupHelper.createLabel("Payload: ", ExtentColor.BLUE));
			ExtentLogger.info(MarkupHelper.createCodeBlock(requestBody, CodeLanguage.JSON));
			}

	}

	private static void logResponse(Response resp) {

		String responseMessage = "*********** Response Details *********** ";
		Headers responseHeaders = resp.getHeaders();

		logger.info(responseMessage);
		ExtentLogger.info(MarkupHelper.createLabel(responseMessage, ExtentColor.GREEN));

		logger.info("Headers: " + responseHeaders);
		ExtentLogger.info(MarkupHelper.createLabel("Headers: ", ExtentColor.BLUE));
		ExtentLogger.info(MarkupHelper.createTable(getTable(responseHeaders)));


		if (Objects.nonNull(resp.getBody())) {
			logger.info("Body: " + resp.prettyPrint());
			ExtentLogger.info(MarkupHelper.createLabel("Body: ", ExtentColor.BLUE));
			ExtentLogger.info(MarkupHelper.createCodeBlock(resp.asString(), CodeLanguage.JSON));
		}

	}
	
	private static String[][] getTable(Headers headers) {
		
		List<Header> headerList = headers.asList();
		
		String[][] arr = new String[headerList.size()+1][2];
		arr[0][0] = "Name";
		arr[0][1] = "Value";
		for(int i=0;i<headerList.size();i++) {
			arr[i+1][0] =  headerList.get(i).getName();
			arr[i+1][1] =  headerList.get(i).getValue();
		}
		return arr;
	}

}
