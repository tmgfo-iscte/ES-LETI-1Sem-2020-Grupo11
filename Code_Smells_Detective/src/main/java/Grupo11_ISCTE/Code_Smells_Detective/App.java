package Grupo11_ISCTE.Code_Smells_Detective;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;

/**
 * Hello world!
 *
 */
public class App {
	public static final String XLSX_FILE_PATH = "/Users/marcelopereira/Desktop/Defeitos.xlsx";
	
	public static void main(String[] args) {

//		new FileParser().printFile();
		ArrayList<MethodEntity> array = new FileParser(XLSX_FILE_PATH).scanFileForMethods();
		
		MethodEntity methodEntity = array.get(array.size()-1);
		
		System.out.println(methodEntity.methodID);
		System.out.println(methodEntity.methodName);
		
	}
}
