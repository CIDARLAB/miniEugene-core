package org.cidarlab.minieugene.predicates.position;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.Not;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XeqY;
import JaCoP.constraints.XneqC;
import JaCoP.constraints.XneqY;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/**
 * [i] EQUALS [j] ... the elements at index i and j must be equal regarding name and type
 * [i] EQUALS p   ... the element at index i must be p
 * 
 * @author Ernst Oberortner
 *
 */
public class Equals 
	extends BinaryPredicate 
	implements PositioningPredicate {
	
	private int i;
	private int j;
	
	public Equals(int i, int j) {
		super(null, null);
		this.i = i;
		this.j = j;
	}
	
	public Equals(int i, Component a) {
		super(a, null);
		this.i = i;
		this.j = -1;		
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.EQUALS.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables)
			throws EugeneException {
		
		if(-1 == this.j) {
			return new XeqC(variables[Variables.PART][i], this.getA().getId());
		}
		
		return new XeqY(variables[Variables.PART][i], variables[Variables.PART][j]);
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		
		if(-1 == this.j) {
			return new XneqC(variables[Variables.PART][i], this.getA().getId());
		}
		
		return new XneqY(variables[Variables.PART][i], variables[Variables.PART][j]);
	}

}
