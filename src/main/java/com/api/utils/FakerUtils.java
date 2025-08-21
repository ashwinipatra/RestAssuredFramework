package com.api.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

	private static final Faker fkr = new Faker();

	public static String getName() {
		return fkr.name().fullName();
	}

	public static String getGender() {
		return fkr.regexify("male|female");
	}

	public static int getAge() {
		return fkr.number().numberBetween(20, 50);
	}

	public static String getCompanyName() {
		return fkr.company().name();
	}

	public static String getLocation() {
		return fkr.address().city();
	}

	public static String getDataCenter() {
		return fkr.regexify("[a-z]{3}") + "-" + fkr.address().city();
	}

	public static String getEmail() {
		return fkr.internet().emailAddress();
	}

	public static String getAddress() {
		return fkr.address().fullAddress();
	}

	public static String getDataPhone() {
		return fkr.phoneNumber().cellPhone();
	}
	
	public static boolean getStatus() {
		return fkr.bool().bool();
	}

}
