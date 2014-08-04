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

package org.cidarlab.minieugene.constants;

import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang3.ArrayUtils;

public class PredefinedTypes {
	
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
		sp,   // Spacer
		unk   // Unknown Type
	}
	
	private static Map<PartType, String> parttypes;
	
	/*
	 * in the pigeonMap we map the Eugene part types 
	 * onto the corresponding pigeon letter
	 */
	private static Map<PredefinedTypes.PartType, Character> pigeonMap;

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

		parttypes.put(PartType.unk, "Unknown Type");		

	}
	
	public static boolean isPredefined(String t) {
		if(null == parttypes) {
			load();
		}
		
		try {
			return PartType.valueOf(t) != null ? true : false;
		} catch(Exception e) {
			return false;
		}
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
			return PartType.unk;
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
		
		return PartType.unk;
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
		pigeonMap = new HashMap<PredefinedTypes.PartType, Character>();
		
		pigeonMap.put(PredefinedTypes.PartType.cp, 'p');
		pigeonMap.put(PredefinedTypes.PartType.ip, 'p');
		pigeonMap.put(PredefinedTypes.PartType.rp, 'p');
		pigeonMap.put(PredefinedTypes.PartType.p, 'p');
		
		pigeonMap.put(PredefinedTypes.PartType.r, 'r');
		
		pigeonMap.put(PredefinedTypes.PartType.fc, 'c');
		pigeonMap.put(PredefinedTypes.PartType.rc, 'c');
		pigeonMap.put(PredefinedTypes.PartType.c, 'c');
		pigeonMap.put(PredefinedTypes.PartType.g, 'g');

		pigeonMap.put(PredefinedTypes.PartType.t, 't');
		pigeonMap.put(PredefinedTypes.PartType.is, '>');
		pigeonMap.put(PredefinedTypes.PartType.sp, 's');
		
		pigeonMap.put(PredefinedTypes.PartType.unk, '?');
	}
	
}
