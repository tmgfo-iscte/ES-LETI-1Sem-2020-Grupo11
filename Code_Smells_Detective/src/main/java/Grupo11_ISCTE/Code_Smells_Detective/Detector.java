package Grupo11_ISCTE.Code_Smells_Detective;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Detector {
	
	
	private final ArrayList<Rule> arrayOfRules;
	
	private final ArrayList<MethodEntity> fileMethods;
	
	 public Detector(String fileToImport, ArrayList<Rule> arrayOfRules) {
		 FileParser fileParser = new FileParser(fileToImport);
		 this.fileMethods = fileParser.scanFileForMethods();
		 this.arrayOfRules = arrayOfRules;
	}
	
	
	
	private ArrayList<MethodEntity> longMethodsLOC(int threshold, boolean isAbove) {
		
		ArrayList<MethodEntity> longMethodsArray = new ArrayList<MethodEntity>(); 
		
		for(MethodEntity method : fileMethods) {
			if(method.getLoc() > threshold && isAbove)
				longMethodsArray.add(method);
			if(method.getLoc() <= threshold && !isAbove)
				longMethodsArray.add(method);
		}
		
		return longMethodsArray;
		
	}
	
	
	
	private ArrayList<MethodEntity> longMethodsCYCLO(int threshold, boolean isAbove) {
		ArrayList<MethodEntity> longMethodsArray = new ArrayList<MethodEntity>(); 
		
		for(MethodEntity method : fileMethods) {
			if(method.getCyclo() > threshold && isAbove)
				longMethodsArray.add(method);
			if(method.getCyclo() <= threshold && !isAbove)
				longMethodsArray.add(method);
		}
		
		return longMethodsArray;
		
	}
	
	private ArrayList<MethodEntity> longMethodsATFD(int threshold, boolean isAbove) {
		ArrayList<MethodEntity> longMethodsArray = new ArrayList<MethodEntity>(); 
		
		for(MethodEntity method : fileMethods) {
			if(method.getAtfd() > threshold && isAbove)
				longMethodsArray.add(method);
			if(method.getAtfd() <= threshold && !isAbove)
				longMethodsArray.add(method);
		}
		
		return longMethodsArray;
		
	}
	
	
	private ArrayList<MethodEntity> longMethodsLAA(float threshold, boolean isAbove) {
		ArrayList<MethodEntity> longMethodsArray = new ArrayList<MethodEntity>(); 
		
		for(MethodEntity method : fileMethods) {
			if(method.getLaa() > threshold && isAbove)
				longMethodsArray.add(method);
			if(method.getLaa() <= threshold && !isAbove)
				longMethodsArray.add(method);
		}
		
		return longMethodsArray;
		
	}
	
	private ArrayList <MethodEntity> aggregateDetections(){
		
		ArrayList<MethodEntity> result = new ArrayList<MethodEntity>();
	
		for(Rule rule: arrayOfRules) {
			if(rule.getIsAndOperator())
				result = (ArrayList<MethodEntity>) ListUtilities.intersection(result, executeRule(rule));
			else
				result = (ArrayList<MethodEntity>) ListUtilities.union(result, executeRule(rule));
		}
		return result;
	}
	
	private void updateMethodEntities(ArrayList <MethodEntity> detections) {
		for(MethodEntity method : detections) {
			method.setOwnDetectorResult(true);
		}
	}
	
	
	public String[][] generateLongMethodData() {
		updateMethodEntities(aggregateDetections());
		String[][] allMethods = new String [fileMethods.size()][4];
		for(int i = 0; i < fileMethods.size(); i++) {
			allMethods[i][0] = fileMethods.get(i).getMethodID();
			allMethods[i][1] = Boolean.toString(fileMethods.get(i).getOwnDetectorResult());
			allMethods[i][2] = Boolean.toString(fileMethods.get(i).getiPlasmaResult());
			allMethods[i][3] = Boolean.toString(fileMethods.get(i).getPmdResult());
		}
		
		return allMethods;
	}
	
	public String[][] generateFeatureEnvyData() {
		updateMethodEntities(aggregateDetections());
		String[][] allMethods = new String [fileMethods.size()][2];
		for(int i = 0; i < fileMethods.size(); i++) {
			allMethods[i][0] = fileMethods.get(i).getMethodID();
			allMethods[i][1] = Boolean.toString(fileMethods.get(i).getOwnDetectorResult());
		}
		
		return allMethods;
	}
	
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
	
	// **********Quality indicators for Own Detector Long Method**********
	public int numberOfDCIOwnDetector() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(method.getOwnDetectorResult() && method.isLongMethod())
				counter++;
		}
		return counter;
	}
	
	public int numberOfDIIOwnDetector() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(method.getOwnDetectorResult() && !method.isLongMethod())
				counter++;
		}
		return counter;
	}
	
	public int numberOfADCIOwnDetector() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(!method.getOwnDetectorResult() && !method.isLongMethod())
				counter++;
		}
		return counter;
	}
	
	public int numberOfADIIOwnDetector() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(!method.getOwnDetectorResult() && method.isLongMethod())
				counter++;
		}
		return counter;
	}
	
	// **********Quality indicators for Own Detector Feature Envy**********
	public int numberOfDCIOwnDetectorFE() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(method.getOwnDetectorResult() && method.isFeatureEnvy())
				counter++;
		}
		return counter;
	}
	
	public int numberOfDIIOwnDetectorFE() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(method.getOwnDetectorResult() && !method.isFeatureEnvy())
				counter++;
		}
		return counter;
	}
	
	public int numberOfADCIOwnDetectorFE() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(!method.getOwnDetectorResult() && !method.isFeatureEnvy())
				counter++;
		}
		return counter;
	}
	
	public int numberOfADIIOwnDetectorFE() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(!method.getOwnDetectorResult() && method.isFeatureEnvy())
				counter++;
		}
		return counter;
	}
	
	// **********Quality indicators for iPlasma**********
	
	public int numberOfDCIiPlasma() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(method.getiPlasmaResult() && method.isLongMethod())
				counter++;
		}
		return counter;
	}
	
	public int numberOfDIIiPlasma() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(method.getiPlasmaResult() && !method.isLongMethod())
				counter++;
		}
		return counter;
	}
	
	public int numberOfADCIiPlasma() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(!method.getiPlasmaResult() && !method.isLongMethod())
				counter++;
		}
		return counter;
	}
	
	public int numberOfADIIiPlasma() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(!method.getiPlasmaResult() && method.isLongMethod())
				counter++;
		}
		return counter;
	}
	
	// **********Quality indicators for PMD**********
	
	public int numberOfDCIPMD() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(method.getPmdResult() && method.isLongMethod())
				counter++;
		}
		return counter;
	}
	
	public int numberOfDIIPMD() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(method.getPmdResult() && !method.isLongMethod())
				counter++;
		}
		return counter;
	}
	
	public int numberOfADCIPMD() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(!method.getPmdResult() && !method.isLongMethod())
				counter++;
		}
		return counter;
	}
	
	public int numberOfADIIPMD() {
		updateMethodEntities(aggregateDetections());
		int counter = 0;
		for(MethodEntity method: fileMethods) {
			if(!method.getPmdResult() && method.isLongMethod())
				counter++;
		}
		return counter;
	}
	
	
	public String[][] generateQualityDataForLongMethod() {
		
		String[][] data = new String [3][5];
		
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
	

public String[][] generateQualityDataForFeatureEnvy() {
		
		String[][] data = new String [1][5];
		
		data[0][0] = "Own Detector";
		data[0][1] = String.valueOf(numberOfDCIOwnDetectorFE());
		data[0][2] = String.valueOf(numberOfDIIOwnDetectorFE());
		data[0][3] = String.valueOf(numberOfADCIOwnDetectorFE());
		data[0][4] = String.valueOf(numberOfADIIOwnDetectorFE());
		
		return data;
	}
	
	
}
