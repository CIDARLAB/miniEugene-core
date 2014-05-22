package org.cidarlab.minieugene.predicates;

import org.cidarlab.minieugene.exception.MiniEugeneException;

import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/**
 *
 * @author Ernst Oberortner
 */
public abstract class Predicate {
	
	/**
	 * 
	 * @return
	 */
	public abstract String getOperator();
	
	/**
	 * 
	 * @param store
	 * @param variables
	 * @return
	 * @throws MiniEugeneException
	 */
	public abstract PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
		throws MiniEugeneException;
	
	/**
	 * 
	 * @param store
	 * @param variables
	 * @return
	 * @throws MiniEugeneException
	 */
	public abstract PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException;
	
}
