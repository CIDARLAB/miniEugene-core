package org.cidarlab.minieugene.predicates.pairing;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;

import JaCoP.constraints.Constraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/**
 * 
 * @author Ernst Oberortner
 *
 */
public class Equals 
	extends BinaryPredicate 
	implements PairingPredicate {
	
	private int i;
	private int j;
	
	public Equals(int i, int j) {
		super(null, null);
		this.i = i;
		this.j = j;
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.EQUALS.toString();
	}

	@Override
	public Constraint toJaCoP(Store store, IntVar[][] variables)
			throws EugeneException {
		System.out.println("imposing "+i+" EQUALS "+j);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Constraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		// TODO Auto-generated method stub
		return null;
	}

}
