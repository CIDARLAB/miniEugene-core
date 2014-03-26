package org.cidarlab.minieugene.predicates;

import org.cidarlab.minieugene.exception.EugeneException;

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
	 * @throws EugeneException
	 */
	public abstract PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
		throws EugeneException;
	
	/**
	 * 
	 * @param store
	 * @param variables
	 * @return
	 * @throws EugeneException
	 */
	public abstract PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException;
	
}
