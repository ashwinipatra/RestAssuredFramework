package com.api.tests;

import org.testng.annotations.Test;

import com.api.annotations.Authors;
import com.api.dataproviders.DataProviders;
import com.api.models.request.Client;
import com.api.services.ClientService;

import io.restassured.response.Response;

public class ClientTestDD {

	@Authors({ "B", "C" })
	@Test(dataProvider = "clientProvider",dataProviderClass = DataProviders.class)
	public void createClient(Client client) {
		ClientService clientService = new ClientService();
		Response resp = clientService.postClient(client);
		String id = resp.jsonPath().getString("id");
		clientService.deleteClient(id);
	}

}
