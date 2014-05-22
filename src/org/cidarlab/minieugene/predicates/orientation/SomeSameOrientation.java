package org.cidarlab.minieugene.predicates.orientation;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;

import JaCoP.constraints.And;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/*
 * a SAME_ORIENTATION b
 * 
 */
public class SomeSameOrientation 
	extends BinaryPredicate 
	implements OrientationPredicate {

	public SomeSameOrientation(Component a, Component b) {
		super(a, b);
	}

	@Override
	public String getOperator() {
		return RuleOperator.SOME_SAME_ORIENTATION.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();		
		sb.append(this.getA().getName()).append(" ")
			.append(this.getOperator()).append(" ")
			.append(this.getB().getName());
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {

		System.out.println("IMPOSING "+this.toString());
		
		// the number of forward oriented a's and the number of forward oriented b's
		// MUST be >= 1
		// OR
		// the number of reverse oriented a's and the number of reverse oriented b's
		// MUST be >= 1
		SomeForward sfa = new SomeForward(this.getA());
		SomeForward sfb = new SomeForward(this.getB());
		
		SomeReverse sra = new SomeReverse(this.getA());
		SomeReverse srb = new SomeReverse(this.getB());
		
		// a SOME_SAME_ORIENTATION b <=>
		//     SOME_FORWARD a /\ SOME_FORWARD b  \/
		//     SOME_REVERSE a /\ SOME_REVERSE b
		
		return new And(
				new And(sfa.toJaCoP(store, variables), sfb.toJaCoP(store, variables)),
				new And(sra.toJaCoP(store, variables), srb.toJaCoP(store, variables)));
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		
		return new Not(this.toJaCoP(store, variables));
	}

}
