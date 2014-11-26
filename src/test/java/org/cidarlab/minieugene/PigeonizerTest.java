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

import org.cidarlab.minieugene.data.pigeon.WeyekinPoster;
import org.cidarlab.minieugene.util.SolutionExporter;

public class PigeonizerTest {

	public static void main(String[] args) {

//		if(!testPrimitives()) {
//			System.exit(1);
//		}
		
		if(!testInteractions()) {
			System.exit(2);
		}
	}
	
	private static boolean testPrimitives() {
		MiniEugene me = new MiniEugene();

		try {
			me.solve("N=1. contains p.");			
			SolutionExporter se = new SolutionExporter(me.getSolutions(), me.getInteractions());
			WeyekinPoster.launchPage(se.toPigeon());
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		// forward oriented invertase site
		try {
			me.solve("N=1. contains is.");
			
			SolutionExporter se = new SolutionExporter(me.getSolutions(), me.getInteractions());
			WeyekinPoster.launchPage(se.toPigeon());
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		// in the last test case, we try to rebuild 
		// a subset of the pigeon glyphs presented on the pigeoncad.org web site
		try {
			me.solve("N=14. "+
					// unknown part type
					"contains unknown. forward unknown. "+
					// promoters
					"contains p1. forward p1. contains p2. reverse p2. "+
					// RBSs
					"contains r1. forward r1. contains r2. reverse r2. "+
					// CDSs
					"contains c1. forward c1. contains c2. reverse c2. "+
					// Genes
					"contains g1. forward g1. contains g2. reverse g2. "+
					
					// Terminators
					"contains t1. forward t1. contains t2. reverse t2. "+
					// Invertase Sites
					"contains is1. forward is1. contains is2. reverse is2. "+
					// Spacers
					"contains sp1. forward sp1. "+
					// and finally, positioning
					"unknown before p1. p1 before p2. p2 before r1. r1 before r2. "+
					"r2 before c1. c1 before c2. c2 before g1. g1 before g2. "+
					"g2 before t1. t1 before t2. t2 before is1. is1 before is2."+
					"is2 before sp1.");
			
			SolutionExporter se = new SolutionExporter(me.getSolutions(), me.getInteractions());
			WeyekinPoster.launchPage(se.toPigeon());
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private static boolean testInteractions() {
		MiniEugene me = new MiniEugene();
		
		// NEGATIVE REPRESSION
		try {
			me.solve("N=2. contains c. contains p. c represses p.");
//			System.out.println(me.getInteractions());

			SolutionExporter se = new SolutionExporter(me.getSolutions(), me.getInteractions());
			WeyekinPoster.launchPage(se.toPigeon());
		} catch(Exception e) {
			e.printStackTrace();
		}		

		// POSITIVE INDUCTION
		try {
			me.solve("N=2. contains c. contains p. c induces p.");
//			System.out.println(me.getInteractions());

			SolutionExporter se = new SolutionExporter(me.getSolutions(), me.getInteractions());
			WeyekinPoster.launchPage(se.toPigeon());
		} catch(Exception e) {
			e.printStackTrace();
		}		

		// INDUCTION
		try {
			me.solve("N=1. contains ipIn1. in1 induces ipIn1.");
//			System.out.println(me.getInteractions());

			SolutionExporter se = new SolutionExporter(me.getSolutions(), me.getInteractions());
			WeyekinPoster.launchPage(se.toPigeon());
		} catch(Exception e) {
			e.printStackTrace();
		}		

		// REPRESSION + ORIENTATION
		try {
			me.solve("N=2. contains c. contains p. p same_orientation c. not forward p or p after c. not reverse p or p before c. c represses p.");
//			System.out.println(me.getInteractions());

			SolutionExporter se = new SolutionExporter(me.getSolutions(), me.getInteractions());
			WeyekinPoster.launchPage(se.toPigeon());
		} catch(Exception e) {
			e.printStackTrace();
		}		

		return true;
	}

}
