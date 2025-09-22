package com.api.services;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import com.api.filters.LoggingFilter;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public final class BaseRequestSpec{
	
	static {
		RestAssured.filters(new LoggingFilter());

	}
	
	private BaseRequestSpec() {
		
	}

	private static RequestSpecification commonSpec(String baseUri,String basePath,Map<String, String> headersMap) {
		return new RequestSpecBuilder()
				.setBaseUri(baseUri)
				.setBasePath(basePath)
				.addHeaders(headersMap)
				.build();
	}

	public static Response get(String baseUri,String basePath,String endpoint, Map<String, String> headersMap) {
		return given()
				.spec(commonSpec(baseUri,basePath,headersMap))
				.get(endpoint);
	} 

	public static Response getWithPath(String baseUri,String basePath,String endpoint, Map<String, String> headersMap,Map<String, String> pathParamMap) {
		return given()
				.spec(commonSpec(baseUri,basePath,headersMap))
				.pathParams(pathParamMap)
				.get(endpoint);
	} 

	public static Response getWithQuery(String baseUri,String basePath, String endpoint,Map<String, String> headersMap,Map<String, String> queryParamMap) {
		return given()
				.spec(commonSpec(baseUri,basePath,headersMap))
				.queryParams(queryParamMap)
				.get(endpoint);
	} 

	public static Response getWithPathAndQuery(String baseUri,String basePath,String endpoint, Map<String, String> headersMap,Map<String, String> pathParamMap,Map<String, String> queryParamMap) {
		return given()
				.spec(commonSpec(baseUri,basePath,headersMap))
				.pathParams(pathParamMap)
				.get(endpoint);
	} 
	
	public static Response post(String baseUri,String basePath,String endpoint,Map<String, String> headersMap, Object body) {
		return given()
				.spec(commonSpec(baseUri,basePath,headersMap))
				.body(body)
				.post(endpoint);
	}  
	

	public static Response put(String baseUri,String basePath,String endpoint, Map<String, String> headersMap,Map<String, String> pathParamMap, Object body) {
		return  given()
				.spec(commonSpec(baseUri,basePath,headersMap))
				.pathParams(pathParamMap)
				.body(body)
				.put(endpoint);
	}  

	public static Response delete(String baseUri,String basePath,String endpoint,Map<String, String> headersMap,Map<String, String> pathParamMap) {
		return given()
				.spec(commonSpec(baseUri,basePath,headersMap))
				.pathParams(pathParamMap)
				.delete(endpoint);
	}  

}
