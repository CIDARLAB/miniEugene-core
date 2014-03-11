package org.cidarlab.minieugene.predicates.counting;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.Constraint;
import JaCoP.constraints.Count;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class Exactly
	extends BinaryPredicate
	implements CountingPredicate {

	public Exactly(Component a, int num) 
			throws EugeneException {				
		super(a, num);

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA())
			.append(" ").append(RuleOperator.EXACTLY).append(" ")
			.append(this.getNum());
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
		IntVar count = new IntVar(store, this.getA()+"_EXACTLY_"+this.getNum()+"-counter", this.getNum(), this.getNum()); 
		store.impose(new Count(variables[Variables.PART], count, (int)this.getA().getId()));
		
		return null;
	}

	@Override
	public Constraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		/*
		 * NOT a EXACTLY N
		 */

		IntVar count1 = new IntVar(store, this.getA()+"_NOT-EXACTLY_"+this.getNum()+"-counter1", 0, this.getNum()-1); 
		store.impose(new Count(variables[Variables.PART], count1, (int)this.getA().getId()));
		
		IntVar count2 = new IntVar(store, this.getA()+"_NOT-EXACTLY_"+this.getNum()+"-counter2", this.getNum()+1, variables.length); 
		store.impose(new Count(variables[Variables.PART], count2, (int)this.getA().getId()));
		
		return null;
	}

}
