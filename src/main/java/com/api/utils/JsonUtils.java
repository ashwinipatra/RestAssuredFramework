package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.api.constants.Constants;
import com.api.enums.JsonFile;
import com.api.loggers.Log4jLogger;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

/**
 * Aug 20, 2025
 * 
 * @author ashwinipatra
 * @version 1.0
 * @since 1.1
 */
public final class JsonUtils {


	private JsonUtils() {

	}

	private static final Log4jLogger logger = new Log4jLogger(JsonUtils.class);

	public static <T> T readFile(JsonFile jsonFile, Class<T> claaz) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(new File(Constants.getJsonFilePath(jsonFile)), claaz);
		} catch (JsonParseException e) {
			logger.error("Error parsing json file", e);
		} catch (JsonMappingException e) {
			logger.error("Error mapping json file", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Error reading json file", e);
		}
		return null;
	}

	public static <T> List<T> readFile(JsonFile jsonFile, TypeReference<List<T>> typeRef) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.readValue(new File(Constants.getJsonFilePath(jsonFile)), typeRef);
		} catch (IOException e) {
			logger.error("Error reading json file", e);
		}
		return Collections.emptyList();
	}

	public static void writeResponse(Response resp) {
		try (FileOutputStream fos = new FileOutputStream(Constants.getResponseFilePath())) {
			fos.write(resp.asByteArray());
		} catch (FileNotFoundException e) {
			logger.error("Response file could not be found", e);
		} catch (IOException e) {
			logger.error("Error write response file", e);

		}
	}

}
