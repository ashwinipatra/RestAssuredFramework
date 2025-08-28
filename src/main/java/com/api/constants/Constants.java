package com.api.constants;

import java.io.File;

import com.api.enums.ConfigProperty;
import com.api.enums.ExcelFile;
import com.api.enums.JsonFile;
import com.api.enums.Schemas;
import com.api.exceptions.FWFileNotFoundException;
import com.api.exceptions.FWInvalidArgumentException;
import com.api.loggers.Log4jLogger;
import com.api.utils.DateTimeUtils;
import com.api.utils.EncoderUtils;
import com.api.utils.PropertyUtils;

/**
 * 
 * Aug 20, 2025
 * 
 * @author ashwinipatra
 * @version 1.0
 * @since 1.1
 */

public final class Constants {

	private Constants() {

	}

	private static final Log4jLogger logger = new Log4jLogger(Constants.class);

	private static final String BASE_PATH = System.getProperty("user.dir");
	private static final String TEST_RESOURCE_PATH = BASE_PATH + "/src/test/resources/";

	private static final String REPORT_FOLDER_PATH = BASE_PATH + "/reports/";
	private static final String REPORT_FILE_NAME = REPORT_FOLDER_PATH + "report.html";

	private static final String RESPONSE_FOLDER_PATH = BASE_PATH + "/responses/";
	private static final String RESPONSE_FILE_NAME = RESPONSE_FOLDER_PATH + "response.json";

	// Property files
	private static final String CONFIG_FOLDER_PATH = TEST_RESOURCE_PATH + "/config/";
	private static final String CONFIG_FILE_PATH = CONFIG_FOLDER_PATH + "config_" + getEnv()
			+ ".properties";

	// testdata
	private static final String TEST_DATA_FOLDER_PATH = TEST_RESOURCE_PATH + "/testdata/";
	private static final String EXCEL_TEST_DATA_FILE_PATH = TEST_DATA_FOLDER_PATH + "testdata.xlsx";

	// runner
	private static final String RUNNER_FOLDER_PATH = TEST_RESOURCE_PATH + "/runner/";
	private static final String EXCEL_RUNNER_FILE_PATH = RUNNER_FOLDER_PATH + "runner.xlsx";

	// Schama
	private static final String SCHEMA_FOLDER_PATH = TEST_RESOURCE_PATH + "/schema/";

	private static final String DB_URL = "jdbc:mysql://localhost:3306/mytest";
	private static final String DB_USER_NAME = "root";
	private static final String DB_PASSWORD = "cGFzc3dvcmQ=";

	public static String getDbUrl() {
		return DB_URL;
	}

	public static String getDbUserName() {
		return DB_USER_NAME;
	}

	public static String getDbUserPassword() {
		return EncoderUtils.decode(DB_PASSWORD);
	}

	public static String getConfigFilePath() {

		return CONFIG_FILE_PATH;
	}

	public static String getExcelFilePath(ExcelFile excelFile) {

		switch (excelFile) {
		case TESTDATA:
			return getFilePath(TEST_DATA_FOLDER_PATH, excelFile, "xlsx");
		case RUNNER:
			return getFilePath(RUNNER_FOLDER_PATH, excelFile, "xlsx");
		default:
			FWInvalidArgumentException throwable = new FWInvalidArgumentException("Invalid excel file argument passed");
			logger.error("Invalid excel file argument passed", throwable);
			throw throwable;
		}

	}

	public static String getJsonFilePath(JsonFile jsonFile) {

		return getFilePath(TEST_DATA_FOLDER_PATH, jsonFile, "json");
	}

	public static String getReportFilePath() {
		boolean overrideReport = Boolean.parseBoolean(PropertyUtils.get(ConfigProperty.OVERRIDE_REPORT));
		if (overrideReport) {
			return REPORT_FILE_NAME;
		} else {
			String currentDateTime = DateTimeUtils.dateTimeToString("YYYY-MM-DD-SS-HH-mm-SS");
			String[] splitReportName = REPORT_FILE_NAME.split("\\.");
			return splitReportName[0] + "-" + currentDateTime + "." + splitReportName[1];

		}
	}

	public static String getResponseFilePath() {
		boolean overrideReport = Boolean.parseBoolean(PropertyUtils.get(ConfigProperty.OVERRIDE_RESPONSE));
		if (overrideReport) {
			return RESPONSE_FILE_NAME;
		} else {
			String currentDateTime = DateTimeUtils.dateTimeToString("YYYY-MM-DD-SS-HH-mm-SS");
			String[] splitReportName = RESPONSE_FILE_NAME.split("\\.");
			return splitReportName[0] + "-" + currentDateTime + "." + splitReportName[1];

		}

	}

	public static String getSchemaFilePath(Schemas schema) {

		return getFilePath(SCHEMA_FOLDER_PATH, schema, "json");

	}

	private static String getFilePath(String folderPath, Enum<?> eum, String fileType) {
		String fileName = eum.toString().toLowerCase() + "." + fileType;
		String filePath = folderPath + fileName;
		File file = new File(filePath);
		if (file.exists()) {
			return filePath;
		} else {
			FWFileNotFoundException fnfe = new FWFileNotFoundException("File " + fileName + " could not be found");
			logger.error("File missing", fnfe);
			throw fnfe;
		}

	}

	private static String getEnv() {
		String env = System.getProperty("environment");
		try {
			if (!env.equals("dev") && !env.equals("qa") && !env.equals("staging") && !env.equals("prod")) {
				logger.fatal("Invalid argument passed for environment");
				System.exit(1);
			}
		} catch (NullPointerException e) {
			env = "dev";
		}
		return env;
	}

}
