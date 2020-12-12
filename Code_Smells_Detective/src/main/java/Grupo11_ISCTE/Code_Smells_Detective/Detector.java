package Grupo11_ISCTE.Code_Smells_Detective;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the Code Smells Detector. The Detector offers several methods to
 * find code smells and calculate quality indicators of the detections made.
 * 
 * @author Marcelo Pereira
 * @version 3.0
 * @since 1.0
 */

public class Detector {

	/**
	 * Represents variables whose value can't be modified. Represents an ArrayList
	 * of rules whose type is Rule and an ArrayList of method entities whose type is
	 * MethodEntity.
	 */

	private final ArrayList<Rule> arrayOfRules;
	private final ArrayList<MethodEntity> fileMethods;

	/**
	 * Class Constructor. Creates a Detector with the specified File to Import and
	 * an Array of Rules. Also creates a File Parser.
	 * 
	 * @param fileToImport The name of the file to import.
	 * @param arrayOfRules The array of rules.
	 */

	public Detector(String fileToImport, ArrayList<Rule> arrayOfRules) {
		FileParser fileParser = new FileParser(fileToImport);
		this.fileMethods = fileParser.scanFileForMethods();
		this.arrayOfRules = arrayOfRules;
	}

	/**
	 * This method detects methods that suffer from the Code Smell 'Long Method',
	 * using the metric 'LOC' (Lines Of Code). The detections are combined in an
	 * ArrayList of Method Entities.
	 * 
	 * @param threshold is the limit value of the metric.
	 * @param isAbove is true if the user wants to detect smells above the threshold and is false otherwise.
	 * @return an ArrayList with the methods detected.
	 */

	private ArrayList<MethodEntity> longMethodsLOC(int threshold, boolean isAbove) {

		ArrayList<MethodEntity> longMethodsArray = new ArrayList<MethodEntity>();

		for (MethodEntity method : fileMethods) {
			if (method.getLoc() > threshold && isAbove)
				longMethodsArray.add(method);
			if (method.getLoc() <= threshold && !isAbove)
				longMethodsArray.add(method);
		}

		return longMethodsArray;

	}

	/**
	 * This method detects methods that suffer from the Code Smell 'Long Method',
	 * using the metric 'CYCLO' (Cyclomatic Complexity). The detections are combined in an
	 * ArrayList of Method Entities.
	 * 
	 * @param threshold the limit value of the metric.
	 * @param isAbove is true if the user wants to detect smells above the threshold and is false otherwise.
	 * @return an ArrayList with the methods detected.
	 */

	private ArrayList<MethodEntity> longMethodsCYCLO(int threshold, boolean isAbove) {
		ArrayList<MethodEntity> longMethodsArray = new ArrayList<MethodEntity>();

		for (MethodEntity method : fileMethods) {
			if (method.getCyclo() > threshold && isAbove)
				longMethodsArray.add(method);
			if (method.getCyclo() <= threshold && !isAbove)
				longMethodsArray.add(method);
		}

		return longMethodsArray;

	}

	/**
	 * This method detects methods that suffer from the Code Smell 'Long Method',
	 * using the metric 'ATFD' (Access to foreign data). The detections are combined in an
	 * ArrayList of Method Entities.
	 * 
	 * @param threshold the limit value of the metric.
	 * @param isAbove is true if the user wants to detect smells above the threshold and is false otherwise.
	 * @return an ArrayList with the methods detected.
	 */

	private ArrayList<MethodEntity> longMethodsATFD(int threshold, boolean isAbove) {
		ArrayList<MethodEntity> longMethodsArray = new ArrayList<MethodEntity>();

		for (MethodEntity method : fileMethods) {
			if (method.getAtfd() > threshold && isAbove)
				longMethodsArray.add(method);
			if (method.getAtfd() <= threshold && !isAbove)
				longMethodsArray.add(method);
		}

		return longMethodsArray;

	}

	/**
	 * This method detects methods that suffer from the Code Smell 'Long Method',
	 * using the metric 'LAA' (Locality of Attribute Accesses). The detections are combined in an
	 * ArrayList of Method Entities.
	 * 
	 * @param threshold the limit value of the metric.
	 * @param isAbove is true if the user wants to detect smells above the threshold and is false otherwise.
	 * @return an ArrayList with the methods detected.
	 */

