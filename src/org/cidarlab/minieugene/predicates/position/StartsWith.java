package org.cidarlab.minieugene.predicates.position;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.UnaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XneqC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;


/*
 * STARTSWITH a
 * 
 * a ... a must appear at the FIRST position
 */
public class StartsWith 
	extends UnaryPredicate 
	implements PositioningPredicate {

	public StartsWith(Component a) {
		super(a);
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.STARTSWITH.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(RuleOperator.STARTSWITH).append(" ").append(this.getA().getName());
		return sb.toString();
	}


	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {
//		int N = (variables[Variables.PART].length);
//		IntVar aPosVar = (IntVar)store.findVariable(this.getA().getName()+".position");
//		if(null == aPosVar) {
//			aPosVar = new IntVar(store, this.getA().getName()+".position", 0, N-1);
//			store.impose(new XeqC(aPosVar, 0));
//		}

		return new XeqC(variables[Variables.PART][0], this.getA().getId());
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		
//		int N = (variables[Variables.PART].length);
//		IntVar aPosVar = (IntVar)store.findVariable(this.getA().getName()+".position");
//		if(null == aPosVar) {
//			aPosVar = new IntVar(store, this.getA().getName()+".position", 0, N-1);
//			store.impose(new XneqC(aPosVar, 0));
//		}
		
		return new XneqC(variables[Variables.PART][0], this.getA().getId());
	}

}
