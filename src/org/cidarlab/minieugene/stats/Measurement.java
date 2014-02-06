package org.cidarlab.minieugene.stats;

/**
 * The Measurement class defines key-value pairs of measurements during the 
 * execution of finding rule-compliant solutions. 
 * <p>
 * In general, the Measurement class is only relevant for internal usage.
 * As a user of the embedded jar, you can ignore this class.
 * 
 * @author Ernst Oberortner
 */
public class Measurement {

	private String key;
	private double value;
	
	public Measurement(String key, double value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return this.key;
	}
	public double getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.key+": "+this.value;
	}
}
