package org.cidarlab.minieugene.predicates.orientation;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

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

		System.out.println("IMPOSING "+this.toString());
		
		// a SAME_ORIENTATION b <=>
		//     ALL_FORWARD a /\ ALL_FORWARD b  \/
		//     ALL_REVERSE a /\ ALL_REVERSE b
		
		new AllForward(this.getA());
		new AllReverse(this.getB());
		
		for(int i=0; i<variables[Variables.ORIENTATION].length; i++) {
			
		}
		return null;
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		// TODO Auto-generated method stub
		return null;
	}

}
