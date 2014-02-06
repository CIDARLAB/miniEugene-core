package org.cidarlab.minieugene.solver.jacop;

import java.util.HashMap;
import java.util.Map;

public class PartTypes {

	public static Map<String, Integer> types;
	
	private static void init() {
		types = new HashMap<String, Integer>();
		
		types.put("PROMOTER", 1);
		types.put("RBS", 2);
		types.put("GENE", 3);
		types.put("TERMINATOR", 4);
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
