package org.cidarlab.minieugene;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.cidarlab.minieugene.stats.Measurement;

public class MiniEugeneStatistics {

	private Set<Measurement> measurements;
	
	public MiniEugeneStatistics() {
		this.measurements = new HashSet<Measurement>();
	}
	
	public void add(String key, double value) {
		this.measurements.add(new Measurement(key, value));
	}
	
	public boolean isEmpty() {
		return this.measurements.isEmpty();
	}

	public Set<Measurement> getMeasurements() {
		return this.measurements;
	}
	
	public double getValueByKey(String key) {
		if(null != key) {
			for(Measurement m : measurements) {
				if(key.equals(m.getKey())) {
					return m.getValue();
				}
			}
		}
		return -1;
	}
	
	public void print() {
		System.out.println("**** STATISTICS ****");
		Iterator<Measurement> it = this.measurements.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
}
