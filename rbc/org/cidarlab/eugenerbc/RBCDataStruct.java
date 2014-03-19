package org.cidarlab.eugenerbc;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.predicates.LogicalPredicate;
import org.cidarlab.minieugene.predicates.Predicate;
import org.cidarlab.minieugene.predicates.counting.Exactly;

public class RBCDataStruct {
	private LogicalPredicate lp;
	private int N;
	private int L;
	
	public RBCDataStruct(LogicalPredicate lp, int N, int L) {
		this.lp = lp;
		this.N = N;
		this.L = L;
	}
	
	public LogicalPredicate getLP() {
		return this.lp;
	}
	
	public int getN() {
		return this.N;
	}
	
	public int getL() {
		return this.L;
	}
	
	public long getCDSCount() throws EugeneRBCException {
		if(this.hasSingleRule()) {
			Predicate p = this.lp.getPredicates().get(0);
			if(p.getOperator() == RuleOperator.EXACTLY.toString()) {
				return this.calculateExactlyCount();
			} else {
				throw new EugeneRBCException("Cannot yet calculate this instance");
			}
		}
		else if(this.hasAllContainsRules()) {
			return this.calculateContains();
		} else {
			throw new EugeneRBCException("Cannot yet calculate this instance");
		}
	}
	
	private long calculateExactlyCount() {
		int num = ((Exactly) this.lp.getPredicates().get(0)).getNum();
		return Combinatorics.nCr(this.getN(), num) * (long) Math.pow(this.L - 1, this.N - num);
	}
	
	private boolean hasSingleRule() {
		return this.lp.getPredicates().size() == 1;
	}
	
	private boolean hasAllContainsRules() {
		for(Predicate p : this.lp.getPredicates()) {
			if(p.getOperator() != RuleOperator.CONTAINS.toString()) {
				return false;
			}
		}
		return true;
	}
	
	private long calculateContains() throws EugeneRBCException {
		int numRules = this.lp.getSize();
		if(numRules > this.N || numRules > this.L) {
			return 0;
		} else {
			throw new EugeneRBCException("Contains not fully implemented");
		}
	}
	
	private long containsIntegrand(int[] ind, int numRules) {
		long total = (long) Math.pow((this.L - numRules), this.N  - Combinatorics.sumArray(ind));
		for(int i = 0; i < ind.length; i++) {
			total *= Combinatorics.nCr(this.N - Combinatorics.sumArray(ind, i), i);
		}
		return total;
	}
	
}
