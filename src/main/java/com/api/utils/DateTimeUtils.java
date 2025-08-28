package com.api.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtils {

	private DateTimeUtils() {

	}

	public static void main(String[] args) {
		System.out.println(dateTimeToString("YYYY-MM-DD-HH-mm-ss"));
	}

	public static String dateTimeToString(String pattern) {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(pattern);

		return LocalDateTime.now().format(ofPattern);
	}

	public static String dateTimeToString(LocalDateTime dateTime, String pattern) {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(pattern);
		return dateTime.format(ofPattern);
		
	}

	public static String dateToString(LocalDate date, String pattern) {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(pattern);
		return date.format(ofPattern);
	}

	public static LocalDateTime stringToDateTime(String dtInString, String pattern) {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(pattern);
		return LocalDateTime.parse(dtInString,ofPattern);
	}

	public static LocalDate stringToDate(String dtInString, String pattern) {
		
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(pattern);

		return LocalDate.parse(dtInString,ofPattern);
	}
	
	//
//	public static LocalDateTime stringToDateTime(String dateAsString) {
//
//		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("MMMM d, yyyy, HH mm SS");
//
//		return LocalDateTime.parse(dateAsString, ofPattern);
//	}

}
