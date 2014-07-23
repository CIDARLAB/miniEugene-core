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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MiniEugeneRules {

	/*
	 * Counting Rules ...
	 * to constraint the number of occurences ...
	 */
	private static final Set<String> setCountingRules = new HashSet<String>(
			Arrays.asList(new String[] { 
					 "CONTAINS", "NOTCONTAINS", 
					 "NOTMORETHAN", "MORETHAN", 
					 "EXACTLY", "NOTEXACTLY",
					 "SAME_COUNT"}));
	
	
	/*
	 * Orientation Rules ...
	 */
	private static final Set<String> setOrientationRules = new HashSet<String>(
			Arrays.asList(new String[] { 
					 RuleOperator.SAME_ORIENTATION.toString(),
					 RuleOperator.ALL_SAME_ORIENTATION.toString(),
					 RuleOperator.SOME_SAME_ORIENTATION.toString(),
					 RuleOperator.FORWARD.toString(),
					 RuleOperator.REVERSE.toString(),
					 RuleOperator.ALL_FORWARD.toString(),
					 RuleOperator.ALL_REVERSE.toString(),
					 RuleOperator.SOME_FORWARD.toString(),
					 RuleOperator.SOME_REVERSE.toString()
					 }));
	/*
	 * Interaction Rules ... 
	 * for regulatory interactions
	 */
	private static final Set<String> setInteractionRules = new HashSet<String>(
			Arrays.asList(new String[] { 
					"REPRESSES", "INDUCES", "DRIVES", "BINDS", "MATCHES"}));

	/*
	 * Pairing Rules ...
	 * to constraint pairs of domain values
	 */
	private static final Set<String> setPairingRules = new HashSet<String>(
			Arrays.asList(new String[] { 
					"WITH", "NOTWITH", "THEN", "NOTTHEN", "ALWAYS_NEXTTO"}));
	
	/*
	 * Positional Rules
	 */
	private static final Set<String> setPositioningRules = new HashSet<String>(
			Arrays.asList(new String[] { 
					"BEFORE", "ALL_BEFORE", "SOME_BEFORE", 
					"AFTER", "ALL_AFTER", "SOME_AFTER",
					"NEXTTO", "ALL_NEXTTO", "SOME_NEXTTO",
					"LEFTTO", "ALL_LEFTTO", "SOME_LEFTTO",
					"RIGHTTO", "ALL_RIGHTTO", "SOME_RIGHTTO",
					"STARTSWITH", "ENDSWITH", 
					"EQUALS", "NOTEQUALS"}));

	private static final Set<String> setUnaryRules = new HashSet<String>(
			Arrays.asList(new String[] { 
					"CONTAINS", "NOTCONTAINS", "STARTSWITH", "ENDSWITH", 
					RuleOperator.ALL_REVERSE.toString(),
					RuleOperator.REVERSE.toString(), 
					RuleOperator.SOME_REVERSE.toString(),
					RuleOperator.ALL_FORWARD.toString(),
					RuleOperator.FORWARD.toString(), 
					RuleOperator.SOME_FORWARD.toString(),
					RuleOperator.ALTERNATE_ORIENTATION.toString()}));

	public static boolean isUnaryRule(String s) {
		return setUnaryRules.contains(s.toUpperCase());
	}

	public static boolean isPositioningRule(String s) {
		return setPositioningRules.contains(s.toUpperCase());
	}

	public static boolean isOrientationRule(String s) {
		return setOrientationRules.contains(s.toUpperCase());
	}
	public static boolean isCountingRule(String s) {
		return setCountingRules.contains(s.toUpperCase());
	}

	public static boolean isPairingRule(String s) {
		return setPairingRules.contains(s.toUpperCase());
	}
	
	public static boolean isInteractionRule(String s) {
		return setInteractionRules.contains(s.toUpperCase());
	}
}
