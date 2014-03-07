package org.cidarlab.minieugene.predicates.positional.before;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.Constraint;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XneqC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/* A BEFORE B 
 * 
 * IF the long[] array, that the evaluate() method receives, CONTAINS A and B, THEN 
 *     A's first occurrence must be before B's first occurrence
 * ELSE
 *     A BEFORE B is true
 * END IF
 * 
 * Note:
 * rules like ``All A's must occur BEFORE all B's'' can be achieved 
 * by using Eugene's new ``FOR ALL'' operator...
 */
public class AllBefore 
		extends BinaryPredicate {

	public AllBefore(int a, int b) {
		super(a, b);
	}

	@Override
	public String getOperator() {
		return RuleOperator.ALL_BEFORE.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append(this.getA())
				.append(" ").append(RuleOperator.ALL_BEFORE).append(" ")
				.append(this.getB());
		} catch(Exception e) {
			// do nothing...
		}
		return sb.toString();
	}

	@Override
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {

		int a = (int)this.getA();
		int b = (int)this.getB();

		int N = variables[Variables.PART].length;

		/*
		 * a ALL_BEFORE b
		 * 
		 * contains(a) /\ contains (b) => 
		 * 		for all a, b: position(a) < position(b)
		 * otherwise => TRUE
		 */

		// a is FORWARD oriented
		PrimitiveConstraint pc[] = new PrimitiveConstraint[N-1];
		for(int i=1; i<N; i++) {
			if(i > 0) {
				PrimitiveConstraint[] pcB = new PrimitiveConstraint[i];
				for(int j=0; j<i; j++) {
					pcB[j] = new XneqC(variables[Variables.PART][j], b);
				}
				
				pc[i-1] = new IfThen(
							new And(new XeqC(variables[Variables.ORIENTATION][i], 1),
									new XeqC(variables[Variables.PART][i], a)),
							new And(pcB));
			} else {
				
				pc[i] = new IfThen(
							new And(new XeqC(variables[Variables.ORIENTATION][i], 1),
									new XeqC(variables[Variables.PART][i], a)),
							new XneqC(variables[Variables.PART][i], b));
			}							
		}			

		store.impose(new And(pc));
		
		pc = new PrimitiveConstraint[N-1];
		for(int i=1; i<N; i++) {
			if(i > 0) {
				PrimitiveConstraint[] pcB = new PrimitiveConstraint[i];
				for(int j=0; j<i; j++) {
					pcB[j] = new XeqC(variables[Variables.PART][j], b);
				}
				
				pc[i-1] = new IfThen(
							new And(new XeqC(variables[Variables.ORIENTATION][i], -1),
									new XeqC(variables[Variables.PART][i], a)),
							new Or(pcB));
			} else {
				
				pc[i] = new IfThen(
							new And(new XeqC(variables[Variables.ORIENTATION][i], -1),
									new XeqC(variables[Variables.PART][i], a)),
							new XneqC(variables[Variables.PART][i], b));
			}							
		}			

		//		return null;
		return new And(pc);
	}
	
}
