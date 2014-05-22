package org.cidarlab.minieugene.predicates.position.before;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.predicates.counting.Contains;
import org.cidarlab.minieugene.predicates.orientation.AllForward;
import org.cidarlab.minieugene.predicates.orientation.AllReverse;
import org.cidarlab.minieugene.predicates.position.PositioningPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.IfThenElse;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XltY;
import JaCoP.constraints.XneqC;
import JaCoP.constraints.XplusCeqZ;
import JaCoP.core.IntVar;
import JaCoP.core.Store;


/**
 * 
 * @author Ernst Oberortner
 *
 */
public class AllBefore 
		extends BinaryPredicate 
		implements PositioningPredicate {

	public AllBefore(Component a, Component b) {
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
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {

		/*
		 * a ALL_BEFORE b
		 * 
		 * contains(a) /\ contains (b) => 
		 * 		for all a, b: position(a) < position(b)
		 * otherwise => TRUE
		 */
		
		Contains containsA = new Contains(this.getA());
		Contains containsB = new Contains(this.getB());

		// a is FORWARD oriented
		PrimitiveConstraint a_before_b = this.before(
				this.getA(), this.getB(), store, variables);
//		PrimitiveConstraint b_before_a = this.before(
//				this.getB(), this.getA(), store, variables);
		

		// contains a /\ contains b =>
		//     for all a,b: position(a) < position(b)
		
		return a_before_b;

//		return new And(
//				new IfThen(
//						new AllForward(this.getA()).toJaCoP(store, variables),
//						a_before_b),
//				new IfThen(
//						new AllReverse(this.getA()).toJaCoP(store, variables),
//						b_before_a)
//				);
	}

	
	private PrimitiveConstraint before(Component a, Component b,
			Store store, IntVar[][] variables) 
			throws MiniEugeneException {
		
		int N = variables[Variables.PART].length;
		
//		/*
//		 * POSITIONING VARIABLES
//		 */
//		IntVar aPosVar = (IntVar)store.findVariable(a.getName()+".position");
//		if(null == aPosVar) {
//			aPosVar = new IntVar(store, a.getName()+".position", 0, N-1);
//		}
//		IntVar bPosVar = (IntVar)store.findVariable(b.getName()+".position");
//		if(null == bPosVar) {
//			bPosVar = new IntVar(store, b.getName()+".position", 0, N-1);
//		}
//		// TODO:
//		// work with Distance constraints!
//		store.impose(
//				new XltY(aPosVar, bPosVar));
//
//		return null;
		
		Contains containsA = new Contains(this.getA());

		PrimitiveConstraint pc[] = new PrimitiveConstraint[N];
		pc[0] = new IfThen(
					containsA.toJaCoP(store, variables),
//					new XeqC(variables[Variables.PART][0], a.getId()),
					new XneqC(variables[Variables.PART][0], b.getId()));

		for(int i=1; i<N; i++) {

			// b cannot appear before a
			PrimitiveConstraint[] pcB = new PrimitiveConstraint[i];
			for(int j=0; j<i; j++) {
				pcB[j] = new XneqC(variables[Variables.PART][j], b.getId());
			}

			pc[i] = new IfThen(
						new XeqC(variables[Variables.PART][i], a.getId()),
						new And(pcB));
		}
		
		return new And(pc);
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		return new Not(
				this.toJaCoP(store, variables));
	}
	
}
