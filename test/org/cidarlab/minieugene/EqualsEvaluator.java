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

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.constants.EugeneConstants;

public class EqualsEvaluator {

	private static final int MAX_DESIGN_SIZE = 3; 
	
	public void evaluate() {
		for(int i=2; i<=MAX_DESIGN_SIZE; i++) {
			System.out.println("*** i: "+i);
			String s=buildScript(i);
			
//			System.out.println(s);

			try {
				MiniEugene me = new MiniEugene();				
				me.solve(s.split(System.getProperty("line.separator")),  i, -1);
				System.out.println(
						me.getStatistics().getValueByKey(EugeneConstants.NUMBER_OF_SOLUTIONS));
//				mer.printSolutions();
//				System.out.println(mer.getSBOL().getPath());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String buildScript(int N) {
		String NEWLINE = System.getProperty("line.separator");
		
		StringBuilder sb = new StringBuilder();
		sb.append("CONTAINS a").append(NEWLINE);
		sb.append("CONTAINS b").append(NEWLINE);
//		for(int i=0; i<N-1; i++) {
			sb.append("[").append("0")
				.append("] NOTEQUALS [")
				.append("1").append("]")
				.append(NEWLINE);
//		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		EqualsEvaluator te = new EqualsEvaluator();
		te.evaluate();
	}
}
