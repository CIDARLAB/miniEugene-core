package org.cidarlab.minieugene.predicates.interaction;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.interaction.Interaction;

import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class Induces 
	extends InteractionPredicate {
	
	private String inducer;
	
	public Induces(String inducer, Component b) {
		super(null, b);
		this.inducer = inducer;
	}

	public String getInducer() {
		return this.inducer;
	}

	@Override
	public String getOperator() {
		return Interaction.InteractionType.INDUCES.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.inducer)
			.append(" ").append(Interaction.InteractionType.INDUCES).append(" ")
			.append(this.getB().getName());
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {

		// x INDUCES a =>
		//     CONTAINS a
		return null;
//		return (new Contains(this.getB())).toJaCoP(store, variables);
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		// TODO Auto-generated method stub
		return null;
	}

}
