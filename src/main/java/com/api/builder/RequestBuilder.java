package com.api.builder;

import static io.restassured.RestAssured.given;

import java.util.Map;

import com.api.loggers.LoggingFilter;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public final class RequestBuilder {
	
	static {
		RestAssured.filters(new LoggingFilter());

	}
	
	private RequestBuilder() {
		
	}
	
	private static RequestSpecification commonSpec(String baseUri,String basePath,Map<String, String> headersMap) {
		return new RequestSpecBuilder()
				.setBaseUri(baseUri)
				.setBasePath(basePath)
				.addHeaders(headersMap)
				.build();
	}
	
	public static Response get(String baseUri,String basePath, Map<String, String> headersMap,Map<String, String> pathParamMap) {
		
		Response resp = given()
				.spec(commonSpec(baseUri,basePath,headersMap))
				.pathParams(pathParamMap)
				.get(getPath(pathParamMap));
				return resp;
	} 
	
//	public static Response get(String baseUri,String basePath, Map<String, String> headersMap,Map<String, String> pathParamMap) {
//		
//		Response resp = given()
//				.spec(commonSpec(baseUri,basePath,headersMap))
//				.pathParams(pathParamMap)
//				.get(getPath(pathParamMap));
//				return resp;
//	} 

	public static Response post(String baseUri,String basePath,Map<String, String> headersMap, Map<String, String> pathParamMap,Object body) {
		Response resp = given()
				.spec(commonSpec(baseUri,basePath,headersMap))
				.body(body)
				.pathParams(pathParamMap)
				.post(getPath(pathParamMap));
				return resp;
	}  

	public static Response put(String baseUri,String basePath, Map<String, String> headersMap,Map<String, String> pathParamMap, Object body) {
		Response resp = given()
				.spec(commonSpec(baseUri,basePath,headersMap))
				.pathParams(pathParamMap)
				.body(body)
				.put(getPath(pathParamMap));
				return resp;
	}  

	public static Response delete(String baseUri,String basePath,Map<String, String> headersMap,Map<String, String> pathParamMap) {
		System.out.println(getPath(pathParamMap));
		Response resp = given()
				.spec(commonSpec(baseUri,basePath,headersMap))
				.pathParams(pathParamMap)
				.delete(getPath(pathParamMap));
				return resp;
	}  

	private static String getPath(Map<String, String> pathParamMap) {
		String path = "";
		for(String key:pathParamMap.keySet()){
			path = "/{" + key + "}/"; 
		}
		return path;
	}
}
