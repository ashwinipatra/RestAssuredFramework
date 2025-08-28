package com.api.utils;

import java.util.Base64;

public final class EncoderUtils {
	
	private EncoderUtils() {
		
	}
	
	public static void main(String[] args) {
		System.out.println(encode("password"));
		System.out.println(decode("cGFzc3dvcmQ="));
	}
	public static String encode(String text) {
	return Base64.getEncoder().encodeToString(text.getBytes());
		
	}

	public static String decode(String text) {
		return new String(Base64.getDecoder().decode(text));

	}

}
