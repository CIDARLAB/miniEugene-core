package org.cidarlab.minieugene.builder;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.LogicalNot;
import org.cidarlab.minieugene.predicates.Predicate;
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
import org.cidarlab.minieugene.predicates.positional.StartsWith;
import org.cidarlab.minieugene.predicates.positional.EndsWith;
import org.cidarlab.minieugene.predicates.positional.after.AllAfter;
import org.cidarlab.minieugene.predicates.positional.after.SomeAfter;
import org.cidarlab.minieugene.predicates.positional.before.AllBefore;
import org.cidarlab.minieugene.predicates.positional.before.SomeBefore;
import org.cidarlab.minieugene.predicates.positional.nextto.AllNextTo;
import org.cidarlab.minieugene.predicates.positional.nextto.SomeNextTo;
import org.cidarlab.minieugene.symbol.SymbolTables;

public class PredicateBuilder {

	private SymbolTables symbols;
	public PredicateBuilder(SymbolTables symbols) {
		this.symbols = symbols;
	}
	
	public Predicate buildNullaryPredicate(String p) 
			throws EugeneException {
		if(RuleOperator.ALL_REVERSE.toString().equalsIgnoreCase(p)) {
			return new AllReverse(-1);
		} else if(RuleOperator.ALL_FORWARD.toString().equalsIgnoreCase(p)) {
			return new AllForward(-1);
		} else if(RuleOperator.ALTERNATE.toString().equalsIgnoreCase(p)) {
			return new Alternate();
		}
		
		throw new EugeneException("Invalid Rule!");

	}
	
	public Predicate buildUnary(String p, int id) 
			throws EugeneException {
		
		if(RuleOperator.CONTAINS.toString().equalsIgnoreCase(p)) {
			return new Contains(id);
		} else if(RuleOperator.NOTCONTAINS.toString().equalsIgnoreCase(p)) {
			return new LogicalNot(new Contains(id));
		} else if(RuleOperator.STARTSWITH.toString().equalsIgnoreCase(p)) {
			return new StartsWith(id);
		} else if(RuleOperator.ENDSWITH.toString().equalsIgnoreCase(p)) {
			return new EndsWith(id);
		} else if(RuleOperator.ALL_REVERSE.toString().equalsIgnoreCase(p) ||
				RuleOperator.REVERSE.toString().equalsIgnoreCase(p)) {
			return new AllReverse(id);
		} else if(RuleOperator.SOME_REVERSE.toString().equalsIgnoreCase(p)) {
			return new SomeReverse(id);
		} else if(RuleOperator.ALL_FORWARD.toString().equalsIgnoreCase(p) ||
				RuleOperator.FORWARD.toString().equalsIgnoreCase(p)) {
			return new AllForward(id);
		} else if(RuleOperator.SOME_FORWARD.toString().equalsIgnoreCase(p)) {
			return new SomeForward(id);
		}
		
		throw new EugeneException("Invalid Unary Rule!");
	}

	public Predicate buildNegatedUnary(String p, int id) 
			throws EugeneException {
		
		Predicate predicate = this.buildUnary(p, id);
		if(null != predicate) {
			return new LogicalNot(predicate);
		}

		throw new EugeneException("Invalid Negated Unary Rule!");
	}
	
	public Predicate buildBinary(int idA, String X, int idB) 
			throws EugeneException {

		if(RuleOperator.ALL_BEFORE.toString().equalsIgnoreCase(X) || 
				RuleOperator.BEFORE.toString().equalsIgnoreCase(X)) {
			return new AllBefore(idA, idB);
		} else if(RuleOperator.SOME_BEFORE.toString().equalsIgnoreCase(X)) {
			return new SomeBefore(idA, idB);
		} else if(RuleOperator.ALL_AFTER.toString().equalsIgnoreCase(X) || 
				RuleOperator.AFTER.toString().equalsIgnoreCase(X)) {
			return new AllAfter(idA, idB);
		} else if(RuleOperator.SOME_AFTER.toString().equalsIgnoreCase(X)) {
			return new SomeAfter(idA, idB);
		} else if(RuleOperator.ALL_NEXTTO.toString().equalsIgnoreCase(X) || 
				RuleOperator.NEXTTO.toString().equalsIgnoreCase(X)) {
			return new AllNextTo(idA, idB);
		} else if(RuleOperator.SOME_NEXTTO.toString().equalsIgnoreCase(X)) {
			return new SomeNextTo(idA, idB);
		} else if(RuleOperator.WITH.toString().equalsIgnoreCase(X)) {
			return new With(idA, idB);
		} else if(RuleOperator.NOTWITH.toString().equalsIgnoreCase(X)) {
			return new LogicalNot(new With(idA, idB));
		} else if(RuleOperator.THEN.toString().equalsIgnoreCase(X)) {
			return new Then(idA, idB);
		} else if(RuleOperator.NOTTHEN.toString().equalsIgnoreCase(X)) {
			return new LogicalNot(new Then(idA, idB));
		} else if(RuleOperator.EXACTLY.toString().equalsIgnoreCase(X)) {
			return new Exactly(idA, idB);
		} else if(RuleOperator.NOTEXACTLY.toString().equalsIgnoreCase(X)) {
			return new LogicalNot(new Exactly(idA, idB));
		} else if(RuleOperator.MORETHAN.toString().equalsIgnoreCase(X)) {
			return new MoreThan(idA, idB);
		} else if(RuleOperator.NOTMORETHAN.toString().equalsIgnoreCase(X)) {
			return new LogicalNot(new MoreThan(idA, idB));
		} else if(RuleOperator.EQUALS.toString().equalsIgnoreCase(X)) {
			return new Equals(idA, idB);
		} else if(RuleOperator.NOTEQUALS.toString().equalsIgnoreCase(X)) {
			return new LogicalNot(new Equals(idA, idB));
		}

		throw new EugeneException("Invalid Binary Rule!");
	}
	
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
						this.symbols.getId(b));
				
			} else if(RuleOperator.REPRESSES.toString().equalsIgnoreCase(X)) {
				
				return new Represses(
						this.symbols.getId(a),
						this.symbols.getId(b));
				
			} else if(RuleOperator.DRIVES.toString().equalsIgnoreCase(X)) {
				
				return new Drives(
						this.symbols.getId(a),
						this.symbols.getId(b));
				
			}			
		}
		
		throw new EugeneException("Invalid Interaction Rule!");
 
	}

}
