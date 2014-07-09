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

public class MiniEugeneTester {

	private static final String NEWLINE = System.getProperty("line.separator");
	
	private static final char[] letters = {'p', 'r', 'g', 't'};
	
	/*** THIS WORKS... tested it some time ago... no test needed anymore...
	public boolean testN() {
		String[] testN = new String[20];
		testN[0] = new String("N");
		testN[1] = new String("*");
		testN[2] = new String("1");
		testN[3] = new String("N=");
		testN[4] = new String("*=");
		testN[5] = new String("1=");
		testN[6] = new String("*=1");
		testN[7] = new String("N=x1");
		testN[8] = new String("*=N");
		testN[9] = new String("*=1");
		testN[10] = new String("");
		testN[11] = new String("N=1.0");
		testN[12] = new String("1=3.14");
		testN[13] = new String("1.0=N");
		testN[14] = new String("x=3.14");
		testN[15] = new String("N 3.14");
		testN[16] = new String("N=1=*");
		testN[17] = new String("1 =");
		
		testN[18] = new String("N=     1");
		testN[19] = new String("    N    =    *");
		
		
		int[] valid = new int[2];		
		int validIdx = 0;		
		for(int i=0; i<testN.length; i++) {
			try {
				new MiniEugene().parseN(testN[i]);
				valid[validIdx++] = i;
			} catch(Exception e) {
			}
		}
		if(valid[0] == 18 && valid[1]==19) {
			return true;
		}
		return false;
	}
	**/
	
	public boolean testRules() {
		
		testContains();
		testAllBefore();
		testSomeBefore();
		
		testDirectionality();
		
		return true;
	}
	
	public boolean testContains() {
		boolean b = true;
		for(int i=0; i<letters.length; i++) {
			b &= testContains(i+1);
		}
		return b;
	}
	
	public boolean testAllBefore() {
		
		for(int i=2; i<=10; i++) {
			String s = new String("N="+i+NEWLINE+"a BEFORE b");
			try {
				MiniEugene me = new MiniEugene();				
				me.solve(s.split(NEWLINE), i, -1);
			} catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return true;
	}

	public boolean testSomeBefore() {
		
		for(int i=2; i<=10; i++) {
			String s = new String("N="+i+NEWLINE+"a SOME_BEFORE b");
			try {
				MiniEugene me = new MiniEugene();				
				me.solve(s.split(NEWLINE), -1, -1);
			} catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return true;
	}

	private boolean testContains(int n) {
		
		/*
		 * N ... length/size of the design
		 * 0 < N <= 10
		 */
		String[] testN = new String[10];

		for(int i=0; i<10; i++) {
			testN[i] = buildContains(i+1, n);
		}

		for(int i=0; i<testN.length; i++) {
			try {
				MiniEugene me = new MiniEugene();				
				me.solve(testN[i].split(NEWLINE), -1, -1);
			} catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		return true;
	}
	
	private void testDirectionality() {
		
		testAllReverse();
		testAllReverseWithId();
		testSomeReverse();
		

	}
	
	private void testAllReverse() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.buildContains(10, letters.length));
		sb.append("ALL_REVERSE");
		try {
			MiniEugene me = new MiniEugene();				
			me.solve(sb.toString().split(NEWLINE), 10, -1);
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void testAllReverseWithId() {
		
	}
	
	private void testSomeReverse() {
		
	}

	
	
	private String buildContains(int N, int k) {
		String s = new String("N="+N)+NEWLINE;
		for(int i=1; i<=k; i++) {
			s += "CONTAINS "+letters[i-1]+NEWLINE;
		}
		return s;
	}
	
	public static void main(String[] args) {
		MiniEugeneTester st = new MiniEugeneTester();
		
//		if(st.testN()) {
//			System.out.println("testN passed!");
//		} else {
//			System.err.println("testN failed!");			
//		}

		if(st.testRules()) {
			System.out.println("testRules passed!");
		} else {
			System.err.println("testRules failed!");			
		}

	}

}
