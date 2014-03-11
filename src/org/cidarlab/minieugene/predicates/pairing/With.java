package org.cidarlab.minieugene.predicates.pairing;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.Or;
import JaCoP.constraints.Constraint;
import JaCoP.constraints.Count;
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
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {

		/*
		 * CONTAINS a /\ CONTAINS b
		 */

		IntVar aCounter = (IntVar)store.findVariable("CONTAINS_"+this.getA()+"-counter");
		if(aCounter == null) {
			aCounter = new IntVar(store, "CONTAINS_"+this.getA()+"-counter", 1, variables[Variables.PART].length);
		}
		
		IntVar bCounter = (IntVar)store.findVariable("CONTAINS_"+this.getB()+"-counter");
		if(bCounter == null) {
			bCounter = new IntVar(store, "CONTAINS_"+this.getB()+"-counter", 1, variables[Variables.PART].length);
		}
		
		store.impose(new Count(variables[Variables.PART], aCounter, this.getA().getId()));
		store.impose(new Count(variables[Variables.PART], bCounter, this.getB().getId()));

		return new And(
				new XgtC(aCounter, 0), 
				new XgtC(bCounter, 0));
	}

	@Override
	public Constraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		
		/*
		 * NOT CONTAINS a \/ NOT CONTAINS b
		 */
		
		IntVar aCounter = (IntVar)store.findVariable("CONTAINS_"+this.getA()+"-counter");
		if(aCounter == null) {
			aCounter = new IntVar(store, "CONTAINS_"+this.getA()+"-counter", 1, variables[Variables.PART].length);
		}
		
		IntVar bCounter = (IntVar)store.findVariable("CONTAINS_"+this.getB()+"-counter");
		if(bCounter == null) {
			bCounter = new IntVar(store, "CONTAINS_"+this.getB()+"-counter", 1, variables[Variables.PART].length);
		}

		store.impose(new Count(variables[Variables.PART], aCounter, this.getA().getId()));
		store.impose(new Count(variables[Variables.PART], bCounter, this.getB().getId()));
		
		return new Or(
				new XltC(aCounter, 1), 
				new XltC(bCounter, 1));
	}

}
