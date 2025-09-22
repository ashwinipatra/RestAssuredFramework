package com.api.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.aeonbits.owner.ConfigFactory;

import com.api.constants.Constants;
import com.api.enums.ConfigProperty;
import com.api.exceptions.FWException;
import com.api.loggers.Log4jLogger;

public final class PropertyUtils {

	private static final Properties prop = new Properties();
	private static final Log4jLogger logger = new Log4jLogger(PropertyUtils.class);

	private PropertyUtils() {
	}

	static {
		String propFile = Constants.getConfigFilePath();
		try (InputStream input = new FileInputStream(propFile)) {
			prop.load(input);
			logger.info("Successfully Loaded config file from: " + propFile);
			
		} catch (FileNotFoundException e) {
			logger.error("File could not be found", e);
			System.exit(0);
		} catch (IOException e) {
			logger.error("File could not be read", e);
			System.exit(0);
		}

	}

	public static String get(ConfigProperty configProperty) {
		String key = configProperty.getKeyName();
		String value = prop.getProperty(key);
		if (value == null || value.isBlank()) {
			logger.error("Property value is null or blank");
			throw new FWException("Terminating program");
		}
		return value;
	}


	
	public static IConfig read() {
	
		return ConfigFactory.create(IConfig.class);
	}

}
