package org.cidarlab.minieugene.predicates;

/* 
 * Unary predicates are rule predicates that MUST have at least one rule operand 
 * i.e. the right-hand-side (RHS) of the rule MUST be a given
 * 
 * In Eugene, the following rules are unary rules:
 * CONTAINS, STARTSWITH, ENDSWITH
 */
public abstract class UnaryPredicate 
		implements Predicate {
	
	private int a;
	
	public UnaryPredicate(int a) {
		this.a = a;
	}
	
	public int getA() {
		return this.a;
	}
}
