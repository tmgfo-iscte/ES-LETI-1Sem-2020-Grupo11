package Grupo11_ISCTE.Code_Smells_Detective;

/** Represents the rules for the Defects Detection.
* This rules will be used according with what will be the input in the GUI.
* @author Teresa Felício
* @author Marcelo Pereira
* @version 1.2
* @since 1.0
*/

public class Rule {

	/** Represents variables whose value can't be modified.
	*/
	
	private final boolean isAndOperator;
	private final String metric;
	private final float threshold;
	private final boolean isAbove;
	

	/** Class Constructor.
	* Creates a Rule with the specified variables.
	* @param isAndOperator Answer to the question "Is AND operator?".
	* @param metric The metric name.
	* @param threshold The threshold value.
	* @param isAbove Answer to the question "Is above?".
	*/
	
	public Rule(boolean isAndOperator, String metric, float threshold, boolean isAbove) {
		this.isAndOperator = isAndOperator;
		this.metric = metric;
		this.threshold = threshold;
		this.isAbove = isAbove;
	}

	/**
	* This method get the response for the question "Is AND operator?".
	* @return a boolean.
	*/
	
	public boolean getIsAndOperator() {
		return isAndOperator;
	}

	/**
	* This method get the metrics name.
	* @return a String.
	*/

	public String getMetric() {
		return metric;
	}

	/**
	* This method get the threshold value.
	* @return a float.
	*/

	public float getThreshold() {
		return threshold;
	}

	/**
	* This method get the response for the question "Is above?".
	* @return a boolean.
	*/

	public boolean isAbove() {
		return isAbove;
	}
	
	
	
}
