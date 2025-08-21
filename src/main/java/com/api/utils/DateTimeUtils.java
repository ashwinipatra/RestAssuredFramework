package com.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTimeUtils {

	private DateTimeUtils() {

	}

	public static String getDateTime() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd-HH-ss");
		return sdf.format(dt);
	}
}
