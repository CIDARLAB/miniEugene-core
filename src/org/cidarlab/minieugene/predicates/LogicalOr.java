package org.cidarlab.minieugene.predicates;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.counting.CountingPredicate;

import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.Reified;
import JaCoP.constraints.XeqC;
import JaCoP.core.BooleanVar;
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
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables)
			throws EugeneException {
		
		int i = 0;
		PrimitiveConstraint[] pc = new PrimitiveConstraint[this.getPredicates().size()];
		for(Predicate predicate : this.getPredicates()) {
			pc[i++] = predicate.toJaCoP(store, variables);
		}
		
		return new Or(pc);
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		int i = 0;
		PrimitiveConstraint[] pc = new PrimitiveConstraint[this.getPredicates().size()];
		for(Predicate predicate : this.getPredicates()) {
			pc[i++] = predicate.toJaCoPNot(store, variables);
		}
		
		return new Or(pc);
	}

	@Override
	public int getMinimumLength() {
		int minN = Integer.MAX_VALUE; 
		for(Predicate p : this.getPredicates()) {
			if(p instanceof CountingPredicate) {
				
				int pN = ((CountingPredicate)p).getMinimumLength();
				
				if (pN < minN) {
					minN = pN;
				}
			} else if(p instanceof LogicalPredicate) {
				int pN = ((LogicalPredicate)p).getMinimumLength();				
				if (pN < minN) {
					minN = pN;
				}
			}
		}
		return minN;
	}

	@Override
	public int getNumberOfRules() {
		return this.getPredicates().size();
	}

}
