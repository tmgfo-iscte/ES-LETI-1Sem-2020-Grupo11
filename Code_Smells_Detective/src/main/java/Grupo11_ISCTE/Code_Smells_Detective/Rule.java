package Grupo11_ISCTE.Code_Smells_Detective;

public class Rule {

	private final boolean isAndOperator;
	private final String metric;
	private final float threshold;
	
	
	public Rule(boolean isAndOperator, String metric, float threshold) {
		this.isAndOperator = isAndOperator;
		this.metric = metric;
		this.threshold = threshold;
	}


	public boolean getIsAndOperator() {
		return isAndOperator;
	}


	public String getMetric() {
		return metric;
	}


	public float getThreshold() {
		return threshold;
	}
	
}