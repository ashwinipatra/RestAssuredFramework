package com.api.dataproviders;

import org.testng.annotations.DataProvider;

import com.api.enums.JsonFile;
import com.api.models.request.Client;
import com.api.utils.DataProviderUtils;

public class DataProviders {
	
	@DataProvider(name="clientProvider")
	public Client[] clientProvider() {
		return DataProviderUtils.get(JsonFile.CLIENT, Client[].class);
	}

}
