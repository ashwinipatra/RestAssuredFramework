package com.api.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.aeonbits.owner.ConfigFactory;

import com.api.constants.Constants;
import com.api.enums.ConfigProperty;
import com.api.exceptions.FWFileNotFoundException;
import com.api.exceptions.FWIOException;
import com.api.exceptions.FWInvalidArgumentException;

public final class PropertyUtils {

	private PropertyUtils() {

	
	}

	private static PropertyUtils instance = null;
	private static Map<String, String> configMap = null;

	private void init() {
		configMap = new HashMap<>();
		try (FileInputStream fis = new FileInputStream(Constants.getConfigFilePath())) {
			Properties prop = new Properties();
			prop.load(fis);
			prop.forEach((k, v) -> configMap.put(String.valueOf(k), String.valueOf(v)));

		} catch (FileNotFoundException fnfe) {
			throw new FWFileNotFoundException(
					"Properites file not found in locateon: " + Constants.getConfigFilePath());
		} catch (IOException ioe) {
			throw new FWIOException("Propety file could not be read");
		}
	}

	private Map<String, String> getMap() {
		return configMap;
	}

	//singleton - double null check 
	private static Map<String, String> getInstance() {
		if (Objects.isNull(instance)) {
			synchronized (PropertyUtils.class) {
				if (Objects.isNull(instance)) {
					instance = new PropertyUtils();
					instance.init();
				}
			}
		}
		return instance.getMap();
	}

	public static String read(ConfigProperty configPropety) {

		Map<String, String> configMap = getInstance();
		String prop = String.valueOf(configPropety).toLowerCase();

		if (Objects.isNull(configMap.get(prop)) || configMap.get(prop).isBlank() || configMap.get(prop).isEmpty()) {
			throw new FWInvalidArgumentException("Property: "+ prop +  " is either null or emtpy");
		}

		return configMap.get(prop).toLowerCase().trim();

	}

	
	public static IConfig read() {
	
		return ConfigFactory.create(IConfig.class);
	}

}
