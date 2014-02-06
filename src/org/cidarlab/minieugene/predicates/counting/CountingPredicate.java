package org.cidarlab.minieugene.predicates.counting;

import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.Predicate;

import JaCoP.constraints.Constraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/*
 * e.g. CONTAINS a -> a appears (at least) 1-times in the design 
 */
public abstract class CountingPredicate 
	implements Predicate {

	private int a;
	private int n;
	
	public CountingPredicate(int a, int n) {
		this.a = a;
		this.n = n;
	}
	
	public int getA() {
		return this.a;
	}
	
	public int getN() {
		return this.n;
	}

	public abstract Constraint toJaCoPNot(Store store, IntVar[][] variables) 
				throws EugeneException;

}
