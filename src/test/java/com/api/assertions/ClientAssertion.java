package com.api.assertions;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import com.api.models.request.Client;

public class ClientAssertion extends AbstractAssert<ClientAssertion, Client> {

	protected ClientAssertion(Client actual, Class<?> selfType) {
		super(actual, selfType);
		// TODO Auto-generated constructor stub
	}

	public static ClientAssertion assertThat(Client client) {
		return new ClientAssertion(client, ClientAssertion.class);
	}
	
	public ClientAssertion assertName(String expectedName) {
		Assertions.assertThat(actual.getName()).isEqualTo(expectedName);
		return this;
	}
	public ClientAssertion assertGender(String expectedGender) {
		Assertions.assertThat(actual.getGender()).isEqualTo(expectedGender);
		return this;
	}
	
	public ClientAssertion assertAge(int expectedAge) {
		Assertions.assertThat(actual.getAge()).isEqualTo(expectedAge);
		return this;
	}
}
