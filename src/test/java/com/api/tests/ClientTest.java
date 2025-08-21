package com.api.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.api.annotations.Authors;
import com.api.assertions.ClientAssertion;
import com.api.assertions.ResponseAssertion;
import com.api.builder.DataBuilder;
import com.api.constants.Constants;
import com.api.enums.Schemas;
import com.api.models.request.Client;
import com.api.models.request.Company;
import com.api.services.ClientService;

import io.restassured.response.Response;

public class ClientTest {

	private String id;
	private Client client;

	@Authors({ "A", "B" })
	@Test
	public void getClients() {
		ClientService clientService = new ClientService();
		Response resp = clientService.getAllClient();
	}

	@Authors({ "B", "C" })
	@Test
	public void createClient() {
		//Generate data
		List<Company> companies = Arrays.asList(DataBuilder.getCompany(), DataBuilder.getCompany());
		client = DataBuilder.getClient(companies);
		
		//Make api call
		ClientService clientService = new ClientService();
		Response resp = clientService.postClient(client);
		id = resp.jsonPath().getString("id");
		
		//Assertions
		ResponseAssertion.assertThat(resp)
		.assertStatusCode(201)
		.assertResponseSize(2000)
		.assertResponseTime(1000)
		.assertContentType("application/json")
		.assertSchema(Constants.getSchemaFilePath(Schemas.CLIENT_SCHEMA));
		
		ClientAssertion.assertThat(resp.as(Client.class))
		.assertName(client.getName())
		.assertAge(client.getAge())
		.assertGender(client.getGender());
	}

	@Authors({ "C", "D" })
	@Test
	public void getClient() {
		ClientService clientService = new ClientService();
		Response resp = clientService.getClient(id);
	}

	@Authors({ "D", "A" })
	@Test
	public void putClient() {
		ClientService clientService = new ClientService();

		Client updatedClient = client.toBuilder().setAge(0).build();
		Response resp = clientService.updateClient(id, updatedClient);
	}

	@Authors({ "D", "B" })
	@Test
	public void deleteClient() {
		ClientService clientService = new ClientService();
		Response resp = clientService.deleteClient(id);
	}
}
