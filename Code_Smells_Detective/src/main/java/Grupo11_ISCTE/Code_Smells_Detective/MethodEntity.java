package Grupo11_ISCTE.Code_Smells_Detective;

import java.util.ArrayList;

public class MethodEntity {

	String methodID;
	String belongsToPack;
	String belongsToClass;
	String methodName;
	int loc;
	int cyclo;
	int atfd;
	float laa;
	boolean isLongMethod;
	boolean iPlasmaResult;
	boolean pmdResult;
	boolean isFeatureEnvy;
	
	
	public MethodEntity(ArrayList<String> data) {
	
		methodID = data.get(0);
		belongsToPack = data.get(1);
		belongsToClass = data.get(2);
		methodName = data.get(3);
		loc = Integer.parseInt(data.get(4));
		cyclo = Integer.parseInt(data.get(5));
		atfd = Integer.parseInt(data.get(6));
		laa = Float.parseFloat(data.get(7));
		isLongMethod = translateBoolean(data.get(8));
		iPlasmaResult = translateBoolean(data.get(9));
		pmdResult = translateBoolean(data.get(10));
		isFeatureEnvy = translateBoolean(data.get(11));
	
	}
	
	
	
	public static boolean translateBoolean(String string) {
		
		return (string.equals("VERDADEIRO") || string.equals("TRUE")); 	
		
	}
	
	
	
}
