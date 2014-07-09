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

package org.cidarlab.minieugene.jetc;

import java.util.HashMap;
import java.util.Map;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.util.SolutionExporter;

public class TemplateDriven {

	private Map<String, Integer> colors;

	public TemplateDriven() {
		
		this.colors = new HashMap<String, Integer>();

		// dark blue NOR gate
		this.colors.put("p1", 2);
		this.colors.put("r1", 2);
		this.colors.put("c1", 2);
		this.colors.put("t1", 2);

		// light blue NOR gate
		this.colors.put("p2", 1);		
		this.colors.put("r2", 1);
		this.colors.put("c2", 1);
		this.colors.put("t2", 1);

		// orange NOR gate
		this.colors.put("p3", 8);		
		this.colors.put("r3", 8);
		this.colors.put("c3", 8);
		this.colors.put("t3", 8);

		// light green NOR gate
		this.colors.put("p4", 3);		
		this.colors.put("r4", 3);
		this.colors.put("c4", 3);
		this.colors.put("t4", 3);

		// dark green NOR gate
		this.colors.put("p5", 4);		
		this.colors.put("r5", 4);
		this.colors.put("c5", 4);
		this.colors.put("t5", 4);

		// INDUCIBLE PROMOTERS
		this.colors.put("pIn0", 14);
		this.colors.put("pIn1", 14);
		this.colors.put("pIn2", 14);
		this.colors.put("p0", 14);

		
		// OUT cassettes
		this.colors.put("rOut0", 10);
		this.colors.put("cOut0", 10);
		this.colors.put("tOut0", 10);
		this.colors.put("rOut1", 6);
		this.colors.put("cOut1", 6);
		this.colors.put("tOut1", 6);

	}
	
	public static void main(String[] args) 
			throws Exception {

		TemplateDriven td = new TemplateDriven();
		
		/**
		// ITERATION 1:
		td.test("iteration1-c1", "N=3. sequence r1, c1, t1. all_forward.");
		td.test("iteration1-c2", "N=3. sequence r2, c2, t2. all_forward.");
		td.test("iteration1-c3", "N=3. sequence r3, c3, t3. all_forward.");
		td.test("iteration1-c4", "N=3. sequence r4, c4, t4. all_forward.");
		td.test("iteration1-c5", "N=3. sequence r5, c5, t5. all_forward.");
		td.test("iteration1-cOut1", "N=3.sequence rOut1, cOut1, tOut1. all_forward.");
		td.test("iteration1-cOut0", "N=3.sequence rOut0, cOut0, tOut0. all_forward.");

		// ITERATION 2:
		// out1 Signal
		td.test("iteration2-out1",
				"N=14."+
				"all_forward." +
				
				// dark blue NOR gate
				"sequence pIn2, pIn1, r1, c1, t1."+
				
				// light blue NOR gate
				"sequence p1, p0, r2, c2, t2. "+

				// out1 reporting device with RFP reporter
				"sequence p2, rOut1, cOut1, tOut1."+

				// input signals of dark blue NOR gate
				"in2 induces pIn2. in1 induces pIn1."+

				// input signals of light blue NOR gate
				"c1 represses p1. 0 induces p0. c2 represses pOut1." +
				
				// input signals of the out1 reporting cassette
				"c2 represses p2.");
				**/

		// out0 Signal
		td.test("iteration2-out0",
				"N=19."+
				"all_forward." +
				
				// orange NOR gate
				"sequence p1, pIn2, r3, c3, t3."+
				
				// light green NOR gate
				"sequence pIn2, pIn0, r4, c4, t4. "+

				"sequence p3, p4, r5, c5, t5."+
				
				// out0 reporting device with GFP reporter
				"sequence p5, rOut0, cOut0, tOut0."+
				
				// induces 
				"in2 induces pIn2. in0 induces pIn0."+
				
				// represses
				"c3 represses p3. c4 represses p4. c5 represses p5.");
		
		/**
		
		// ITERATION 2:
		td.test("iteration3-penc",
				"N=33."+
		
				//*** reusing out1 signal 
				"all_forward." +
				
				// dark blue NOR gate
				"sequence pIn2, pIn1, r1, c1, t1."+
				
				// light blue NOR gate
				"sequence p1, p0, r2, c2, t2. "+

				// out1 reporting device with RFP reporter
				"sequence p2, rOut1, cOut1, tOut1."+

				// input signals of dark blue NOR gate
				"in2 induces pIn2. in1 induces pIn1."+

				// input signals of light blue NOR gate
				"c1 represses p1. 0 induces p0. c2 represses p2." +
				
				// *** reusing out0 signal
				"all_forward." +
				
				// orange NOR gate
				"sequence p1, pIn2, r3, c3, t3."+
				
				// light green NOR gate
				"sequence pIn2, pIn0, r4, c4, t4. "+

				"sequence p3, p4, r5, c5, t5."+
				
				// out0 reporting device with GFP reporter
				"sequence p5, rOut0, cOut0, tOut0."+
				
				// induces 
				// in2 induces pIn2.  <- already specified 
				"in0 induces pIn0."+
				
				// represses
				"c3 represses p3. c4 represses p4. c5 represses p5.");
		***/				
	
	}

	public void test(String name, String script) {
		MiniEugene me = new MiniEugene();				
		long t1 = -1;
		long tProcessing = -1;
		
		try {
			/*
			 * read the file
			 */
			t1 = System.nanoTime();
			
			/*
			 * execute the script
			 */
			me.solve(script);

		} catch(Exception e) {
			e.printStackTrace();
			return;
		}

		me.getStatistics().print();

		if(me.getSolutions() == null || me.getSolutions().isEmpty() ) {
			return;
		}
		
		SolutionExporter se = new SolutionExporter(me.getSolutions(), me.getInteractions());
		try {
			se.pigeonize("./images/acm-jetc/5_"+name+"_1.png", colors, false, 5);
		} catch(Exception e) {
			e.printStackTrace();
		}

		// CONSOLE OUTPUT
		se.toConsole();

		tProcessing = System.nanoTime() - t1;
		System.out.println("[TestSuite.test] full processing time: "+tProcessing*Math.pow(10, -9)+"sec");
		
		//new Eugene(sFile);
	}
	
	
}
