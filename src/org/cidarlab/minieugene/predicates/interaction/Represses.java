package org.cidarlab.minieugene.predicates.interaction;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.interaction.Interaction;

import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class Represses 
	extends InteractionPredicate {
	
	public Represses(Component a, Component b) {
		super(a, b);
	}
		

	@Override
	public String getOperator() {
		return Interaction.InteractionType.REPRESSES.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA().getName())
			.append(" ").append(Interaction.InteractionType.REPRESSES).append(" ")
			.append(this.getB().getName());
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {

		// a REPRESSES b =>
		//   CONTAINS a /\ CONTAINS b
//		return new And(
//				(new Contains(this.getA())).toJaCoP(store, variables),
//				(new Contains(this.getB())).toJaCoP(store, variables));
		
		return null;
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
