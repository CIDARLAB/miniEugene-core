package org.cidarlab.minieugene.predicates.orientation;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;

import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;
import JaCoP.constraints.Or;
import JaCoP.constraints.And;
import JaCoP.constraints.Not;

/*
 * a SAME_ORIENTATION b
 * 
 */
public class AllSameOrientation 
	extends BinaryPredicate 
	implements OrientationPredicate {

	public AllSameOrientation(Component a, Component b) {
		super(a, b);
	}

	@Override
	public String getOperator() {
		return RuleOperator.SAME_ORIENTATION.toString();
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
				throws EugeneException {

		AllForward afa = new AllForward(this.getA());
		AllForward afb = new AllForward(this.getB());
		
		AllReverse ara = new AllReverse(this.getA());
		AllReverse arb = new AllReverse(this.getB());
		
		// a SAME_ORIENTATION b <=>
		//     ALL_FORWARD a /\ ALL_FORWARD b  \/
		//     ALL_REVERSE a /\ ALL_REVERSE b
		
		return new Or(
				new And(afa.toJaCoP(store, variables), afb.toJaCoP(store, variables)),
				new And(ara.toJaCoP(store, variables), arb.toJaCoP(store, variables)));
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		return new Not(this.toJaCoP(store, variables));
	}

}
