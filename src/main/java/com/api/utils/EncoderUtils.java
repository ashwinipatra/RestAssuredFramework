package com.api.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public final class EncoderUtils {
	
	private static final String SECRET_KEY = "FWSECRETKEY";
	
	private EncoderUtils() {
		
	}
	
	public static void main(String[] args) {
		System.out.println(base64encode("password"));
		System.out.println(base64decode("cGFzc3dvcmQ="));
	}
	public static String base64encode(String text) {
	return Base64.getEncoder().encodeToString(text.getBytes());
		
	}

	public static String base64decode(String text) {
		return new String(Base64.getDecoder().decode(text));

	}

	//https://www.baeldung.com/java-aes-encryption-decryption}

	public static String encode(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
	    Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
	    cipher.init(Cipher.ENCRYPT_MODE, key);
	     
	    return Base64.getEncoder().encodeToString((cipher.doFinal(text.getBytes())));
	}

	public static String decode(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
	    Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
	    cipher.init(Cipher.DECRYPT_MODE, key);
	    ;
	    return new String(cipher.doFinal(Base64.getDecoder().decode(text)));
	}

}