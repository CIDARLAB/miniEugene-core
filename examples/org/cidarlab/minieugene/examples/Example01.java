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

package org.cidarlab.minieugene.examples;

import java.util.Arrays;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;

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

		} catch(EugeneException e) {
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
