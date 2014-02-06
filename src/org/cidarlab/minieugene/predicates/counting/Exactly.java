package org.cidarlab.minieugene.predicates.counting;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.Constraint;
import JaCoP.constraints.Count;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class Exactly 
	extends CountingPredicate {

	public Exactly(int a, int n) 
			throws EugeneException {				
		super(a, n);

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA())
			.append(" ").append(RuleOperator.EXACTLY).append(" ")
			.append(this.getN());
		return sb.toString();
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.EXACTLY.toString();
	}

	@Override
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {

		// a EXACTLY N
		IntVar count = new IntVar(store, this.getA()+"_EXACTLY_"+this.getN()+"-counter", this.getN(), this.getN()); 
		store.impose(new Count(variables[Variables.PART], count, (int)this.getA()));
		
		return null;
	}

	@Override
	public Constraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		/*
		 * NOT a EXACTLY N
		 */

		IntVar count1 = new IntVar(store, this.getA()+"_NOT-EXACTLY_"+this.getN()+"-counter1", 0, this.getN()-1); 
		store.impose(new Count(variables[Variables.PART], count1, (int)this.getA()));
		
		IntVar count2 = new IntVar(store, this.getA()+"_NOT-EXACTLY_"+this.getN()+"-counter2", this.getN()+1, variables.length); 
		store.impose(new Count(variables[Variables.PART], count2, (int)this.getA()));
		
		return null;
	}

}
