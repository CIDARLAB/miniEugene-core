package org.cidarlab.minieugene.predicates;

import org.cidarlab.minieugene.exception.EugeneException;
import JaCoP.constraints.Constraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public interface Predicate {
	
	public String getOperator();
	
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
		throws EugeneException;
}
