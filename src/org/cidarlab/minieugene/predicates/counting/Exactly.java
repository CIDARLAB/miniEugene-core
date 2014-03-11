package org.cidarlab.minieugene.predicates.counting;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.Count;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XneqC;
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
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {

		// a EXACTLY N
		IntVar count = (IntVar)store.findVariable(this.getA().getName()+"_EXACTLY_"+this.getNum()+"-counter");
		if(null == count) {
			count = new IntVar(store, 
					this.getA().getName()+"_EXACTLY_"+this.getNum()+"-counter", 
					this.getNum(), 
					this.getNum());
		}
		store.impose(new Count(variables[Variables.PART], count, (int)this.getA().getId()));
		
		return new XeqC(count, this.getNum());
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		// a NOT EXACTLY N
		IntVar count = (IntVar)store.findVariable(this.getA().getName()+"_EXACTLY_"+this.getNum()+"-counter");
		if(null == count) {
			count = new IntVar(store, 
					this.getA().getName()+"_EXACTLY_"+this.getNum()+"-counter", 
					0, 
					Integer.MAX_VALUE);
		}
		store.impose(new Count(variables[Variables.PART], count, (int)this.getA().getId()));
		
		return new XneqC(count, this.getNum());
	}

}
