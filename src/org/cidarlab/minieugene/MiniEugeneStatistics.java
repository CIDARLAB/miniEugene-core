/*
Copyright (c) 2014 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
 */

package org.cidarlab.minieugene;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.cidarlab.minieugene.stats.Measurement;


/**
 * 
 * @author Ernst Oberortner
 */
public class MiniEugeneStatistics {

	private Set<Measurement> measurements;
	
	public MiniEugeneStatistics() {
		this.measurements = new HashSet<Measurement>();
	}
	
	/**
	 * add/2 adds a measurement (i.e. a key-value pair) to the set of measurements 
	 * of the Measurement object. 
	 * <p>
	 * In general, all measurements are set by miniEugene internally. 
	 * <p>
	 * I'm just a bit too swamped to reorganize the code in order to make the add/2 method private.
	 * <p>
	 * Hence, please ignore this method!
	 */
	public void add(String key, double value) {
		this.measurements.add(new Measurement(key, value));
	}
	
	/**
	 * should be obvious what isEmpty/0 means.
	 * 
	 * @return true or false
	 */
	public boolean isEmpty() {
		return this.measurements.isEmpty();
	}

	/**
	 * getMeasurements/0 returns the set of all measurements measured while 
	 * executing the solve method of miniEugene
	 * 
	 * @return a set of measurements (i.e. key-value pairs) of measurements measured 
	 * during finding compliant solutions to given rules.
	 */
	public Set<Measurement> getMeasurements() {
		return this.measurements;
	}
	
	/**
	 * getValueByKey/1 returns the value (as double) of a given measurement. Possible keys 
	 * are specified in the EugeneConstants class.
	 * 
	 * @param key ... the key of the required measurement
	 * @return a double value of the required measurement
	 */
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
	
	/**
	 * print/0 dumps all measurements to the console (System.out).
	 */
	public void print() {
		System.out.println("**** STATISTICS ****");
		Iterator<Measurement> it = this.measurements.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
}
