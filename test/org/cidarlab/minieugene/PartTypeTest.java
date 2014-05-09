package org.cidarlab.minieugene;

import java.util.List;

import org.cidarlab.minieugene.constants.PartTypesTable;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;

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
						throw new EugeneException("miniEugeneTest01 failed! -> "+s[i]);
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
					throw new EugeneException("miniEugeneTest02 failed!");
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
					throw new EugeneException("miniEugeneTest03 failed!");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
