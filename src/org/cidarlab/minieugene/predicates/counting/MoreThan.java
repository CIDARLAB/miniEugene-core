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

/**
 * 
 * @author Ernst Oberortner
 */
public class MoreThan
	extends BinaryPredicate
	implements CountingPredicate {

	public MoreThan(Component a, int n) {				
		super(a, n);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA().getId())
			.append(" ").append(RuleOperator.MORETHAN).append(" ")
			.append(this.getNum());
		return sb.toString();
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.MORETHAN.toString();
	}

	@Override
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {
		
		// a MORETHAN N
		IntVar count = new IntVar(store, 
				this.getA().getId()+"_MORETHAN_"+this.getNum()+"-counter", 
				this.getNum()+1, 
				variables.length); 
		
		store.impose(new Count(variables[Variables.PART], count, (int)this.getA().getId()));
		
		return null;
	}

	@Override
	public Constraint toJaCoPNot(Store store, IntVar[][] variables) 
				throws EugeneException {

		// a NOTMORETHAN N
		IntVar count = new IntVar(store, 
				this.getA().getId()+"_NOTMORETHAN_"+this.getNum()+"-counter", 
				0, 
				this.getNum()); 
		store.impose(new Count(variables[Variables.PART], count, (int)this.getA().getId()));
		
		return null;
	}
}
