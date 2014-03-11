package org.cidarlab.minieugene.predicates;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.minieugene.exception.EugeneException;

import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class LogicalAnd 
	extends LogicalPredicate {

	private int N;
	
	public LogicalAnd() {
		super(LogicalOperator.AND, new ArrayList<Predicate>());
		this.setN(-1);
	}
	
	public LogicalAnd(List<Predicate> predicates) {
		super(LogicalOperator.AND, predicates);
		this.setN(-1);
	}
	
	public void setN(int N) {
		this.N = N;
	}
	public int getN() {
		return this.N;		
	}
	
	
	@Override
	public String getOperator() {
		return LogicalOperator.AND.toString();
	}
	
	@Override
	public int getSize() {
		int n = 0;
		for(Predicate predicate:this.getPredicates()) {
			if(predicate instanceof LogicalOr) {
				n += ((LogicalOr)predicate).getSize();
			} else {
				n++;
			}
		}
		return n;
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

}
