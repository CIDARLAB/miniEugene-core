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

package org.cidarlab.minieugene.examples;

import java.util.Arrays;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;

/**
 * In this example we demonstrate how to utilize miniEugene in order 
 * to find ALL solutions. 
 * <p>
 * In general, the utilization of miniEugene is divided into the following steps:<br/>
 * 1. instantiate MiniEugene<br/>
 * 2. specify an String array, each row containing a miniEugene rule<br/>
 * 3. let miniEugene solve the rules (using MiniEugene's solve() method)<br/>
 * 4. process the solutions (maybe by using the SolutionExporter)<br/>
 *    and/or have a look into the statistics of the solving process<br/>
 *
 * @author Ernst Oberortner
 *
 */
public class Example01 {

	public static void main(String[] args) {
		/*
		 * STEP 1 
		 */
		MiniEugene me = new MiniEugene();
		
		/*
		 * STEP 2
		 * 
		 */
		String[] rules = {"CONTAINS a", "CONTAINS b"};
		
		try {
		
			/*
			 * STEP 3: 
			 * we let miniEugene solve the constraints
			 * with a given length of the designs of 3
			 */
			me.solve(rules, 3);     

		} catch(MiniEugeneException e) {
			/*
			 * do some exception handling here
			 */
			e.printStackTrace();
		}		
			
		/*
		 * STEP 4.1:
		 * processing the solutions 
		 * just printing them to the console
		 */
		if(null != me.getSolutions()) {
			for(Component[] solution : me.getSolutions()) {
				System.out.println(Arrays.toString(solution));
			}
		}
		
		/*
		 * STEP 4.2:
		 * dump the statistics
		 */
		if(null != me.getStatistics()) {
			me.getStatistics().print();
		}
		
	}
}
