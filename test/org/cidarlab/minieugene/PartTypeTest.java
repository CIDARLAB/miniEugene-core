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

import java.util.List;

import org.cidarlab.minieugene.constants.PredefinedTypes;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;

public class PartTypeTest {

	/*
	 * That's Ernst's UNIT testing... :)
	 */
	public static void main(String[] args) {
//		if(!interfaceTests()) {
//			System.exit(1);
//		}
//		
//		if(!componentsTests()) {
//			System.exit(2);
//		}
		
		if(!miniEugeneTests()) {
			System.exit(3);
		}
		
		System.out.println("COOL!");
	}
	
	
	private static boolean interfaceTests() {
		/*
		 * test of the toPartType method
		 */
		if(PredefinedTypes.PartType.unk != PredefinedTypes.toPartType(null)) {
			System.err.println(" FAILED!");
			return false;
		}
		if(PredefinedTypes.PartType.unk != PredefinedTypes.toPartType("")) {
			System.err.println(" FAILED!");
			return false;
		}
		if(PredefinedTypes.PartType.unk != PredefinedTypes.toPartType("I don't Know!")) {
			System.err.println("I don't Know! FAILED!");
			return false;
		}
		if(PredefinedTypes.PartType.ip != PredefinedTypes.toPartType("ip")) {
			System.err.println("ip FAILED!");
			return false;
		}
		if(PredefinedTypes.PartType.ip != PredefinedTypes.toPartType("ipIn1")) {
			System.err.println("ipIn1 FAILED!");
			return false;
		}
		if(PredefinedTypes.PartType.ip != PredefinedTypes.toPartType("iPIn1")) {
			System.err.println("iPIn1 FAILED!");
			return false;
		}
		if(PredefinedTypes.PartType.ip != PredefinedTypes.toPartType("IpIn1")) {
			System.err.println("IpIn1 FAILED!");
			return false;
		}
		if(PredefinedTypes.PartType.ip != PredefinedTypes.toPartType("IPIn1")) {
			System.err.println("IPIn1 FAILED!");
			return false;
		}


		/*
		 * test of the toName method
		 */
		if(!"Inducible Promoter".equals(PredefinedTypes.toName(PredefinedTypes.PartType.ip))) {
			System.err.println("Inducible Promoter FAILED!");
			return false;
		}


		/*
		 * testing the toId method
		 */
		// 12 == UNKNOWN
		if(12 != PredefinedTypes.toId(PredefinedTypes.toPartType(null))) {
			System.err.println("toId() FAILED!");
			return false;
		}
		if(12 != PredefinedTypes.toId(PredefinedTypes.toPartType(""))) {
			System.err.println("toId(\"\") FAILED!");
			return false;
		}
		if(0 != PredefinedTypes.toId(PredefinedTypes.toPartType("cp"))) {
			System.err.println("toId(cp) FAILED!");
			return false;
		}
		if(1 != PredefinedTypes.toId(PredefinedTypes.toPartType("ip"))) {
			System.err.println("toId(ip) FAILED!");
			return false;
		}
		if(5 != PredefinedTypes.toId(PredefinedTypes.toPartType("fc"))) {
			System.err.println("toId(fc) FAILED!");
			return false;
		}
		if(6 != PredefinedTypes.toId(PredefinedTypes.toPartType("rc"))) {
			System.err.println("toId(rc) FAILED!");
			return false;
		}
		if(7 != PredefinedTypes.toId(PredefinedTypes.toPartType("c"))) {
			System.err.println("toId(c) FAILED!");
			return false;
		}
		if(12 != PredefinedTypes.toId(PredefinedTypes.toPartType("unk"))) {
			System.err.println("toId(xyz) FAILED!");
			return false;
		}

		return true;
	}
	
	/*
	 * here, we test instantiating the Component class 
	 * and comparing the Component's against valid part types
	 */
	private static boolean componentsTests() {
		
		if(PredefinedTypes.toId(PredefinedTypes.toPartType("")) != new Component("", null).getTypeId()) {
			System.err.println("componentsTest01 FAILED!");
			return false;
		}

		if(!PredefinedTypes.toName(PredefinedTypes.toPartType("ip")).equals(new Component("ipIn1", null).getTypeName())) {
			System.err.println("componentsTest02 FAILED!");
			return false;
		}

		if(PredefinedTypes.toId(PredefinedTypes.toPartType("ip")) != new Component("ipIn1", null).getTypeId()) {
			System.err.println("componentsTest03 FAILED!");
			return false;
		}
		
		if(PredefinedTypes.toId(PredefinedTypes.toPartType("c")) != new Component("cds", null).getTypeId()) {
			System.err.println("componentsTest04 FAILED!");
			return false;
		}

		return true;
	}
	
	/*
	 * here, we test executing miniEugene based on the new 
	 * part type features
	 */
	private static boolean miniEugeneTests() {
		
		MiniEugene me = new MiniEugene();
		
		// miniEugeneTest01
		try {
			me.solve("N=1. contains pIn1.");
			List<Component[]> ss = me.getSolutions();
			for(Component[] s : ss) {
				for(int i=0; i<s.length; i++) {
					if(PredefinedTypes.toId(PredefinedTypes.toPartType("p")) != s[i].getTypeId()) {
						throw new MiniEugeneException("miniEugeneTest01 failed! -> "+s[i]);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}

		// miniEugeneTest02
		try {
			me.solve("N=2. contains p. contains r. p before r.");
			List<Component[]> ss = me.getSolutions();
			for(Component[] s : ss) {
				if(PredefinedTypes.toId(PredefinedTypes.toPartType("p")) != s[0].getTypeId() && 
						PredefinedTypes.toId(PredefinedTypes.toPartType("r")) != s[1].getTypeId()) {
					throw new MiniEugeneException("miniEugeneTest02 failed!");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}

		// miniEugeneTest03
		try {
			me.solve("N=1. contains UNKNOWN.");
			List<Component[]> ss = me.getSolutions();
			for(Component[] s : ss) {
				if(PredefinedTypes.toId(PredefinedTypes.toPartType(null)) != s[0].getTypeId()) {
					throw new MiniEugeneException("miniEugeneTest03 failed!");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
