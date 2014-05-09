package org.cidarlab.minieugene.constants;

import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang3.ArrayUtils;
import org.cidarlab.minieugene.dom.Component;

public class PartTypesTable {
	
	public enum PartType {
		cp,   // Constituitive Promoter
		ip,   // Inducible Promoter
		rp,   // Repressible Promoter
		p,    // Promoter
		r,    // Ribosome Binding Site
		fc,   // Coding Sequence that encodes a Fluorescent Protein
		rc,   // Coding Sequence that encodes a Repressor
		c,    // Coding sequence
		g,    // Gene
		t,    // Terminator 
		is,   // Invertase Site
		sp    // Spacer
	}
	
	private static Map<PartType, String> parttypes;
	
	/*
	 * in the pigeonMap we map the Eugene part types 
	 * onto the corresponding pigeon letter
	 */
	private static Map<PartTypesTable.PartType, Character> pigeonMap;

	public static void load() {
		
		parttypes = new HashMap<PartType, String>();
		
		/*
		 * currently, this is hard-coded...
		 * would be great to ``load'' the part types table from,
		 * for example, a file
		 */
		parttypes.put(PartType.cp, "Constitutive Promoter");
		parttypes.put(PartType.ip, "Inducible Promoter");
		parttypes.put(PartType.rp, "Repressible Promoter");
		parttypes.put(PartType.p, "Promoter");

		parttypes.put(PartType.r, "Ribosome Binding Site");

		parttypes.put(PartType.fc, "Fluorescent Coding Sequence");
		parttypes.put(PartType.rc, "Repressing Coding Sequence");
		parttypes.put(PartType.c, "Coding Sequence");
		parttypes.put(PartType.g, "Gene");
		
		parttypes.put(PartType.t, "Terminator");
		
		parttypes.put(PartType.is, "Invertase Site");		
		parttypes.put(PartType.sp, "Spacer");		
	}
	
	public static String toName(PartType pt) {
		if(null == parttypes) {
			load();
		}

		return parttypes.get(pt);
	}
	
	public static int toId(PartType pt) {
		PartType[] array = PartType.values();
		return ArrayUtils.indexOf(array, pt);
	}

	public static PartType toPartType(String name) {
		if(name == null || name.length() < 1) {
			return null;
		}

		if(name.length() > 1) {
			String second = name.substring(0, 2).toLowerCase();
			try {
				return PartType.valueOf(second);
			} catch(java.lang.IllegalArgumentException iae) {}
		}
		
		String first = name.substring(0, 1).toLowerCase();
		try {
			return PartType.valueOf(first);
		} catch(java.lang.IllegalArgumentException iae) {}
		
		return null;
	}
	
	
	/*
	 * PIGEON-related methods
	 */
	public static char toPigeonLetter(int id) {

		if(id == -1) {
			return '?';
		}
		
		/*
		 * LAZY instantiation of the Eugene2PigeonMap
		 */
		if(null == pigeonMap) {
			loadPigeonMap();
		}
		
		PartType pt = PartType.values()[id];
		
		if(pigeonMap.containsKey(pt)) {
			return (pigeonMap.get(pt)).charValue();
		}
		
		// unknown part type
		return '?';
	}

	/**
	 * loading of the pigeon map
	 * 
	 * one current drawback:
	 * we need to keep the loadMap method aligned 
	 * with the PartTypesTable.PartType enum 
	 * => we could move the Eugene2PigeonMap into the PartTypesTable class
	 */
	private static void loadPigeonMap() {
		/*
		 * key   ... Eugene type
		 * value ... Pigeon letter 
		 */
		pigeonMap = new HashMap<PartTypesTable.PartType, Character>();
		
		pigeonMap.put(PartTypesTable.PartType.cp, 'p');
		pigeonMap.put(PartTypesTable.PartType.ip, 'p');
		pigeonMap.put(PartTypesTable.PartType.rp, 'p');
		pigeonMap.put(PartTypesTable.PartType.p, 'p');
		
		pigeonMap.put(PartTypesTable.PartType.r, 'r');
		
		pigeonMap.put(PartTypesTable.PartType.fc, 'c');
		pigeonMap.put(PartTypesTable.PartType.rc, 'c');
		pigeonMap.put(PartTypesTable.PartType.c, 'c');
		pigeonMap.put(PartTypesTable.PartType.g, 'g');

		pigeonMap.put(PartTypesTable.PartType.t, 't');
		pigeonMap.put(PartTypesTable.PartType.is, '>');
		pigeonMap.put(PartTypesTable.PartType.sp, 's');
	}
	
}
