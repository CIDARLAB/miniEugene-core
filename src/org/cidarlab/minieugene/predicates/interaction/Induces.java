package org.cidarlab.minieugene.predicates.interaction;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;

import JaCoP.constraints.Constraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class Induces 
	extends InteractionPredicate {
	
	private String inducer;
	
	public Induces(String inducer, int b) {
		super(-1, b);
		this.inducer = inducer;
	}

	public String getInducer() {
		return this.inducer;
	}

	@Override
	public String getOperator() {
		return RuleOperator.INDUCES.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA())
			.append(" ").append(RuleOperator.INDUCES).append(" ")
			.append(this.getB());
		return sb.toString();
	}

	@Override
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {
		return null;
	}

}
