package com.api.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.api.builder.RequestBuilder;
import com.api.models.request.Client;

import io.restassured.response.Response;

public class ClientService {
	
	/*
	 * http://server-name/api-auth/login
	 * baseUrl = http://server-name/
	 * basePath = /api-auth
	 * endPoint = /login
	 * 
	 */

	private static final String BASE_URL = "http://localhost:3000";
	private static final String BASE_PATH = "/clients";
	
	public Response getClient(String id) {


		String endpoint = "";
		
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");
		
		Map<String, String> pathParamMap = new LinkedHashMap<>();
		pathParamMap.put("id", id);

		return RequestBuilder.get(
				BASE_URL, 
				BASE_PATH + endpoint , 
				headerMap,
				pathParamMap);
	}

	public Response getAllClient() {

		String endpoint = "";

		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");

		Map<String, String> pathParamMap = new LinkedHashMap<>();
		
		return RequestBuilder.get(
				BASE_URL, 
				BASE_PATH + endpoint, 
				headerMap,
				pathParamMap);
	}

	public Response postClient(Client client) {
		String endpoint = "/";

		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");
		
		Map<String, String> pathParamMap = new LinkedHashMap<>();
		
		return RequestBuilder.post(
				BASE_URL, 
				BASE_PATH + endpoint, 
				headerMap,
				pathParamMap,
				client);
	}

	public Response updateClient(String id,Client client) {

		String endpoint = "";

		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");
		
		Map<String, String> pathParamMap = new LinkedHashMap<>();
		pathParamMap.put("id", id);
		
		Client updatedClient = client.toBuilder().setAge(0).build();
		
		return RequestBuilder.put(
				BASE_URL, 
				BASE_PATH + endpoint, 
				headerMap, 
				pathParamMap,
				updatedClient);
	}

	public Response deleteClient(String id) {

		String endpoint = "";

		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");

		Map<String, String> pathParamMap = new LinkedHashMap<>();
		pathParamMap.put("id", id);
		
		return RequestBuilder.delete(
				BASE_URL, 
				BASE_PATH + endpoint, 
				headerMap,
				pathParamMap);
	}
}
