package org.cidarlab.minieugene.predicates.position;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.UnaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.Constraint;
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
		sb.append(RuleOperator.STARTSWITH).append(" ").append(this.getA());
		return sb.toString();
	}


	@Override
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {
		return new XeqC(variables[Variables.PART][0], this.getA().getId());
	}

	@Override
	public Constraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		return new XneqC(variables[Variables.PART][0], this.getA().getId());
	}

}
