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

public class FileParser {

	private Workbook workbook;
	private Sheet sheet;
	private DataFormatter dataFormatter;
	
	
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
	
	
	public void printFile(){
		
			for (Row row : sheet) {
				for (Cell cell : row) {
					String cellValue = dataFormatter.formatCellValue(cell);
					System.out.print(StringUtilities.fitStringToLength(cellValue,20) + "\t\t");
				}

				System.out.println();
			}
	}
	
	
	
	
	
	
	
	
	
}
