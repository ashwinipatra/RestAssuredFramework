package com.api.assertions;

import java.io.File;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class ResponseAssertion extends AbstractAssert<ResponseAssertion, Response>{

	protected ResponseAssertion(Response actual, Class<?> selfType) {
		super(actual, selfType);
	}
	
	public static ResponseAssertion assertThat(Response resp) {
		return new ResponseAssertion(resp,ResponseAssertion.class);
	}
	
	public ResponseAssertion assertStatusCode(int expectedStatusCode) {
		Assertions.assertThat(actual.getStatusCode())
		.withFailMessage(() -> "Status code expected is " +expectedStatusCode + " got" + actual.getStatusCode())
		.isEqualTo(expectedStatusCode);
		return this;
	}

	public ResponseAssertion assertResponseSize(int expectedResponseSize) {
		Assertions.assertThat(actual.asByteArray().length)
		.isLessThan(expectedResponseSize);
		return this;
	}
	
	public ResponseAssertion assertResponseTime(long expectedResponseTime) {
		Assertions.assertThat(actual.getTime())
		.isLessThan(expectedResponseTime);
		return this;
	}
	
	public ResponseAssertion assertContentType(String expectedContentType) {
		Assertions.assertThat(actual.getContentType())
		.isEqualTo(expectedContentType);
		return this;
	}
	
	public ResponseAssertion assertSchema(String schemaPath) {
		actual.then().body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
		return this;
	}

}
