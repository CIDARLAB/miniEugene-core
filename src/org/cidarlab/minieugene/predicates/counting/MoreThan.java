package org.cidarlab.minieugene.predicates.counting;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.solver.jacop.Variables;
import org.cidarlab.minieugene.symbol.SymbolTables;

import JaCoP.constraints.Constraint;
import JaCoP.constraints.Count;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.Reified;
import JaCoP.constraints.XeqC;
import JaCoP.core.BooleanVar;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class MoreThan 
	extends CountingPredicate {

	public MoreThan(int a, int n) {				
		super(a, n);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA())
			.append(" ").append(RuleOperator.MORETHAN).append(" ")
			.append(this.getN());
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
		IntVar count = new IntVar(store, this.getA()+"_MORETHAN_"+this.getN()+"-counter", this.getN()+1, variables.length); 
		store.impose(new Count(variables[Variables.PART], count, (int)this.getA()));
		
		return null;
	}

	@Override
	public Constraint toJaCoPNot(Store store, IntVar[][] variables) 
				throws EugeneException {

		// a NOTMORETHAN N
		IntVar count = new IntVar(store, this.getA()+"_NOTMORETHAN_"+this.getN()+"-counter", 0, this.getN()); 
		store.impose(new Count(variables[Variables.PART], count, (int)this.getA()));
		
		return null;
	}
}
