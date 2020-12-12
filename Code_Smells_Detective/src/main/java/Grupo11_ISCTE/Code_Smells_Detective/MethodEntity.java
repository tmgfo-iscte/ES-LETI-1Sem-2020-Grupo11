package Grupo11_ISCTE.Code_Smells_Detective;

import java.util.ArrayList;

/** Represents the Method Entity.
* The method entity is a class that when instanced has the several fields of data present in the Excel file for a single method.
* @author Marcelo Pereira
* @version 1.0
* @since 1.0
*/

public class MethodEntity {

	/**
	 * The following attributes represent all the field of a method that are present in the Excel File. 
	*/
	
	private String methodID;
	private String belongsToPack;
	private String belongsToClass;
	private String methodName;
	private int loc;
	private int cyclo;
	private int atfd;
	private float laa;
	private boolean isLongMethod;
	private boolean iPlasmaResult;
	private boolean pmdResult;
	private boolean isFeatureEnvy;
	private boolean ownDetectorResult;
	
	/** Class Constructor.
	* Receives an ArrayList with the data found in the Excel File for a single Method
	* and creates an instance of the Method Entity with that data.
	* 
	* @param data The ArrayList with all the data from the Excel File for a single method.
	*/
	
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
		ownDetectorResult = false;
	
	}
	
	/** 
	* This method is used to translate VERDADEIRO(Portuguese) and TRUE(English) to the appropriate boolean value.
	* @param string with the value of the Excel Field
	* @return a boolean.
	*/
	
	public static boolean translateBoolean(String string) {
		
		return (string.equals("VERDADEIRO") || string.equals("TRUE")); 	
		
	}

	/**
	 * This method return the package to which the method belongs
	 * @return a String.
	 */

	public String getBelongsToPack() {
		return belongsToPack;
	}

	/**
	 * This method return the class to which the method belongs
	 * @return String.
	 */

	public String getBelongsToClass() {
		return belongsToClass;
	}

	/**
	* This method get the method identification.
	* @return a String.
	*/

	public String getMethodName() {
		return methodName;
	}

	/**
	 * This method get the LOC value.
	 * @return a int.
	 */

	public int getLoc() {
		return loc;
	}

	/**
	 * This method get the CYCLO value.
	 * @return a int. 
	 */

	public int getCyclo() {
		return cyclo;
	}

	/**
	 * This method get the ATFD value.
	 * @return a int.
	 */

	public int getAtfd() {
		return atfd;
	}

	/**
	 * This method get the LAA value.
	 * @return a float.
	 */

	public float getLaa() {
		return laa;
	}

	/**
	* This method gets the response for the question "is a Long Method?".
	* @return a boolean.
	*/

	public boolean isLongMethod() {
		return isLongMethod;
	}

	/**
	* This method gets the response for the question "is a Feature Envy?".
	* @return a boolean.
	*/

	public boolean isFeatureEnvy() {
		return isFeatureEnvy;
	}

	/**
	* This method gets the method identification.
	* @return a String.
	*/

	public String getMethodID() {
		return methodID;
	}

	/**
	* This method gets the response for the question "iPlasma result?".
	* @return a boolean.
	*/

	public boolean getiPlasmaResult() {
		return iPlasmaResult;
	}

	/**
	* This method gets the response for the question "pmd result?".
	* @return a boolean.
	*/

	public boolean getPmdResult() {
		return pmdResult;
	}

	/**
	* This method gets the response for the question "ownDetector result?".
	* @return a boolean.
	*/

	public boolean getOwnDetectorResult() {
		return ownDetectorResult;
	}

	/**
	* This method sets the ownDetector result.
	* @param ownDetectorResult.
	*/

	public void setOwnDetectorResult(boolean ownDetectorResult) {
		this.ownDetectorResult = ownDetectorResult;
	}

	/** 
	 * 
	 * @return a String
	 */

	@Override
	public String toString() {
		return "MethodEntity [methodID=" + methodID + "]";
	}
	
	
	
}
