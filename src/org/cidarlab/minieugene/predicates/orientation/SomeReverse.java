package org.cidarlab.minieugene.predicates.orientation;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.UnaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.IfThen;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/*
 * SOME_REVERSE a
 * 
 * X := the set of all symbols (defined by the user)
 * a element_of X
 * exists a : direction(a) = '-'
 */
public class SomeReverse
	extends UnaryPredicate
	implements OrientationPredicate {

	public SomeReverse(Component a) {
		super(a);
	}

	@Override
	public String getOperator() {
		return RuleOperator.SOME_REVERSE.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getOperator());
		if(null != this.getA()) {
			sb.append(" ").append(this.getA().getName());
		}
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {

		/*
		 * SOME_REVERSE a ...
		 * CONTAINS a =>
		 * the number of a components that are reverse oriented must be greater or equal to 1
		 */

		PrimitiveConstraint[] pc = new PrimitiveConstraint[variables[Variables.ORIENTATION].length];
		if(this.getA() == null) {
			for(int i=0; i<variables[Variables.ORIENTATION].length; i++) {
				pc[i] = new XeqC(variables[Variables.ORIENTATION][i], -1);
			}
		} else {
			for(int i=0; i<variables[Variables.ORIENTATION].length; i++) {
				pc[i] = new IfThen(
							new XeqC(variables[Variables.PART][i], this.getA().getId()),
							new XeqC(variables[Variables.ORIENTATION][i], -1));
			}
		}
		
		return new Or(pc);
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {

		return new Not(this.toJaCoP(store, variables));
	}

}
