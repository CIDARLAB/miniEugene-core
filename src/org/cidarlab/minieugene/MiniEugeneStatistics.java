/*
 * Copyright (c) 2014, Boston University
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 *    
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *    
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.minieugene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cidarlab.minieugene.stats.Measurement;


/**
 * 
 * @author Ernst Oberortner
 */
public class MiniEugeneStatistics {

	private List<Measurement> measurements;
	
	public MiniEugeneStatistics() {
		this.measurements = new ArrayList<Measurement>();
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
	public List<Measurement> getMeasurements() {
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
