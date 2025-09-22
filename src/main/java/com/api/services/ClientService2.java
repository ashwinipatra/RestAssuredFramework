package com.api.services;

import java.util.Map;

import com.api.models.request.Client;

import io.restassured.response.Response;

public class ClientService2  extends BaseRequestSpec2{
	
	/*
	 * http://server-name/api-auth/login
	 * baseUrl = http://server-name/
	 * basePath = /api-auth
	 * endPoint = /login
	 * 
	 */

	private static final String BASE_URL = "http://localhost:3000";
	private static final String BASE_PATH = "/clients";
	private static final Map<String, String> HEADER_MAP = Map.of("Content-Type", "application/json");

	public Response getClient(String id) {
		
		String endpoint = "/{id}";
		
		Map<String, String> pathParamMap = Map.of("id",id);
		
		return getWithPath(
				BASE_URL, 
				BASE_PATH , 
				endpoint,
				HEADER_MAP,
				pathParamMap
				);
	}

	public Response getAllClient() {

		String endpoint = "/";

		return get(
				BASE_URL, 
				BASE_PATH,
				endpoint,
				HEADER_MAP);
	}

	public Response postClient(Client client) {
		String endpoint = "/";
		
		return BaseRequestSpec.post(
				BASE_URL, 
				BASE_PATH,
				endpoint,
				HEADER_MAP,
				client);
	}

	public Response updateClient(String id,Client client) {

		String endpoint = "/{id}";
		Map<String, String> pathParamMap = Map.of("id", id);
		
		Client updatedClient = client.toBuilder().setAge(0).build();
		
		return put(
				BASE_URL, 
				BASE_PATH,
				endpoint, 
				HEADER_MAP, 
				pathParamMap,
				updatedClient);
	}

	public Response deleteClient(String id) {

		String endpoint = "/{id}";
		Map<String, String> pathParamMap = Map.of("id", id);
		
		return delete(
				BASE_URL, 
				BASE_PATH,
				endpoint, 
				HEADER_MAP,
				pathParamMap);
	}
}
