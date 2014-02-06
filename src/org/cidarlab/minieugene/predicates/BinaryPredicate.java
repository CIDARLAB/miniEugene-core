package org.cidarlab.minieugene.predicates;

/* 
 * Binary predicates have two rule operands (A and B)
 * 
 * In Eugene, the following rules are binary rules:
 * AFTER, BEFORE, NEXTTO, MATCH, WITH, THEN
 */
public abstract class BinaryPredicate 
	implements Predicate {
	
	protected int a;
	protected int b;
	
	public BinaryPredicate(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public int getA() {
		return this.a;
	}
	public int getB() {
		return this.b;
	}
		
}
