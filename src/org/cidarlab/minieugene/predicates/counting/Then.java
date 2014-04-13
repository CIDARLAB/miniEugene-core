package org.cidarlab.minieugene.predicates.counting;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.predicates.pairing.PairingPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.Count;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Not;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XgtC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/**
 * a THEN b
 * 
 * CONTAINS a => CONTAINS b
 * 
 * @author Ernst Oberortner
 *
 */
public class Then 
		extends BinaryPredicate
		implements PairingPredicate {

	public Then(Component a, Component b) {
		super(a, b);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA())
			.append(" ").append(RuleOperator.THEN).append(" ")
			.append((this.getB()));
		return sb.toString();
	}

	@Override
	public String getOperator() {
		return RuleOperator.THEN.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {		
		
		// CONTAINS A
		IntVar countA = new IntVar(store, "CONTAINS_"+this.getA().getName()+"-counter", 0, variables[Variables.PART].length); 
		store.impose(new Count(variables[Variables.PART], countA, this.getA().getId()));
		
		// CONTAINS B
		IntVar countB = new IntVar(store, "CONTAINS_"+this.getB().getName()+"-counter", 0, variables[Variables.PART].length); 
		store.impose(new Count(variables[Variables.PART], countB, this.getB().getId()));

		
		// IF CONTAINS A THEN CONTAINS B
		return new IfThen(
				new XgtC(countA, 0),
				new XgtC(countB, 0));
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		/*
		 * TODO: a NOTTHEN b vs NOT a THEN b
		 */
		return new Not(this.toJaCoP(store, variables));
	}

}
