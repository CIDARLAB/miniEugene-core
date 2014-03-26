package org.cidarlab.minieugene.predicates.counting;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.Count;
import JaCoP.constraints.Not;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqY;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/**
 * 
 * binary constraint 
 * a SAME_COUNT b
 * 
 * a must appear as many times as b
 * 		
 * @author Ernst Oberortner
 *
 */
public class SameCount
		extends BinaryPredicate
		implements CountingPredicate {

	public SameCount(Component a, Component b) {
		super(a, b);
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.SAME_COUNT.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA().getName()).append(" ")
			.append(this.getOperator()).append(" ")
			.append(this.getB().getName());
		return sb.toString();
	}
	
	@Override
	public PrimitiveConstraint toJaCoP(
			Store store, IntVar[][] variables) 
				throws EugeneException {
		
		/*
		 * a SAME_COUNT b
		 */
		
		// a's counter
		IntVar counterA = (IntVar)store.findVariable("CONTAINS_"+this.getA().getName()+"-counter");
		if(null == counterA) {
			counterA = new IntVar(store, "CONTAINS_"+this.getA().getName()+"-counter", 0, variables[Variables.PART].length);
		}
		store.impose(new Count(variables[Variables.PART], counterA, this.getA().getId()));

		// b's counter
		IntVar counterB = (IntVar)store.findVariable("CONTAINS_"+this.getB().getName()+"-counter");
		if(null == counterB) {
			counterB = new IntVar(store, "CONTAINS_"+this.getB().getName()+"-counter", 0, variables[Variables.PART].length);
		}
		store.impose(new Count(variables[Variables.PART], counterB, this.getB().getId()));

		// both counters must be equal
		return new XeqY(counterA, counterB);
		
	}
	
	@Override
	public PrimitiveConstraint toJaCoPNot(
			Store store, IntVar[][] variables) 
				throws EugeneException {
		
		System.out.println("imposing NOT "+this.toString());
		
		/*
		 * NOT a SAME_COUNT b
		 */
		return new Not(this.toJaCoP(store, variables));
	}

}
