package org.cidarlab.minieugene.predicates.positional.before;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.Constraint;
import JaCoP.constraints.IfThen;
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
public class SomeBefore 
	extends BinaryPredicate {
	
	public SomeBefore(int a, int b) {
		super(a, b);
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.SOME_BEFORE.toString();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append(this.getA())
				.append(" ").append(RuleOperator.SOME_BEFORE).append(" ")
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
		 * a SOME_BEFORE b
		 * 
		 * contains(a) && contains(b)
		 * => exists a: position(a) < position(b)
		 */
		
		/*
		 * we cannot place any b before a's last possible occurrence
		 * => a's last possible occurrence is at index N-1
		 */
		PrimitiveConstraint[] pcB = new PrimitiveConstraint[N];
		for(int j=0; j<N; j++) {
			pcB[j] = new XneqC(variables[Variables.PART][j], b);
		} 
		
		return new IfThen(
					new XeqC(variables[Variables.PART][N-1], a),
					new And(pcB));			
	}
		
}
