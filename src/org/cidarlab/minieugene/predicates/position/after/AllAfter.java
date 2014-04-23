package org.cidarlab.minieugene.predicates.position.after;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.predicates.position.PositioningPredicate;
import org.cidarlab.minieugene.predicates.position.before.AllBefore;

import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;



/**
 * A ALL_AFTER B 
 * 
 * 
 * @author Ernst Oberortner
 *
 */
public class AllAfter 
		extends BinaryPredicate 
		implements PositioningPredicate {

	private AllBefore before;
	
	public AllAfter(Component a, Component b) {
		super(a, b);
		this.before = new AllBefore(b, a);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA().getName())
			.append(" ").append(RuleOperator.ALL_AFTER).append(" ")
			.append(this.getB().getName());

		return sb.toString();
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.ALL_AFTER.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
			throws EugeneException {
		return this.before.toJaCoP(store, variables);
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		return this.before.toJaCoPNot(store, variables);
	}
}

