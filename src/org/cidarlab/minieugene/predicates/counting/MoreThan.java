package org.cidarlab.minieugene.predicates.counting;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.Count;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XgtC;
import JaCoP.constraints.XlteqC;
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
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {
		
		// a MORETHAN N
		IntVar count = (IntVar)store.findVariable(this.getA().getName()+"-counter");
		if(null == count) {
			count = new IntVar(store, 
					this.getA().getName()+"-counter", 
					0, 
					variables[Variables.PART].length);
		}

		store.impose(new Count(variables[Variables.PART], count, (int)this.getA().getId()));
		
		return new XgtC(count, this.getNum());
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables) 
				throws EugeneException {

		// a MORETHAN N
		IntVar count = (IntVar)store.findVariable(this.getA().getName()+"-counter");
		if(null == count) {
			count = new IntVar(store, 
					this.getA().getName()+"-counter", 
					0, 
					variables[Variables.PART].length);
		}

		store.impose(new Count(variables[Variables.PART], count, (int)this.getA().getId()));
		
		return new XlteqC(count, this.getNum());
	}
}
