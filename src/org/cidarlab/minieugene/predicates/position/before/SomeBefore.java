package org.cidarlab.minieugene.predicates.position.before;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.predicates.position.PositioningPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XneqC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/**
 * 
 * @author Ernst Oberortner
 *
 */
public class SomeBefore 
	extends BinaryPredicate 
	implements PositioningPredicate {
	
	public SomeBefore(Component a, Component b) {
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
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
			throws EugeneException {
		
		int a = (int)this.getA().getId();
		int b = (int)this.getB().getId();
		int N = variables[Variables.PART].length;

		/*
		 * a SOME_BEFORE b
		 * 
		 * contains(a) && contains(b) => 
		 *     exists a: position(a) < position(b)
		 */
		
		PrimitiveConstraint pc[] = new PrimitiveConstraint[N];
		
		// a can appear at the first position
		pc[0] = new XeqC(variables[Variables.PART][0], a);
 
		for(int i=1; i<N; i++) {

			// if a appears at the i-th position,
			// then at least one element after a must be b 
			
			PrimitiveConstraint[] pcB = new PrimitiveConstraint[N-i];
			for(int j=i; j<N; j++) {
				pcB[j-i] = new XeqC(variables[Variables.PART][j], b);
			}
			
			pc[i] = new And(
						new XeqC(variables[Variables.PART][i], a),
						new Or(pcB));
		}			

		return new Or(pc);

//		return new And(
//				new Or(pc),
//				// a cannot appear at the last position
//				new XneqC(variables[Variables.PART][N-1], a));

	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		return new Not(this.toJaCoP(store, variables));
	}
		
}
