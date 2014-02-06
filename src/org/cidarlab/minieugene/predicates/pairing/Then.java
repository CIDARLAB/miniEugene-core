package org.cidarlab.minieugene.predicates.pairing;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.Constraint;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;


public class Then 
		extends BinaryPredicate {

	public Then(int a, int b) 
			throws EugeneException {
		super(a, b);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA())
			.append(" ").append(RuleOperator.THEN).append(" ")
			.append((this.getB()));
		return sb.toString();
	}

	@Override
	public String getOperator() {
		return RuleOperator.THEN.toString();
	}

	@Override
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {

		int a = (int)this.getA();
		int b = (int)this.getB();
		int NR_OF_VARIABLES = variables.length;
		
		/*
		 * a THEN b
		 * 
		 * IF CONTAINS a THEN CONTAINS b
		 */

		for(int posA = 0; posA<NR_OF_VARIABLES; posA ++) {
			
			PrimitiveConstraint[] pcB = new PrimitiveConstraint[NR_OF_VARIABLES-1];
			for(int posB = 0, i=0; posB<NR_OF_VARIABLES; posB++) {
				if(posB != posA) {
					pcB[i++] = new XeqC(variables[Variables.PART][posB], b);
				}			
			}
			
			store.impose(new IfThen(
					new XeqC(variables[Variables.PART][posA], a),
					new Or(pcB)));
		}
		
		return null;
	}

}
