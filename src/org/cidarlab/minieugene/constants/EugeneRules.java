package org.cidarlab.minieugene.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EugeneRules {

	/*
	 * Counting Rules ...
	 * to constraint the number of occurences ...
	 */
	private static final Set<String> setCountingRules = new HashSet<String>(
			Arrays.asList(new String[] { 
					 "CONTAINS", "NOTCONTAINS", "NOTMORETHAN", "MORETHAN", "EXACTLY", "NOTEXACTLY"}));
	
	
	/*
	 * Orientation Rules ...
	 */
	private static final Set<String> setOrientationRules = new HashSet<String>(
			Arrays.asList(new String[] { 
					 RuleOperator.SAME_ORIENTATION.toString(),
					 RuleOperator.ALL_SAME_ORIENTATION.toString(),
					 RuleOperator.SOME_SAME_ORIENTATION.toString()
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
					"WITH", "NOTWITH", "THEN", "NOTTHEN"}));
	
	/*
	 * Positional Rules
	 */
	private static final Set<String> setPositionalRules = new HashSet<String>(
			Arrays.asList(new String[] { 
					"BEFORE", "ALL_BEFORE", "SOME_BEFORE", 
					"AFTER", "ALL_AFTER", "SOME_AFTER",
					"NEXTTO", "ALL_NEXTTO", "SOME_NEXTTO",
					"LEFTTO", "ALL_LEFTTO", "SOME_LEFTTO",
					"RIGHTTO", "ALL_RIGHTTO", "SOME_RIGHTTO",
					"STARTSWITH", "ENDSWITH"}));

	private static final Set<String> setUnaryRules = new HashSet<String>(
			Arrays.asList(new String[] { 
					"CONTAINS", "NOTCONTAINS", "STARTSWITH", "ENDSWITH", 
					RuleOperator.ALL_REVERSE.toString(),
					RuleOperator.REVERSE.toString(), 
					RuleOperator.SOME_REVERSE.toString(),
					RuleOperator.ALL_FORWARD.toString(),
					RuleOperator.FORWARD.toString(), 
					RuleOperator.SOME_FORWARD.toString(),
					RuleOperator.ALTERNATE.toString()}));

	public static boolean isUnaryRule(String s) {
		return setUnaryRules.contains(s.toUpperCase());
	}

	public static boolean isPositionalRule(String s) {
		return setPositionalRules.contains(s.toUpperCase());
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