	private ArrayList<MethodEntity> longMethodsLAA(float threshold, boolean isAbove) {
		ArrayList<MethodEntity> longMethodsArray = new ArrayList<MethodEntity>();

		for (MethodEntity method : fileMethods) {
			if (method.getLaa() > threshold && isAbove)
				longMethodsArray.add(method);
			if (method.getLaa() <= threshold && !isAbove)
				longMethodsArray.add(method);
		}

		return longMethodsArray;

	}

	/**
	 * This method aggregates the Code Smells Detections into a single ArrayList.
	 * 
	 * @return an ArrayList with the methods detected.
	 */

	private ArrayList<MethodEntity> aggregateDetections() {

		ArrayList<MethodEntity> result = new ArrayList<MethodEntity>();

		for (Rule rule : arrayOfRules) {
			if (rule.getIsAndOperator())
				result = (ArrayList<MethodEntity>) ListUtilities.intersection(result, executeRule(rule));
			else
				result = (ArrayList<MethodEntity>) ListUtilities.union(result, executeRule(rule));
		}
		return result;
	}

	/**
	 * This method updates Method Entities with the result of the detections made 
	 * 
	 * @param detections.
	 * @return nothing.
	 */

	private void updateMethodEntities(ArrayList<MethodEntity> detections) {
		for (MethodEntity method : detections) {
			method.setOwnDetectorResult(true);
		}
	}

	/**
	 * This method generates a String matrix with the results of the detections of 'Long Method' for the three tools.
	 * 
	 * @return a String matrix.
	 */

	public String[][] generateLongMethodData() {
		updateMethodEntities(aggregateDetections());
		String[][] allMethods = new String[fileMethods.size()][4];
		for (int i = 0; i < fileMethods.size(); i++) {
			allMethods[i][0] = fileMethods.get(i).getMethodID();
			allMethods[i][1] = Boolean.toString(fileMethods.get(i).getOwnDetectorResult());
			allMethods[i][2] = Boolean.toString(fileMethods.get(i).getiPlasmaResult());
			allMethods[i][3] = Boolean.toString(fileMethods.get(i).getPmdResult());
		}

		return allMethods;
	}

	/**
	 * This method generates a String matrix with the results of 'Feature Envy' data for the own detector.
	 * 
	 * @return a String matrix.
	 */

	public String[][] generateFeatureEnvyData() {
		updateMethodEntities(aggregateDetections());
		String[][] allMethods = new String[fileMethods.size()][2];
		for (int i = 0; i < fileMethods.size(); i++) {
			allMethods[i][0] = fileMethods.get(i).getMethodID();
			allMethods[i][1] = Boolean.toString(fileMethods.get(i).getOwnDetectorResult());
		}

		return allMethods;
	}

	/**
	 * This method executes the given rule by the user.
	 * 
	 * @param rule
	 * @return an ArrayList with the methods detected.
	 */

	private ArrayList<MethodEntity> executeRule(Rule rule) {

		if (rule.getMetric().equals("LOC"))
			return longMethodsLOC((int) rule.getThreshold(), rule.isAbove());

		if (rule.getMetric().equals("CYCLO"))
			return longMethodsCYCLO((int) rule.getThreshold(), rule.isAbove());

		if (rule.getMetric().equals("ATFD"))
			return longMethodsATFD((int) rule.getThreshold(), rule.isAbove());

		if (rule.getMetric().equals("LAA"))
			return longMethodsLAA(rule.getThreshold(), rule.isAbove());

		return null;
	}

	/**
	 * Quality indicators for Own Detector 'Long Method'.
	 */

	/**
	 * This method gets the number of DCI for OwnDetector 'Long Method'.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfDCIOwnDetector() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (method.getOwnDetectorResult() && method.isLongMethod())
				counter++;
		}
		return counter;
	}

	/**
	 * This method gets the number of DII for OwnDetector 'Long Method'.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfDIIOwnDetector() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (method.getOwnDetectorResult() && !method.isLongMethod())
				counter++;
		}
		return counter;
	}

	/**
	 * This method gets the number of ADCI for OwnDetector 'Long Method'.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfADCIOwnDetector() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (!method.getOwnDetectorResult() && !method.isLongMethod())
				counter++;
		}
		return counter;
	}

	/**
	 * This method gets the number of ADII for OwnDetector 'Long Method'.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfADIIOwnDetector() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (!method.getOwnDetectorResult() && method.isLongMethod())
				counter++;
		}
		return counter;
	}

	/**
	 * Quality indicators for Own Detector 'Feature Envy'.
	 */

