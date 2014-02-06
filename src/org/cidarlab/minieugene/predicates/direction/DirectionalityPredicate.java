package org.cidarlab.minieugene.predicates.direction;

import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.Predicate;

import JaCoP.constraints.Constraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public abstract class DirectionalityPredicate 
	implements Predicate {

	private int a;
	
	public DirectionalityPredicate(int a) {
		this.a = a;
	}
	
	public int getA() {
		return a;
	}
	
	public abstract Constraint toJaCoPNot(Store store, IntVar[][] variables) 
			throws EugeneException;
}
