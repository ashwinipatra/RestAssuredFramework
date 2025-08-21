package com.api.constants;

import java.io.File;
import java.util.Objects;

import com.api.enums.ConfigProperty;
import com.api.enums.ExcelFile;
import com.api.enums.JsonFile;
import com.api.enums.Schemas;
import com.api.exceptions.FWFileNotFoundException;
import com.api.exceptions.FWInvalidArgumentException;
import com.api.loggers.Log4jLogger;
import com.api.utils.DateTimeUtils;
import com.api.utils.PropertyUtils;

/**
 * 
 * Aug 20, 2025
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
	private static final String DEFAULT_CONFIG_FILE_PATH = CONFIG_FOLDER_PATH + "config.properties";
	private static final String DEV_CONFIG_FILE_PATH = CONFIG_FOLDER_PATH + "config_dev.properties";
	private static final String QA_CONFIG_FILE_PATH = CONFIG_FOLDER_PATH + "config_qa.properties";
	private static final String STAGING_CONFIG_FILE_PATH = CONFIG_FOLDER_PATH + "config_staging.properties";

	// testdata
	private static final String TEST_DATA_FOLDER_PATH = TEST_RESOURCE_PATH + "/testdata/";
	private static final String EXCEL_TEST_DATA_FILE_PATH = TEST_DATA_FOLDER_PATH + "testdata.xlsx";
	
	//runner
	private static final String RUNNER_FOLDER_PATH = TEST_RESOURCE_PATH + "/runner/";
	private static final String EXCEL_RUNNER_FILE_PATH = RUNNER_FOLDER_PATH + "runner.xlsx";
	
	//Schama
	private static final String SCHEMA_FOLDER_PATH = TEST_RESOURCE_PATH + "/schema/";

public static void main(String[] args) {
	System.out.println(getSchemaFilePath(Schemas.CLIENT_SCHEMA));
}
	public static String getConfigFilePath() {
		String env = System.getProperty("environment");

		if (Objects.isNull(env)) {
			logger.info("No argument passed, hence loading default profile from config.properties");
			return DEFAULT_CONFIG_FILE_PATH;
		}
		logger.info("Loading default profile from " + env + " env");
		switch (env.toLowerCase()) {
		case "dev":
			return DEV_CONFIG_FILE_PATH;
		case "qa":
			return QA_CONFIG_FILE_PATH;
		case "staging":
			return STAGING_CONFIG_FILE_PATH;
		default: {
			logger.info("Invalid passed ,hence loading default profile from config.properties");
			return DEFAULT_CONFIG_FILE_PATH;
		}

		}
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
		
		return getFilePath(TEST_DATA_FOLDER_PATH,jsonFile, "json");
	}


	public static String getReportFilePath() {
		boolean overrideReport = Boolean.parseBoolean(PropertyUtils.read(ConfigProperty.OVERRIDE_REPORT));
		if (overrideReport) {
			return REPORT_FILE_NAME;
		} else {
			String currentDateTime = DateTimeUtils.getDateTime();
			String[] splitReportName = REPORT_FILE_NAME.split("\\.");
			return splitReportName[0] + "-" + currentDateTime + "." + splitReportName[1];

		}
	}
	
	public static String getResponseFilePath() {
		boolean overrideReport = Boolean.parseBoolean(PropertyUtils.read(ConfigProperty.OVERRIDE_RESPONSE));
		if (overrideReport) {
			return RESPONSE_FILE_NAME;
		} else {
			String currentDateTime = DateTimeUtils.getDateTime();
			String[] splitReportName = RESPONSE_FILE_NAME.split("\\.");
			return splitReportName[0] + "-" + currentDateTime + "." + splitReportName[1];

		}

	}
	
	public static String getSchemaFilePath(Schemas schema) {
		
	 return	getFilePath(SCHEMA_FOLDER_PATH,schema,"json");

	}

	private static  String getFilePath(String folderPath,Enum<?> eum, String fileType) {
		String fileName = String.valueOf(eum).toLowerCase() + "."+ fileType;
		String filePath =  folderPath + fileName;
		File file = new File(filePath);
		if(file.exists()) {
			return filePath;
		}else {
			FWFileNotFoundException fnfe = new FWFileNotFoundException("File "+ fileName + " could not be found");
			logger.error("File missing", fnfe);
			throw fnfe;
		}
		
	}

}
