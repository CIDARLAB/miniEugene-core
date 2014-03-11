package org.cidarlab.minieugene.predicates.orientation;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.UnaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.Constraint;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XneqC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/*
 * ALTERNATE a
 *
 * i in {1, ..., N-1}
 * for all i: orientation(i) != orientation(i+1)
 * 
 */
public class Alternate 
	extends UnaryPredicate 
	implements OrientationPredicate {

	public Alternate(Component rhs) {
		super(rhs);
	}

	@Override
	public String getOperator() {
		return RuleOperator.ALL_REVERSE.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(RuleOperator.ALL_REVERSE);
		return sb.toString();
	}

	@Override
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {
		
		int N = variables[Variables.ORIENTATION].length;
		
		/*
		 * 
		 * IF orientation(i) == 1 THEN orientation(i+1) != 1 
		 * OR
		 * IF orientation(i) == -1 THEN orientation(i+1) != -1
		 * 
		 * 0 <= i < N
		 */
		PrimitiveConstraint pcForward[] = new PrimitiveConstraint[N];
		for(int i=0; i<N; i++) {
			if(i%2==0) {
				pcForward[i] = new XeqC(variables[Variables.ORIENTATION][i], 1);				
			} else {
				pcForward[i] = new XneqC(variables[Variables.ORIENTATION][i], 1);
			}
		}
		PrimitiveConstraint pcReverse[] = new PrimitiveConstraint[N];
		for(int i=0; i<N; i++) {
			if(i%2==0) {
				pcReverse[i] = new XeqC(variables[Variables.ORIENTATION][i], -1);				
			} else {
				pcReverse[i] = new XneqC(variables[Variables.ORIENTATION][i], -1);
			}
		}
		
		return new Or(new And(pcForward), new And(pcReverse));
	}

	@Override
	public Constraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		return new Not((PrimitiveConstraint)this.toJaCoP(store, variables));
	}
	
}
