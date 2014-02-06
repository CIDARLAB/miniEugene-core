package org.cidarlab.minieugene.predicates.pairing;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.Constraint;
import JaCoP.constraints.Count;
import JaCoP.constraints.XgtC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;


public class With 
		extends BinaryPredicate {

	public With(int a, int b) {
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
		 * a must appear at least once AND
		 * b must appear at lease once 
		 */

		IntVar aCounter = (IntVar)store.findVariable("CONTAINS_"+this.getA()+"-counter");
		if(aCounter == null) {
			aCounter = new IntVar(store, "CONTAINS_"+this.getA()+"-counter", 1, variables[Variables.PART].length);
		}
		
		IntVar bCounter = (IntVar)store.findVariable("CONTAINS_"+this.getB()+"-counter");
		if(bCounter == null) {
			bCounter = new IntVar(store, "CONTAINS_"+this.getB()+"-counter", 1, variables[Variables.PART].length);
		}
		
		store.impose(new Count(variables[Variables.PART], aCounter, this.getA()));
		store.impose(new Count(variables[Variables.PART], bCounter, this.getB()));

		return new And(new XgtC(aCounter, 0), new XgtC(bCounter, 0));
	}

}
