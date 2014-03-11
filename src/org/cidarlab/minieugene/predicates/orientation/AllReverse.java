package org.cidarlab.minieugene.predicates.orientation;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.UnaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Not;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/*
 * ALL_REVERSE
 * 
 * all elements must have a reverse direction
 * 
 * X := the set of all symbols (defined by the user)
 * a element_of X
 * a == -1 => forall X : direction(X) = '-'
 * a != -1 => forall a : direction(a) = '-'
 * 
 */
public class AllReverse
	extends UnaryPredicate
	implements OrientationPredicate {

	public AllReverse(Component a) {
		super(a);
	}

	@Override
	public String getOperator() {
		return RuleOperator.ALL_REVERSE.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(RuleOperator.ALL_REVERSE);
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {
		
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
		
		return new And(pc);
	}

	
	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		
		return new Not(this.toJaCoP(store, variables));
	}

}
