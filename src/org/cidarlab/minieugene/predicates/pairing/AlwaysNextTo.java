package org.cidarlab.minieugene.predicates.pairing;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.Or;
import JaCoP.constraints.Not;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.IfThen;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/**
 * 
 * @author Ernst Oberortner
 *
 */
public class AlwaysNextTo 
		extends BinaryPredicate
		implements PairingPredicate {

	public AlwaysNextTo(Component a, Component b) {
		super(a, b);
	}

	@Override	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA()).append(" ")
			.append(this.getOperator()).append(" ")
			.append(this.getB());
		return sb.toString();
	}

	@Override
	public String getOperator() {
		return RuleOperator.ALWAYS_NEXTTO.toString();
	}


	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {

		System.out.println("imposing "+this.toString());
		
		int N = variables[Variables.PART].length;
		int a = this.getA().getId();
		int b = this.getB().getId();
		
		PrimitiveConstraint[] pc = new PrimitiveConstraint[N];
		pc[0] = new IfThen(
					new XeqC(variables[Variables.PART][0], a),
					new XeqC(variables[Variables.PART][1], b));
		
		for(int i=1; i< N-1; i++) {
			pc[i] = new IfThen(
						new XeqC(variables[Variables.PART][i], a),
						new Or(
							new XeqC(variables[Variables.PART][i-1], b),
							new XeqC(variables[Variables.PART][i+1], b)));
		}

		pc[N-1] = new IfThen(
					new XeqC(variables[Variables.PART][N-1], a),
					new XeqC(variables[Variables.PART][N-1], b));
		
		return new And(pc);
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {

		return new Not(this.toJaCoP(store, variables));
	}

}
