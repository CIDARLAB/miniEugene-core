package org.cidarlab.minieugene.predicates.counting;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.predicates.pairing.PairingPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.Constraint;
import JaCoP.constraints.Count;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XeqY;
import JaCoP.constraints.XgtC;
import JaCoP.constraints.XltC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/**
 * 
 * @author Ernst Oberortner
 *
 */
public class With 
		extends BinaryPredicate
		implements PairingPredicate {

	public With(Component a, Component b) {
		super(a, b);
	}

	@Override	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA()).append(" ")
			.append(RuleOperator.WITH).append(" ")
			.append(this.getB());
		return sb.toString();
	}

	@Override
	public String getOperator() {
		return RuleOperator.WITH.toString();
	}


	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {
		
		/*
		 * CONTAINS a /\ CONTAINS b
		 */
		return new And(
				new Contains(this.getA()).toJaCoP(store, variables), 
				new Contains(this.getB()).toJaCoP(store, variables));
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		
		// a's counter
		IntVar counterA = (IntVar)store.findVariable(this.getA().getName()+"-counter");
		if(null == counterA) {
			counterA = new IntVar(store, 
					this.getA().getName()+"-counter", 
					0, 
					variables[Variables.PART].length);
			store.impose(new Count(variables[Variables.PART], counterA, this.getA().getId()));
		}

		// b's counter
		IntVar counterB = (IntVar)store.findVariable(this.getB().getName()+"-counter");
		if(null == counterB) {
			counterB = new IntVar(store, 
					this.getB().getName()+"-counter", 
					0, 
					variables[Variables.PART].length);
			store.impose(new Count(variables[Variables.PART], counterB, this.getB().getId()));
		}

		/*
		 * NOT (CONTAINS a /\ CONTAINS b)
		 * <=>
		 * NOT CONTAINS a \/ NOT CONTAINS b
		 */
		return new Or(
				new XeqC(counterA, 0), 
				new XeqC(counterB, 0));
	}

}
