package org.cidarlab.minieugene.predicates.counting;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.UnaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.Count;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XgtC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/**
 * 
 * unary contains 
 * 
 * CONTAINS a
 * 		
 * @author Ernst Oberortner
 *
 */
public class Contains
		extends UnaryPredicate
		implements CountingPredicate {

	public Contains(Component a) {
		super(a);
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.CONTAINS.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getOperator()).append(" ").append(this.getA());
		return sb.toString();
	}
	
	@Override
	public PrimitiveConstraint toJaCoP(
			Store store, IntVar[][] variables) 
				throws MiniEugeneException {
		
		/*
		 * CONTAINS a
		 */
		
		// a's counter
		IntVar counterA = (IntVar)store.findVariable(this.getA().getName()+"-counter");
		if(null == counterA) {
			counterA = new IntVar(store, 
					this.getA().getName()+"-counter", 
					0, 
					variables[Variables.PART].length);
			store.impose(new Count(variables[Variables.PART], counterA, this.getA().getId()));
		}
		
		return new XgtC(counterA, 0);
		
	}
	
	@Override
	public PrimitiveConstraint toJaCoPNot(
			Store store, IntVar[][] variables) 
				throws MiniEugeneException {
		// a's counter
		IntVar counterA = (IntVar)store.findVariable(this.getA().getName()+"-counter");
		if(null == counterA) {
			counterA = new IntVar(store, 
					this.getA().getName()+"-counter", 
					0, 
					variables[Variables.PART].length);
		}
		store.impose(new Count(variables[Variables.PART], counterA, this.getA().getId()));
		
		return new XeqC(counterA, 0);
	}

	@Override
	public int getMinimumLength() {
		return 1;
	}

}
