package org.cidarlab.minieugene.predicates;

/* 
 * Unary predicates are rule predicates that MUST have at least one rule operand 
 * i.e. the right-hand-side (RHS) of the rule MUST be a given
 * 
 * In Eugene, the following rules are nullary rules:
 * ALL_FORWARD, ALL_REVERSE, ALTERNATE, NO_REPETITION
 */
public abstract class NullaryPredicate 
		implements Predicate {
	
	public NullaryPredicate() {
	}
}
