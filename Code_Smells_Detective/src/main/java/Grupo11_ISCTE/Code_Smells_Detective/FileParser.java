package Grupo11_ISCTE.Code_Smells_Detective;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileParser {

	public static final String XLSX_FILE_PATH = "/Users/marcelopereira/Desktop/Defeitos.xlsx";
	
	
	public void printFile(){
		
		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(new File(XLSX_FILE_PATH));
			Sheet sheet = workbook.getSheetAt(0);

			DataFormatter dataFormatter = new DataFormatter();

			for (Row row : sheet) {
				for (Cell cell : row) {
					String cellValue = dataFormatter.formatCellValue(cell);
					System.out.print(StringUtilities.fitStringToLength(cellValue,20) + "\t\t");
				}

				System.out.println();
			}
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
}
