package Grupo11_ISCTE.Code_Smells_Detective;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestFileParser {

	@Test
	void testDataArray() {
		
		FileParser parser = new FileParser("/Users/marcelopereira/Desktop/Defeitos.xlsx");
		
		String[][] output = parser.dataArray();
		
		assertEquals("Output()",output[0][3]);
		
		
	}
	
	@Test
	void testColumnNames() {
		
		FileParser parser = new FileParser("/Users/marcelopereira/Desktop/Defeitos.xlsx");
		
		String[] output = parser.columnNamesArray();
		
		assertEquals("is_long_method", output[8]);
		
	}
	
	@Test
	void testScanFileForMethods() {
		FileParser parser = new FileParser("/Users/marcelopereira/Desktop/Defeitos.xlsx");
		
		ArrayList<MethodEntity> methods = parser.scanFileForMethods();
		
		assertEquals("21", methods.get(20).getMethodID());
		assertEquals("org.jfin.date.web.example", methods.get(20).getBelongsToPack());
		assertEquals("PlainSwap", methods.get(20).getBelongsToClass());
		assertEquals("getCurrency()", methods.get(20).getMethodName());
		assertEquals(4, methods.get(20).getLoc());
		assertEquals(1, methods.get(20).getCyclo());
		assertEquals(0, methods.get(20).getAtfd());
		assertEquals(1, methods.get(20).getLaa());
		assertEquals(false, methods.get(20).isLongMethod());
		assertEquals(false, methods.get(20).getiPlasmaResult());
		assertEquals(false, methods.get(20).getPmdResult());
		assertEquals(false, methods.get(20).isFeatureEnvy());
		assertEquals(false, methods.get(20).getOwnDetectorResult());
		assertEquals("MethodEntity [methodID=21]", methods.get(20).toString());
		
		methods.get(20).setOwnDetectorResult(true);
		assertEquals(true, methods.get(20).getOwnDetectorResult());
		
	}

}
