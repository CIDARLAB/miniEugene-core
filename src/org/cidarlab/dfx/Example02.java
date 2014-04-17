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

package org.cidarlab.dfx;

import org.cidarlab.minieugene.MiniEugene;

/**
 * In this example we demonstrate how to utilize miniEugene in order 
 * to find a subset of all solutions. The user needs to provide the 
 * number (>0) of desired solutions. 
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
public class Example02 {

	public static void main(String[] args) {
		/*
		 * first, instantiate miniEugene
		 */
		MiniEugene me = new MiniEugene();

		/*
		 * then, define some rules 
		 * (in a String array)
		 */
		String[] rules = {
				"CONTAINS a", "CONTAINS b", "CONTAINS c", 
				"CONTAINS d", "CONTAINS e", "CONTAINS f", 
				"CONTAINS g", "CONTAINS h", "CONTAINS i",
				"CONTAINS j", "CONTAINS k", "CONTAINS l", 
				"a BEFORE b", "b BEFORE c", "c BEFORE d",
				"d BEFORE e", "e BEFORE f", "f BEFORE g",
				"g BEFORE h", "h BEFORE i", "i BEFORE j",
				"j BEFORE k", "k BEFORE l"};
		try {

			/*
			 * let miniEugene solve the problem
			 * N=12 and we'd like to have 20 solutions
			 */
			me.solve(rules, 12, 20);

		} catch(Exception e) {
			e.printStackTrace();
		}		

		/*
		 * print the number of solutions
		 * (should be equal to 20)
		 */
		if(null != me.getSolutions()) {
			System.out.println("Number of Solutions: "+me.getSolutions().size());
//                        first = me.getSolutions().get(0)[0];
                        System.out.println("Solution 0: " + me.getSolutions().get(0)[2].getType());
		}

		/*
		 * dump the statistics
		 */
		if(null != me.getStatistics()) {
			me.getStatistics().print();
		}

	}

}
