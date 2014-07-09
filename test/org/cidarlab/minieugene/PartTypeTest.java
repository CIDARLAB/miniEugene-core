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

import org.cidarlab.minieugene.constants.PartTypesTable;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;

public class PartTypeTest {

	/*
	 * That's Ernst's UNIT testing... :)
	 */
	public static void main(String[] args) {
		if(!interfaceTests()) {
			System.exit(1);
		}
		
		if(!componentsTests()) {
			System.exit(2);
		}
		
		if(!miniEugeneTests()) {
			System.exit(3);
		}
		
		System.out.println("COOL!");
	}
	
	
	private static boolean interfaceTests() {
		/*
		 * test of the toPartType method
		 */
		if(null != PartTypesTable.toPartType(null)) {
			System.err.println(" FAILED!");
			return false;
		}
		if(null != PartTypesTable.toPartType("")) {
			System.err.println(" FAILED!");
			return false;
		}
		if(null != PartTypesTable.toPartType("I don't Know!")) {
			System.err.println("I don't Know! FAILED!");
			return false;
		}
		if(PartTypesTable.PartType.ip != PartTypesTable.toPartType("ip")) {
			System.err.println("ip FAILED!");
			return false;
		}
		if(PartTypesTable.PartType.ip != PartTypesTable.toPartType("ipIn1")) {
			System.err.println("ipIn1 FAILED!");
			return false;
		}
		if(PartTypesTable.PartType.ip != PartTypesTable.toPartType("iPIn1")) {
			System.err.println("iPIn1 FAILED!");
			return false;
		}
		if(PartTypesTable.PartType.ip != PartTypesTable.toPartType("IpIn1")) {
			System.err.println("IpIn1 FAILED!");
			return false;
		}
		if(PartTypesTable.PartType.ip != PartTypesTable.toPartType("IPIn1")) {
			System.err.println("IPIn1 FAILED!");
			return false;
		}


		/*
		 * test of the toName method
		 */
		if(!"Inducible Promoter".equals(PartTypesTable.toName(PartTypesTable.PartType.ip))) {
			System.err.println("Inducible Promoter FAILED!");
			return false;
		}


		/*
		 * testing the toId method
		 */
		if(-1 != PartTypesTable.toId(PartTypesTable.toPartType(null))) {
			System.err.println("toId() FAILED!");
			return false;
		}
		if(-1 != PartTypesTable.toId(PartTypesTable.toPartType(""))) {
			System.err.println("toId(\"\") FAILED!");
			return false;
		}
		if(0 != PartTypesTable.toId(PartTypesTable.toPartType("cp"))) {
			System.err.println("toId(cp) FAILED!");
			return false;
		}
		if(1 != PartTypesTable.toId(PartTypesTable.toPartType("ip"))) {
			System.err.println("toId(ip) FAILED!");
			return false;
		}
		if(5 != PartTypesTable.toId(PartTypesTable.toPartType("fc"))) {
			System.err.println("toId(fc) FAILED!");
			return false;
		}
		if(6 != PartTypesTable.toId(PartTypesTable.toPartType("rc"))) {
			System.err.println("toId(rc) FAILED!");
			return false;
		}
		if(7 != PartTypesTable.toId(PartTypesTable.toPartType("c"))) {
			System.err.println("toId(c) FAILED!");
			return false;
		}
		if(-1 != PartTypesTable.toId(PartTypesTable.toPartType("xyz"))) {
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
		
		if(PartTypesTable.toId(PartTypesTable.toPartType("")) != new Component("").getTypeId()) {
			System.err.println("componentsTest01 FAILED!");
			return false;
		}

		if(!PartTypesTable.toName(PartTypesTable.toPartType("ip")).equals(new Component("ipIn1").getTypeName())) {
			System.err.println("componentsTest02 FAILED!");
			return false;
		}

		if(PartTypesTable.toId(PartTypesTable.toPartType("ip")) != new Component("ipIn1").getTypeId()) {
			System.err.println("componentsTest03 FAILED!");
			return false;
		}
		
		if(PartTypesTable.toId(PartTypesTable.toPartType("c")) != new Component("cds").getTypeId()) {
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
					if(PartTypesTable.toId(PartTypesTable.toPartType("p")) != s[i].getTypeId()) {
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
				if(PartTypesTable.toId(PartTypesTable.toPartType("p")) != s[0].getTypeId() && 
						PartTypesTable.toId(PartTypesTable.toPartType("r")) != s[1].getTypeId()) {
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
				if(PartTypesTable.toId(PartTypesTable.toPartType(null)) != s[0].getTypeId()) {
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
