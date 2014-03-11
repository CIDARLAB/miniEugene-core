package org.cidarlab.minieugene.predicates;

import java.util.List;

import org.cidarlab.minieugene.constants.EugeneConstants;
import org.cidarlab.minieugene.exception.EugeneException;

import JaCoP.constraints.Constraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class LogicalOr 
	extends LogicalPredicate {

	public LogicalOr(List<Predicate> predicates) {
		super(LogicalOperator.OR, predicates);
	}
	
	
	@Override
	public String getOperator() {
		return EugeneConstants.OR;
	}

	@Override
	public Constraint toJaCoP(Store store, IntVar[][] variables)
			throws EugeneException {
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
