package org.cidarlab.minieugene.predicates.positional;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.UnaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.Constraint;
import JaCoP.constraints.XeqC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;


/*
 * STARTSWITH a
 * 
 * a ... a must appear at the FIRST position
 */
public class StartsWith 
	extends UnaryPredicate {

	public StartsWith(int a) {
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
		return new XeqC(variables[Variables.PART][0], this.getA());
	}

}
