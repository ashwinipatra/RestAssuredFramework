package com.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.constants.Constants;
import com.api.enums.ExcelFile;
import com.api.enums.ExcelSheet;
import com.api.exceptions.FWFileNotFoundException;
import com.api.exceptions.FWIOException;
import com.api.models.Runner;
import com.creditdatamw.zerocell.Reader;

public final class ExcelUtils {

	private ExcelUtils() {

	}
	public static void main(String[] args) {
		System.out.println(read(ExcelFile.RUNNER,ExcelSheet.TESTCASES));
		System.out.println(read(ExcelFile.RUNNER,ExcelSheet.TESTCASES,Runner.class));
	}

	public static List<Map<String, String>> read(ExcelFile excelFile, ExcelSheet excelSheet) {

		List<Map<String, String>> excelList = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(Constants.getExcelFilePath(excelFile));
				XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			XSSFSheet sheet = workbook.getSheet(String.valueOf(excelSheet).toLowerCase());
			int rows = sheet.getLastRowNum();
			int cols = sheet.getRow(0).getLastCellNum();
			DataFormatter df = new DataFormatter();
			for (int i = 1; i <= rows; i++) {
				Map<String, String> map = new HashMap<>();
				for (int j = 0; j < cols; j++) {
					map.put(df.formatCellValue(sheet.getRow(0).getCell(j)),
							df.formatCellValue(sheet.getRow(i).getCell(j)));
				}
				excelList.add(map);
			}

		} catch (FileNotFoundException fnfe) {
			throw new FWFileNotFoundException("Excel file could not be found");
		} catch (IOException ioe) {
			throw new FWIOException("Excel found cannot be read");
		}

		return excelList;
	}

	public static <T> List<T> read(ExcelFile excelFile, ExcelSheet excelSheet, Class<T> clazz) {

		return Reader.of(clazz).from(new File(Constants.getExcelFilePath(excelFile)))
				.sheet(String.valueOf(excelSheet).toLowerCase()).skipHeaderRow(true).list();
	}

}
