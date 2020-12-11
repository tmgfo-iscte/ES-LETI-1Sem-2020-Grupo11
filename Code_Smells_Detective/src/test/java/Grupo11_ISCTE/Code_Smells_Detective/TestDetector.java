package Grupo11_ISCTE.Code_Smells_Detective;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestDetector {

	@Test
	void testGenerateQualityDataForLongMethod() {

		String filePath = "/Users/marcelopereira/Desktop/Defeitos.xlsx";

		Rule rule1 = new Rule(false, "LOC", 400, true);
		Rule rule2 = new Rule(true, "CYCLO", 50, true);

		ArrayList<Rule> rules = new ArrayList<Rule>();

		rules.add(rule1);
		rules.add(rule2);

		Detector detector = new Detector(filePath, rules);

		String[][] output = detector.generateQualityDataForLongMethod();

		assertEquals("Own Detector", output[0][0]);
		assertEquals("9", output[0][1]);
		assertEquals("131", output[0][4]);
		assertEquals("140", output[2][1]);
		assertEquals("18", output[2][2]);
		assertEquals("262", output[2][3]);
	}

	@Test
	void testGenerateQualityDataForFeatureEnvy() {

		String filePath = "/Users/marcelopereira/Desktop/Defeitos.xlsx";

		Rule rule1 = new Rule(false, "ATFD", 4, true);
		Rule rule2 = new Rule(true, "LAA", (float) 0.42, false);

		ArrayList<Rule> rules = new ArrayList<Rule>();

		rules.add(rule1);
		rules.add(rule2);

		Detector detector = new Detector(filePath, rules);

		String[][] output = detector.generateQualityDataForFeatureEnvy();

		assertEquals("Own Detector", output[0][0]);
		assertEquals("112", output[0][1]);
		assertEquals("0", output[0][2]);
		assertEquals("306", output[0][3]);
		assertEquals("2", output[0][4]);
	}

	@Test
	void testGenerateLongMethodData() {

		String filePath = "/Users/marcelopereira/Desktop/Defeitos.xlsx";

		Rule rule1 = new Rule(false, "LOC", 400, true);
		Rule rule2 = new Rule(true, "CYCLO", 50, true);

		ArrayList<Rule> rules = new ArrayList<Rule>();

		rules.add(rule1);
		rules.add(rule2);

		Detector detector = new Detector(filePath, rules);
		
		String[][] output = detector.generateLongMethodData();
		
		assertEquals("14", output[13][0]);
		assertEquals("true", output[13][1]);
	}

	@Test
	void testGenerateFeatureEnvyData() {
		
		String filePath = "/Users/marcelopereira/Desktop/Defeitos.xlsx";

		Rule rule1 = new Rule(false, "ATFD", 4, true);
		Rule rule2 = new Rule(true, "LAA", (float) 0.42, false);

		ArrayList<Rule> rules = new ArrayList<Rule>();

		rules.add(rule1);
		rules.add(rule2);

		Detector detector = new Detector(filePath, rules);
		String[][] output = detector.generateFeatureEnvyData();
		
		assertEquals("7", output[6][0]);
		assertEquals("true", output[6][1]);
		
	}
	
	
}
