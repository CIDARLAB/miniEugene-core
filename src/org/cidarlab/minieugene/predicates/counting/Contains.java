package org.cidarlab.minieugene.predicates.counting;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.UnaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.Constraint;
import JaCoP.constraints.Count;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/*
 * ? CONTAINS B
 */
		
public class Contains
		extends UnaryPredicate
		implements CountingPredicate {

	public Contains(Component a) {
		super(a);
	}
	
	@Override
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {
		
		/*
		 * CONTAINS B
		 */
		IntVar counter = new IntVar(store, "CONTAINS_"+this.getA()+"-counter", 1, variables[Variables.PART].length); 
		return new Count(variables[Variables.PART], counter, this.getA().getId());
	}
	
	@Override
	public Constraint toJaCoPNot(
			Store store, IntVar[][] variables) 
				throws EugeneException {
		/*
		 * NOT CONTAINS B
		 */

		IntVar counter = new IntVar(store,"NOTCONTAINS_"+this.getA()+"-counter", 0, 0); 
		return new Count(variables[Variables.PART], counter, (int)this.getA().getId());
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
}
