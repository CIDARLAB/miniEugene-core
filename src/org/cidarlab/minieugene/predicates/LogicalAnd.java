package org.cidarlab.minieugene.predicates;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.counting.CountingPredicate;

import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class LogicalAnd 
	extends LogicalPredicate {

	private int minN;
	private int maxN;
	
	public LogicalAnd() {
		super(LogicalOperator.AND, new ArrayList<Predicate>());
		this.setMinN(-1);
	}
	
	public LogicalAnd(List<Predicate> predicates) {
		super(LogicalOperator.AND, predicates);
		this.setMinN(-1);
	}
	
	public void setMinN(int minN) {
		this.minN = minN;
	}
	
	public int getMinN() {
		if(this.minN == -1) {
			this.minN = this.getMinimumLength();
		}
		return minN;
	}

	public void setMaxN(int maxN) {
		this.maxN = maxN;
	}
	
	public int getMaxN() {
		return this.maxN;		
	}
	
	
	@Override
	public String getOperator() {
		return LogicalOperator.AND.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i=0;
		for(Predicate predicate : this.getPredicates()) {
			if(predicate instanceof LogicalPredicate) {
				sb.append(" ( ").append(predicate).append(" ) "); 
			} else {
				sb.append(predicate);
			}
			
			if((++i) < this.getPredicates().size()) {
				sb.append(" /\\ ");
			}
		}
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables)
			throws EugeneException {
		System.out.println("[toJaCop] -> "+this.toString());
		return null;
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMinimumLength() {
		int minN = 0;
		for(Predicate p : this.getPredicates()) {
			if(p instanceof LogicalPredicate) {
				minN += ((LogicalPredicate)p).getMinimumLength();
			} else if(p instanceof CountingPredicate) {
				minN += ((CountingPredicate)p).getMinimumLength();
			}
		}
		return minN;
	}

	@Override
	public int getNumberOfRules() {
		int n = 0;
		for(Predicate p : this.getPredicates()) {
			if(p instanceof LogicalPredicate) {
				n += ((LogicalPredicate)p).getNumberOfRules();
			} else {
				n++;
			}
		}
		return n;
	}

}
