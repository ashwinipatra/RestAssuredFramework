package com.api.utils;

import java.util.List;

import com.api.enums.ExcelFile;
import com.api.enums.ExcelSheet;
import com.api.enums.JsonFile;

public final class DataProviderUtils {

	private DataProviderUtils() {

	}

	public static <T> List<T> get(ExcelFile excelFile, ExcelSheet excelSheet, Class<T> clazz) {

		return ExcelUtils.read(excelFile, excelSheet, clazz);
	}

	public static <T> T get(JsonFile jsonFile, Class<T> clazz) {

		return JsonUtils.readFile(jsonFile, clazz);
	}

}
