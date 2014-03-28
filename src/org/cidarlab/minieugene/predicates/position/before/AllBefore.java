package org.cidarlab.minieugene.predicates.position.before;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
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
import JaCoP.constraints.XneqC;
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
				throws EugeneException {

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
			throws EugeneException {
		
		int N = variables[Variables.PART].length;
		
		Contains containsA = new Contains(this.getA());

		PrimitiveConstraint pc[] = new PrimitiveConstraint[N];
		pc[0] = new IfThen(
					containsA.toJaCoP(store, variables),
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
			throws EugeneException {
		return new Not(this.toJaCoP(store, variables));
	}
	
	/*
	 * TODO:
	 * check out the following two methods to integrate orientation 
	 * into positioning rules 
	 */
	
//  private PrimitiveConstraint[] PBeforeG_Forward(Store store, IntVar[][] variables) {
//
//  	int N = variables[Variables.TYPE].length;
//
//  	PrimitiveConstraint[] pc = new PrimitiveConstraint[N-1];
//  	for(int i=1; i<N; i++) {
//
//	    	PrimitiveConstraint[] upstreamPromoter = new PrimitiveConstraint[i];
//			PrimitiveConstraint[] noInterveningTerminator = new PrimitiveConstraint[i];
//			
//			for(int j=0; j<i; j++) {				
//	    		upstreamPromoter[j] = new XeqC(variables[Variables.PART][j], this.getA().getId()); 
//
//	    		noInterveningTerminator[j] = new XneqC(variables[Variables.TYPE][j], PartTypes.get("TERMINATOR"));
//			}
//
//			pc[i-1] = new IfThen(
//							new And(
//								new XeqC(variables[Variables.PART][i], this.getB().getId()),
//								new Or(upstreamPromoter)),
//							new And(noInterveningTerminator));
//  	}
//  	
//  	return pc;
//  }
  
//  private PrimitiveConstraint[] PAfterG_Reverse(Store store, IntVar[][] variables) {
//  	
//  	int N = variables[Variables.TYPE].length;
//  	
//  	PrimitiveConstraint[] pc = new PrimitiveConstraint[N-1];
//  	for(int i=0; i<N-1; i++) {
//
//	    	PrimitiveConstraint[] upstreamPromoter = new PrimitiveConstraint[N-i];
//			
//			for(int j=i; j<N; j++) {				
//	    		upstreamPromoter[j-i] = new XeqC(variables[Variables.PART][j], this.getA().getId());
//			}
//			
//  		if(i != N-1) {
//  			PrimitiveConstraint[] noInterveningTerminator = new PrimitiveConstraint[N-i-1];
//  			for(int j=i; j<N-1; j++) {
//  	    		noInterveningTerminator[j-i] = new XneqC(variables[Variables.TYPE][j], 4);
//  			}
//  			
//  			pc[i] = new IfThen(
//  						new XeqC(variables[Variables.PART][i], this.getB().getId()),
//  						new And(new Or(upstreamPromoter),
//  								new And(noInterveningTerminator)));
//  		} else {
//  			pc[i] = new IfThen(
//							new XeqC(variables[Variables.PART][i], this.getB().getId()),
//							new Or(upstreamPromoter));
//  		}
//  	}
//  	
//  	return pc;
//  }

}
