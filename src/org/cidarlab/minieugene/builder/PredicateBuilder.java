package org.cidarlab.minieugene.builder;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.LogicalNot;
import org.cidarlab.minieugene.predicates.Predicate;
import org.cidarlab.minieugene.predicates.counting.BinaryContains;
import org.cidarlab.minieugene.predicates.counting.Contains;
import org.cidarlab.minieugene.predicates.counting.Exactly;
import org.cidarlab.minieugene.predicates.counting.MoreThan;
import org.cidarlab.minieugene.predicates.interaction.Drives;
import org.cidarlab.minieugene.predicates.interaction.Induces;
import org.cidarlab.minieugene.predicates.interaction.Represses;
import org.cidarlab.minieugene.predicates.orientation.AllForward;
import org.cidarlab.minieugene.predicates.orientation.AllReverse;
import org.cidarlab.minieugene.predicates.orientation.Alternate;
import org.cidarlab.minieugene.predicates.orientation.SomeForward;
import org.cidarlab.minieugene.predicates.orientation.SomeReverse;
import org.cidarlab.minieugene.predicates.pairing.Equals;
import org.cidarlab.minieugene.predicates.pairing.Then;
import org.cidarlab.minieugene.predicates.pairing.With;
import org.cidarlab.minieugene.predicates.position.EndsWith;
import org.cidarlab.minieugene.predicates.position.StartsWith;
import org.cidarlab.minieugene.predicates.position.after.AllAfter;
import org.cidarlab.minieugene.predicates.position.after.SomeAfter;
import org.cidarlab.minieugene.predicates.position.before.AllBefore;
import org.cidarlab.minieugene.predicates.position.before.SomeBefore;
import org.cidarlab.minieugene.predicates.position.nextto.AllNextTo;
import org.cidarlab.minieugene.predicates.position.nextto.SomeNextTo;
import org.cidarlab.minieugene.symbol.SymbolTables;

/**
 * 
 * @author Ernst Oberortner
 */
public class PredicateBuilder {

	private SymbolTables symbols;
	
