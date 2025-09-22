package com.api.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api.exceptions.FWException;
import com.api.loggers.Log4jLogger;
import com.opencsv.CSVReader;

final public class CSVUtils {

	private static final Log4jLogger logger = new Log4jLogger(CSVUtils.class);

	private CSVUtils() {

	}

	public static void main(String[] args) {
		System.out.println(CSVUtils.readFile(System.getProperty("user.dir") + "/src/test/resources/testdata/user.csv"));
	}

	private static List<Map<String, String>> readFile(String filePath) {

		List<Map<String, String>> list = new ArrayList<>();

		Path path = Path.of(filePath);
		try (BufferedReader br = Files.newBufferedReader(path)) {

			String[] headers = br.readLine().split(",");
			String line;
			while ((line = br.readLine()) != null) {
				Map<String, String> map = new HashMap<>();
				String[] fields = line.split(",");
				for (int i = 0; i < fields.length; i++) {
					map.put(headers[i], fields[i]);
				}
				list.add(map);
			}

		} catch (IOException e) {
			logger.error("File could not be read", e.getCause());
			throw new FWException("File could not be read");
		}
		
		return list;

	}
	
	private static <T> void readFile(String filePath,Class<T> clazz) {
			
		
		try(CSVReader csvReader =  new CSVReader(new FileReader(filePath))) {
			
		}catch(IOException e) {
			logger.error("File could not be read", e.getCause());
			throw new FWException("File could not be read");

		}
		
		
	}

	

}
