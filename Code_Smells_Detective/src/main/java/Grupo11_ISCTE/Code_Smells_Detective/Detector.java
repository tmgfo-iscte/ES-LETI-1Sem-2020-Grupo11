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
	
	
	
	private ArrayList<MethodEntity> longMethodsLOC(int threshold) {
		
		ArrayList<MethodEntity> longMethodsArray = new ArrayList<MethodEntity>(); 
		
		for(MethodEntity method : fileMethods) {
			if(method.loc > threshold)
				longMethodsArray.add(method);
		}
		
		return longMethodsArray;
		
	}
	
	
	
	private ArrayList<MethodEntity> longMethodsCYCLO(int threshold) {
		ArrayList<MethodEntity> longMethodsArray = new ArrayList<MethodEntity>(); 
		
		for(MethodEntity method : fileMethods) {
			if(method.cyclo > threshold)
				longMethodsArray.add(method);
		}
		
		return longMethodsArray;
		
	}
	
	private ArrayList<MethodEntity> longMethodsATFD(int threshold) {
		ArrayList<MethodEntity> longMethodsArray = new ArrayList<MethodEntity>(); 
		
		for(MethodEntity method : fileMethods) {
			if(method.atfd > threshold)
				longMethodsArray.add(method);
		}
		
		return longMethodsArray;
		
	}
	
	
	private ArrayList<MethodEntity> longMethodsLAA(float threshold) {
		ArrayList<MethodEntity> longMethodsArray = new ArrayList<MethodEntity>(); 
		
		for(MethodEntity method : fileMethods) {
			if(method.laa > threshold)
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
	
	
	public String[][] generateData() {
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
	
	
	private ArrayList <MethodEntity> executeRule(Rule rule) {
		
		if(rule.getMetric().equals("LOC"))
			return longMethodsLOC((int)rule.getThreshold());
		
		if(rule.getMetric().equals("CYCLO"))
			return longMethodsCYCLO((int)rule.getThreshold());
		
		if(rule.getMetric().equals("ATFD"))
			return longMethodsATFD((int)rule.getThreshold());
	
		if(rule.getMetric().equals("LAA"))
			return longMethodsLAA(rule.getThreshold());
		
		return null;
	}
	
	

}
