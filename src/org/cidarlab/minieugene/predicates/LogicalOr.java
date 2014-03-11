package org.cidarlab.minieugene.predicates;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.minieugene.exception.EugeneException;

import JaCoP.constraints.Constraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class LogicalOr 
	extends LogicalPredicate {

	public LogicalOr() {
		super(LogicalOperator.OR, new ArrayList<Predicate>());
	}
	
	public LogicalOr(List<Predicate> predicates) {
		super(LogicalOperator.OR, predicates);
	}
	
	
	@Override
	public String getOperator() {
		return LogicalOperator.OR.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<this.getPredicates().size(); i++) {
			sb.append(this.getPredicates().get(i));
			if(i < this.getPredicates().size() - 1) {
				sb.append(" \\/ ");
			}
		}
		return sb.toString();
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

	@Override
	public int getSize() {
		return this.getPredicates().size();
	}

}
