package org.cidarlab.minieugene.predicates;

import org.cidarlab.minieugene.dom.Component;

/**
 * Binary predicates have two operands 
 * left-hand-side (LHS) and right-hand-side (RHS) 
 * 
 * Examples of binary predicates are
 * AFTER, BEFORE, NEXTTO, MATCH, WITH, THEN
 * 
 * @author Ernst Oberortner
 */
/* 
 */
public abstract class BinaryPredicate 
	extends UnaryPredicate {
	
	private Component b;
	private int num;
	
	/**
	 * 
	 * @param lhs
	 * @param rhs
	 */
	public BinaryPredicate(Component a, Component b) {
		super(a);
		this.b = b;
		this.num = -1;
	}
	
	public BinaryPredicate(Component a, int num) {
		super(a);
		this.b = null;
		this.num = num;
	}
	
	/**
	 * 
	 * @return
	 */
	public Component getB() {
		return this.b;
	}	
	
	/**
	 * 
	 * @return
	 */
	public int getNum() {
		return this.num;
	}
}
