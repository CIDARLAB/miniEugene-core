package org.cidarlab.minieugene.predicates.position.before;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.predicates.position.PositioningPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.Constraint;
import JaCoP.constraints.IfThen;
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
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
			throws EugeneException {
		
		int a = (int)this.getA().getId();
		int b = (int)this.getB().getId();
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

	@Override
	public Constraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		// TODO Auto-generated method stub
		return null;
	}
		
}
