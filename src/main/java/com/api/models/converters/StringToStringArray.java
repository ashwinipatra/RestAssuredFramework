package com.api.models.converters;

import com.creditdatamw.zerocell.converter.Converter;

public class StringToStringArray implements Converter<String[]> {

	@Override
	public String[] convert(String value, String columnName, int row) {
		return value.split(",");
	}

}