	public PredicateBuilder(SymbolTables symbols) {
		this.symbols = symbols;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 * @throws EugeneException
	 */
	public Predicate buildNullaryPredicate(String p) 
			throws EugeneException {
		if(RuleOperator.ALL_REVERSE.toString().equalsIgnoreCase(p)) {
			return new AllReverse(null);
		} else if(RuleOperator.ALL_FORWARD.toString().equalsIgnoreCase(p)) {
			return new AllForward(null);
		} else if(RuleOperator.ALTERNATE.toString().equalsIgnoreCase(p)) {
			return new Alternate(null);
		}
		
		throw new EugeneException("Invalid Rule!");

	}
	
	/**
	 * 
	 * @param p
	 * @param c
	 * @return
	 * @throws EugeneException
	 */
	public Predicate buildUnary(String p, Component c) 
			throws EugeneException {
		
		if(RuleOperator.CONTAINS.toString().equalsIgnoreCase(p)) {
			return new Contains(c);
		} else if(RuleOperator.NOTCONTAINS.toString().equalsIgnoreCase(p)) {
			return new LogicalNot(new Contains(c));
		} else if(RuleOperator.STARTSWITH.toString().equalsIgnoreCase(p)) {
			return new StartsWith(c);
		} else if(RuleOperator.ENDSWITH.toString().equalsIgnoreCase(p)) {
			return new EndsWith(c);
		} else if(RuleOperator.ALL_REVERSE.toString().equalsIgnoreCase(p) ||
				RuleOperator.REVERSE.toString().equalsIgnoreCase(p)) {
			return new AllReverse(c);
		} else if(RuleOperator.SOME_REVERSE.toString().equalsIgnoreCase(p)) {
			return new SomeReverse(c);
		} else if(RuleOperator.ALL_FORWARD.toString().equalsIgnoreCase(p) ||
				RuleOperator.FORWARD.toString().equalsIgnoreCase(p)) {
			return new AllForward(c);
		} else if(RuleOperator.SOME_FORWARD.toString().equalsIgnoreCase(p)) {
			return new SomeForward(c);
		}
		
		throw new EugeneException("Invalid Unary Rule!");
	}

	/**
	 * 
	 * @param p
	 * @param c
	 * @return
	 * @throws EugeneException
	 */
	public Predicate buildNegatedUnary(String p, Component c) 
			throws EugeneException {
		
		Predicate predicate = this.buildUnary(p, c);
		if(null != predicate) {
			return new LogicalNot(predicate);
		}

		throw new EugeneException("Invalid Negated Unary Rule!");
	}
	
	/**
	 * 
	 * @param lhs
	 * @param X
	 * @param rhs
	 * @return
	 * @throws EugeneException
	 */
	public Predicate buildBinary(Component lhs, String X, Component rhs) 
			throws EugeneException {

		if(RuleOperator.ALL_BEFORE.toString().equalsIgnoreCase(X) || 
				RuleOperator.BEFORE.toString().equalsIgnoreCase(X)) {
			return new AllBefore(lhs, rhs);
		} else if(RuleOperator.SOME_BEFORE.toString().equalsIgnoreCase(X)) {
			return new SomeBefore(lhs, rhs);
		} else if(RuleOperator.ALL_AFTER.toString().equalsIgnoreCase(X) || 
				RuleOperator.AFTER.toString().equalsIgnoreCase(X)) {
			return new AllAfter(lhs, rhs);
		} else if(RuleOperator.SOME_AFTER.toString().equalsIgnoreCase(X)) {
			return new SomeAfter(lhs, rhs);
		} else if(RuleOperator.ALL_NEXTTO.toString().equalsIgnoreCase(X) || 
				RuleOperator.NEXTTO.toString().equalsIgnoreCase(X)) {
			return new AllNextTo(lhs, rhs);
		} else if(RuleOperator.SOME_NEXTTO.toString().equalsIgnoreCase(X)) {
			return new SomeNextTo(lhs, rhs);
		} else if(RuleOperator.WITH.toString().equalsIgnoreCase(X)) {
			return new With(lhs, rhs);
		} else if(RuleOperator.NOTWITH.toString().equalsIgnoreCase(X)) {
			return new LogicalNot(new With(lhs, rhs));
		} else if(RuleOperator.THEN.toString().equalsIgnoreCase(X)) {
			return new Then(lhs, rhs);
		} else if(RuleOperator.NOTTHEN.toString().equalsIgnoreCase(X)) {
			return new LogicalNot(new Then(lhs, rhs));
		} else if(RuleOperator.CONTAINS.toString().equalsIgnoreCase(X)) {
			return new BinaryContains(lhs, rhs);
		} else if(RuleOperator.NOTCONTAINS.toString().equalsIgnoreCase(X)) {
			return new LogicalNot(new BinaryContains(lhs, rhs));
		}

		throw new EugeneException("Invalid Binary Rule!");
	}
	
	/**
	 * 
	 * @param lhs
	 * @param p
	 * @param num
	 * @return
	 * @throws EugeneException
	 */
	public Predicate buildBinary(Component lhs, String p, int num) 
			throws EugeneException {
		if(RuleOperator.EXACTLY.toString().equalsIgnoreCase(p)) {
			return new Exactly(lhs, num);
		} else if(RuleOperator.NOTEXACTLY.toString().equalsIgnoreCase(p)) {
			return new LogicalNot(new Exactly(lhs, num));
		} else if(RuleOperator.MORETHAN.toString().equalsIgnoreCase(p)) {
			return new MoreThan(lhs, num);
		} else if(RuleOperator.NOTMORETHAN.toString().equalsIgnoreCase(p)) {
			return new LogicalNot(new MoreThan(lhs, num));
		}
		
		throw new EugeneException("Invalid Binary Rule!");
	}
	
	/**
	 * 
	 * @param i
	 * @param p
	 * @param j
	 * @return
	 * @throws EugeneException
	 */
	public Predicate buildIndexedBinary(int i, String p, int j) 
			throws EugeneException {
		if(RuleOperator.EQUALS.toString().equalsIgnoreCase(p)) {
			return new Equals(i, j);
		} else if(RuleOperator.NOTEQUALS.toString().equalsIgnoreCase(p)) {
			return new LogicalNot(new Equals(i, j));
		}
		
		throw new EugeneException("Invalid Binary Rule!");
	}
	
	/**
	 * 
	 * @param a
	 * @param X
	 * @param b
	 * @return
	 * @throws EugeneException
	 */
	public Predicate buildInteraction(String a, String X, String b)
		throws EugeneException {
		
		if(a.startsWith("[") && a.endsWith("]")) {
			if(b.startsWith("[") && b.endsWith("]")) {
				
				// [i] X [j]
				// X :={REPRESSES, DRIVES}
			} else {
				
				// [i] X b
				// X := {}
			}
		} else if(b.startsWith("[") && b.endsWith("]")) {
			
			// a X [j]
			// X := {INDUCES}
			
		} else {
			// a X b
			// X := {INDUCES, REPRESSES, DRIVES}
			if(RuleOperator.INDUCES.toString().equalsIgnoreCase(X)) {
				
				/*
				 * the inducer is not allowed to appear in any
				 * non-INDUCES rule
				 */
				if(this.symbols.contains(a)) {
					throw new EugeneException("Invalid INDUCES rule!");
				}
				
				return new Induces(
						a,
						this.symbols.get(this.symbols.getId(b)));
				
			} else if(RuleOperator.REPRESSES.toString().equalsIgnoreCase(X)) {
				
				return new Represses(
						this.symbols.get(this.symbols.getId(a)),
						this.symbols.get(this.symbols.getId(b)));
				
			} else if(RuleOperator.DRIVES.toString().equalsIgnoreCase(X)) {
				
				return new Drives(
						this.symbols.get(this.symbols.getId(a)),
						this.symbols.get(this.symbols.getId(b)));
				
			}			
		}
		
		throw new EugeneException("Invalid Interaction Rule!");
 
	}

}
