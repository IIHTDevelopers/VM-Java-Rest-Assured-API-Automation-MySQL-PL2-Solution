package coreUtilities.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

public class FileOperations {
	public JSONParser jsonParser;
	public JSONObject jsonObject;
	public Fillo fillo;
	public Connection connection;
	public Properties properties;

	/**
	 * This method is useful to read the excel sheet based on the Filename and sheet
	 * name. It'll return the values for the respective sheet in {@link Map} where
	 * the first column name as a key and the value as per the value entered in
	 * second column.
	 * 
	 * @param excelFilePath - {@link String} excel sheet location
	 * @param sheetName     - {@link String} Sheet name to read the excel
	 * @return {@link Map}
	 * @throws Exception
	 */
	public Map<String, String> readExcelPOI(String excelFilePath, String sheetName) throws Exception {
		// Create a Map to store key-value pairs from the Excel file
		Map<String, String> dataMap = new HashMap<>();

		// Create a FileInputStream object to read the Excel file
		FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));

		// Create a Workbook object using the WorkbookFactory
		Workbook workbook = WorkbookFactory.create(fileInputStream);

		// Get the sheet from the workbook
		Sheet sheet = workbook.getSheet(sheetName);

		// Iterate through each row in the sheet
		for (Row row : sheet) {
			// Assuming the first column contains the key and the second column contains the
			// value
			Cell keyCell = row.getCell(0);
			Cell valueCell = row.getCell(1);

			if (keyCell != null && valueCell != null) {
				// Convert the cell type to STRING if it is not already STRING
				if (keyCell.getCellType() != CellType.STRING) {
					keyCell.setCellType(CellType.STRING);
				}
				if (valueCell.getCellType() != CellType.STRING) {
					valueCell.setCellType(CellType.STRING);
				}

				// Store the key-value pair in the Map
				dataMap.put(keyCell.getStringCellValue(), valueCell.getStringCellValue());
			}
		}

		// Close the workbook and file stream
		workbook.close();
		fileInputStream.close();

		// Return the data map
		return dataMap;
	}

}