	/**
	 * This method gets the number of DCI for OwnDetector 'Feature Envy'.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfDCIOwnDetectorFE() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (method.getOwnDetectorResult() && method.isFeatureEnvy())
				counter++;
		}
		return counter;
	}

	/**
	 * This method gets the number of DII for OwnDetector 'Feature Envy'.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfDIIOwnDetectorFE() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (method.getOwnDetectorResult() && !method.isFeatureEnvy())
				counter++;
		}
		return counter;
	}

	/**
	 * This method gets the number of ADCI for OwnDetector 'Feature Envy'.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfADCIOwnDetectorFE() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (!method.getOwnDetectorResult() && !method.isFeatureEnvy())
				counter++;
		}
		return counter;
	}

	/**
	 * This method gets the number of ADII for OwnDetector 'Feature Envy'.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfADIIOwnDetectorFE() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (!method.getOwnDetectorResult() && method.isFeatureEnvy())
				counter++;
		}
		return counter;
	}

	/**
	 * Quality indicators for iPlasma.
	 */

	/**
	 * This method gets the number of DCI for iPlasma.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfDCIiPlasma() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (method.getiPlasmaResult() && method.isLongMethod())
				counter++;
		}
		return counter;
	}

	/**
	 * This method gets the number of DII for iPlasma.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfDIIiPlasma() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (method.getiPlasmaResult() && !method.isLongMethod())
				counter++;
		}
		return counter;
	}

	/**
	 * This method gets the number of ADCI for iPlasma.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfADCIiPlasma() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (!method.getiPlasmaResult() && !method.isLongMethod())
				counter++;
		}
		return counter;
	}

	/**
	 * This method gets the number of ADII for iPlasma.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfADIIiPlasma() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (!method.getiPlasmaResult() && method.isLongMethod())
				counter++;
		}
		return counter;
	}

	/**
	 * Quality indicators for PMD.
	 */

	/**
	 * This method gets the number of DCI for PMD.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfDCIPMD() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (method.getPmdResult() && method.isLongMethod())
				counter++;
		}
		return counter;
	}

	/**
	 * This method gets the number of DII for PMD.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfDIIPMD() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (method.getPmdResult() && !method.isLongMethod())
				counter++;
		}
		return counter;
	}

	/**
	 * This method gets the number of ADCI for PMD.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfADCIPMD() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (!method.getPmdResult() && !method.isLongMethod())
				counter++;
		}
		return counter;
	}

	/**
	 * This method gets the number of ADII for PMD.
	 * 
	 * @return an int that represents the counter.
	 */

	public int numberOfADIIPMD() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for (MethodEntity method : fileMethods) {
			if (!method.getPmdResult() && method.isLongMethod())
				counter++;
		}
		return counter;
	}

	/**
	 * This method generates the quality data for the Code Smell 'Long Method'.
	 * 
	 * @return a String matrix
	 */

	public String[][] generateQualityDataForLongMethod() {

		String[][] data = new String[3][5];

		data[0][0] = "Own Detector";
		data[0][1] = String.valueOf(numberOfDCIOwnDetector());
		data[0][2] = String.valueOf(numberOfDIIOwnDetector());
		data[0][3] = String.valueOf(numberOfADCIOwnDetector());
		data[0][4] = String.valueOf(numberOfADIIOwnDetector());

		data[1][0] = "iPlasma";
		data[1][1] = String.valueOf(numberOfDCIiPlasma());
		data[1][2] = String.valueOf(numberOfDIIiPlasma());
		data[1][3] = String.valueOf(numberOfADCIiPlasma());
		data[1][4] = String.valueOf(numberOfADIIiPlasma());

		data[2][0] = "PMD";
		data[2][1] = String.valueOf(numberOfDCIPMD());
		data[2][2] = String.valueOf(numberOfDIIPMD());
		data[2][3] = String.valueOf(numberOfADCIPMD());
		data[2][4] = String.valueOf(numberOfADIIPMD());

		return data;
	}

	/**
	 * This method generates the quality data for the Code Smell 'Feature Envy'.
	 * 
	 * @return a String matrix
	 */

	public String[][] generateQualityDataForFeatureEnvy() {

		String[][] data = new String[1][5];

		data[0][0] = "Own Detector";
		data[0][1] = String.valueOf(numberOfDCIOwnDetectorFE());
		data[0][2] = String.valueOf(numberOfDIIOwnDetectorFE());
		data[0][3] = String.valueOf(numberOfADCIOwnDetectorFE());
		data[0][4] = String.valueOf(numberOfADIIOwnDetectorFE());

		return data;
	}

}
