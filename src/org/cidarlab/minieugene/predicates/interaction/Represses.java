package org.cidarlab.minieugene.predicates.interaction;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;

import JaCoP.constraints.Constraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class Represses 
	extends InteractionPredicate {
	
	public Represses(int a, int b) {
		super(a, b);
	}
		

	@Override
	public String getOperator() {
		return RuleOperator.REPRESSES.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA())
			.append(" ").append(RuleOperator.REPRESSES).append(" ")
			.append(this.getB());
		return sb.toString();
	}

	@Override
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {
		System.out.println(this.toString());
		return null;
	}
	
}
