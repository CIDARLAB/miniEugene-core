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
 * ENDSWITH a
 * 
 * a ... a must appear at the LAST position
 */
public class EndsWith 
	extends UnaryPredicate {

	public EndsWith(int a) {
		super(a);
	}

	@Override
	public String getOperator() {
		return RuleOperator.ENDSWITH.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(RuleOperator.ENDSWITH).append(" ").append(this.getA());
		return sb.toString();
	}

	@Override
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {
		int N = variables[Variables.PART].length;
		return new XeqC(variables[Variables.PART][N-1], this.getA());
	}

}
