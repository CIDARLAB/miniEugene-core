package org.cidarlab.minieugene.predicates.position.nextto;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.predicates.position.PositioningPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XplusCeqZ;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/*
 * A NEXTTO B
 * 
 * Definition:  
 */
public class AllNextTo 
	extends BinaryPredicate 
	implements PositioningPredicate {

	public AllNextTo(Component a, Component b) {
		super(a, b);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA().getName())
			.append(" ").append(RuleOperator.ALL_NEXTTO).append(" ")
			.append(this.getB().getName());
		return sb.toString();
	}

	@Override
	public String getOperator() {
		return RuleOperator.ALL_NEXTTO.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {
		
		int a = (int)this.getA().getId();
		int b = (int)this.getB().getId();
		int N = variables[Variables.PART].length;
		
		if(N <= 1) {
			throw new MiniEugeneException("I cannot impose "+this.toString()+"! Invalid length of design!");
		}

		int[] idxA = new int[N];
		for(int i=0; i<N; i++) {
			idxA[i] = i;
		}

		/*
		 * a NEXTTO b
		 */
		PrimitiveConstraint[] pc = 
				constraintIndices(variables[Variables.PART], idxA, a, b);

		return new And(pc);
	}

	/*
	 * a NEXTTO b
	 */
	private static PrimitiveConstraint[] constraintIndices(IntVar[] parts, int[] indices, int a, int b) {
		int N = parts.length;
		PrimitiveConstraint[] pc = new PrimitiveConstraint[indices.length];
		for(int i=0; i<indices.length; i++) {
			int idx = indices[i];
			if(idx > 0 && idx<N-1) {
				
				/*
				 * if a is placed at any position (except the first and the last position),
				 * then place b either at the position immediately before or 
				 *                     at the position immediately after
				 */
				pc[i] = new IfThen(
							new XeqC(parts[idx], a),
							new Or(
								new XeqC(parts[idx-1], b),
								new XeqC(parts[idx+1], b)));
			} else if(idx == 0) {
				
				/*
				 * if a is placed at the first position,
				 * then place b at the second position
				 */
				pc[i] = new IfThen(
							new XeqC(parts[idx], a),
							new XeqC(parts[idx+1], b));
			} else if(idx == N-1) {
				
				/*
				 * if a is placed at the last position,
				 * then place b at the second last position
				 */
				pc[i] = new IfThen(
							new XeqC(parts[idx], a),
							new XeqC(parts[idx-1], b));
			}
		}
		
		return pc;
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		return new Not(this.toJaCoP(store, variables));
	}
	
}
