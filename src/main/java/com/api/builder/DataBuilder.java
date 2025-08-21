package com.api.builder;

import java.util.List;

import com.api.models.request.Client;
import com.api.models.request.Company;
import com.api.utils.FakerUtils;

public final class DataBuilder {

	private DataBuilder() {

	}

	public static Client getClient(List<Company> companies) {
		return Client.builder()
				.setName(FakerUtils.getName())
				.setGender(FakerUtils.getGender())
				.setAge(FakerUtils.getAge())
				.setEmail(FakerUtils.getEmail())
				.setAddress(FakerUtils.getAddress())
				.setPhone(FakerUtils.getAddress())
				.setStatus(FakerUtils.getStatus())
				.setCompanies(companies)
				.build();

	}

	public static Company getCompany() {
		return Company.builder()
				.setName(FakerUtils.getCompanyName())
				.setLocation(FakerUtils.getLocation())
				.setDatacenter(FakerUtils.getDataCenter())
				.build();
	}
}
