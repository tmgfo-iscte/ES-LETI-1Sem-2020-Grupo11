package Grupo11_ISCTE.Code_Smells_Detective;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/** Represents the File Parser.
* This File Parser (...).
* @author Marcelo Pereira
* @version 1.0
* @since 1.0
*/

public class FileParser {

	/**
	*/
	
	private Workbook workbook;
	private Sheet sheet;
	private DataFormatter dataFormatter;
	
	/** Class Constructor.
	* Creates a File Parser
	* and (...)
	* @filePath The file path the user wants to use.
	* @exception EncryptedDocumentException if can't process encrypted word file.
	* @exception IOException if the user is trying to read a file that doesn't exist (On input error).
	*/
	
	public FileParser(String filePath) {
		try {
			workbook = WorkbookFactory.create(new File(filePath));
			sheet = workbook.getSheetAt(0);
			dataFormatter = new DataFormatter();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	* This method scans the file in order to take the methods.
	* (...)
	* @return a ArrayList<MethodEntity>.
	*/
		
	public ArrayList<MethodEntity> scanFileForMethods() {

		ArrayList<MethodEntity> allMethods = new ArrayList<MethodEntity>();
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			ArrayList<String> rowData= new ArrayList<String>();
			for (Cell cell : sheet.getRow(i)) {
				String cellValue = dataFormatter.formatCellValue(cell);
				rowData.add(cellValue);
			}

			MethodEntity methodEntity = new MethodEntity(rowData);
			allMethods.add(methodEntity);
		}
		
		return allMethods;
	}
	

	/**
	* This method contains all the data distributed in a matrix
	* that was originally in the variable sheet.
	* (...)
	* @return a String matrix.
	*/
	
	public String[][] dataArray() {
		
		String allRows[][] = new String[sheet.getLastRowNum()][sheet.getRow(1).getLastCellNum()];
		
		for (int i = 1; i < sheet.getLastRowNum()+1; i++) {
			String rowData[]= new String[sheet.getRow(i).getLastCellNum()];
			for (int j = 0; j < sheet.getRow(i).getLastCellNum();  j++) {
				Cell cell = sheet.getRow(i).getCell(j);
				String cellValue = dataFormatter.formatCellValue(cell);
				rowData[j]=cellValue;
			}

			allRows[i-1]= rowData;
		}
		
		return allRows;
	
	}
	
	/**
	* This method puts the column names in an array.
	* (...)
	* @return a String array.
	*/
	
	public String[] columnNamesArray() {
		
		String[] columnNames = new String[sheet.getRow(0).getLastCellNum()];
		
		for (int j = 0; j < sheet.getRow(0).getLastCellNum();  j++) {
			Cell cell = sheet.getRow(0).getCell(j);
			String cellValue = dataFormatter.formatCellValue(cell);
			columnNames[j] = cellValue;
		}
		return columnNames;
		
	}
	
}
