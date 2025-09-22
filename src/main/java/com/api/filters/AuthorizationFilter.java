package com.api.filters;

import com.api.enums.TokenType;
import com.api.exceptions.FWInvalidArgumentException;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AuthorizationFilter implements Filter{

	private final String token;
	private final TokenType tokenType;
	public AuthorizationFilter(String token,TokenType tokenType) {
		this.token = token;
		this.tokenType = tokenType;
	}
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		
		switch(tokenType) {
		case BASIC : requestSpec.header("Authorization", "Basic " + token);break;
		case BEARER : requestSpec.header("Authorization", "Bearer " + token);break;
		default : requestSpec.and();
		}
		
		return ctx.next(requestSpec, responseSpec);
	}

}
