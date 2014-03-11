package org.cidarlab.minieugene.predicates;

import org.cidarlab.minieugene.dom.Component;

/**
 * Unary predicates are rule predicates that MUST have at least one rule operand 
 * i.e. the right-hand-side (RHS) of the rule MUST be a given
 * 
 * In miniEugene, the following rules, for example, are unary rules:
 * CONTAINS, STARTSWITH, ENDSWITH
 * 
 * @author Ernst Oberortner
 */
public abstract class UnaryPredicate 
		extends EugeneConstraint {
		
	private Component a;
	
	/**
	 * 
	 * @param lhs
	 * @param rhs
	 */
	public UnaryPredicate(Component a) {
		this.a = a;
	}
	
	/**
	 * 
	 * @return
	 */
	public Component getA() {
		return this.a;
	}
}
