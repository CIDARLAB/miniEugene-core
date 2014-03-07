package org.cidarlab.minieugene.predicates.direction;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.Constraint;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.XeqC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/*
 * ALTERNATE
 *
 * i in {1, ..., N-1}
 * for all i: orientation(i) != orientation(i+1)
 * 
 */
public class Alternate 
	extends OrientationPredicate {

	public Alternate(int a) {
		super(a);
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
		if(this.getA() == -1) {
			for(int i=0; i<variables[Variables.ORIENTATION].length; i++) {
				store.impose(new XeqC(variables[Variables.ORIENTATION][i], -1));
			}
		} else {
			for(int i=0; i<variables[Variables.ORIENTATION].length; i++) {
				store.impose(
						new IfThen(
								new XeqC(variables[Variables.PART][i], this.getA()),
								new XeqC(variables[Variables.ORIENTATION][i], -1)));
			}
		}
		
		return null;
	}

	
	@Override
	public Constraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		if(this.getA() == -1) {
			for(int i=0; i<variables[Variables.ORIENTATION].length; i++) {
				store.impose(new XeqC(variables[Variables.ORIENTATION][i], 1));
			}
		} else {
			for(int i=0; i<variables[Variables.ORIENTATION].length; i++) {
				store.impose(
						new IfThen(
								new XeqC(variables[Variables.PART][i], this.getA()),
								new XeqC(variables[Variables.ORIENTATION][i], 1)));
			}
		}
		
		return null;
	}

}
