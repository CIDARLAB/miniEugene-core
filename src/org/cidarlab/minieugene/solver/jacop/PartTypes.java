package org.cidarlab.minieugene.solver.jacop;

import java.util.HashMap;
import java.util.Map;

public class PartTypes {

	public static Map<String, Integer> types;
	
	private static void init() {
		types = new HashMap<String, Integer>();
		
		types.put("PROMOTER", 3);
		types.put("RBS", 4);
		types.put("GENE", 7);
		types.put("TERMINATOR", 9);
	}
	
	public static int get(String s) {
		if(null == types) {
			init();
		}
		
		if(types.containsKey(s.toUpperCase())) {
			return types.get(s.toUpperCase());
		}
		
		return -1;
	}
}
