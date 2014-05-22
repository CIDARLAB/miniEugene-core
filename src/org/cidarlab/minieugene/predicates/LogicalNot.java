package org.cidarlab.minieugene.predicates;

import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.counting.Contains;
import org.cidarlab.minieugene.predicates.counting.CountingPredicate;
import org.cidarlab.minieugene.predicates.counting.Exactly;
import org.cidarlab.minieugene.predicates.counting.MoreThan;

import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;


public class LogicalNot 
	extends LogicalPredicate {

	/**
	 * 
	 * @param predicate
	 */
	public LogicalNot(Predicate predicate) {
		super(LogicalOperator.NOT, predicate);
	}
	
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("NOT ").append(this.getPredicates()).append("");
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		return this.getPredicates().get(0).toJaCoPNot(store, variables);
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMinimumLength() {
		if(this.getPredicates().get(0) instanceof Contains) {
			// NOT CONTAINS -> 0
			return 0;
		} else if(this.getPredicates().get(0) instanceof MoreThan) {
			
			int n = ((MoreThan)this.getPredicates().get(0)).getMinimumLength();
			// NOT MORETHAN n
			if(n == 0) {
				return 0;
			}
			return 1;
			
		} else if(this.getPredicates().get(0) instanceof Exactly) {
			
			int n = ((Exactly)this.getPredicates().get(0)).getMinimumLength();

			if(n == 1) {
				return 0;
			}
			return 1;
		}
		
		return 0;
	}

	@Override
	public int getNumberOfRules() {
		return 1;
	}
	
}
