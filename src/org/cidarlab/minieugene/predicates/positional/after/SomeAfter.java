package org.cidarlab.minieugene.predicates.positional.after;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.predicates.positional.before.SomeBefore;

import JaCoP.constraints.Constraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;



/* A SOME_AFTER B 
 * 
 * IF the long[] array, that the evaluate() method receives, CONTAINS A and B, THEN 
 *     A's first occurrence must be AFTER B's first occurrence
 * ELSE
 *     A AFTER B is true
 * END IF
 * 
 * Notes:
 * - A AFTER B is equal to B BEFORE A... therefore, we utilize the BEFORE predicate 
 *   to evaluate the AFTER rule...
 * - rules like ``All A's must occur BEFORE all B's'' can be achieved 
 *   by using Eugene's new ``FOR ALL'' operator...
 */
public class SomeAfter 
		extends BinaryPredicate {

	private SomeBefore before;
	
	public SomeAfter(int a, int b) 
			throws EugeneException {
		super(a, b);
		this.before = new SomeBefore(b, a);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA())
				.append(" ").append(RuleOperator.SOME_AFTER).append(" ")
				.append(this.getB());

		return sb.toString();
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.SOME_AFTER.toString();
	}

	@Override
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
			throws EugeneException {
		return this.before.toJaCoP(store, variables);
	}
}

