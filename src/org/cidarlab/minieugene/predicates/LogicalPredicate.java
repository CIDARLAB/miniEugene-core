package org.cidarlab.minieugene.predicates;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ernst Oberortner
 *
 */
public abstract class LogicalPredicate 
	extends Predicate {

	private LogicalOperator op;
	private List<Predicate> predicates;
	
	/**
	 * 
	 * @param op
	 * @param predicate
	 */
	public LogicalPredicate(LogicalOperator op, Predicate predicate) {
		this.op = op;
		this.predicates = new ArrayList<Predicate>();
		this.predicates.add(predicate);
	}

	/**
	 * 
	 * @param op
	 * @param predicates
	 */
	public LogicalPredicate(LogicalOperator op, List<Predicate> predicates) {
		this.op = op;
		this.predicates = predicates;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Predicate> getPredicates() {
		return this.predicates;
	}
	
	/**
	 * 
	 */
	public String getOperator() {
		return this.op.toString();
	}
}
