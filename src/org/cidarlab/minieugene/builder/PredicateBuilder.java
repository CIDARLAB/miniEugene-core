/*
 * Copyright (c) 2014, Boston University
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 *    
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *    
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.minieugene.builder;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.dom.Identified;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.ConstraintOperand;
import org.cidarlab.minieugene.predicates.LogicalNot;
import org.cidarlab.minieugene.predicates.Constraint;
import org.cidarlab.minieugene.predicates.counting.BinaryContains;
import org.cidarlab.minieugene.predicates.counting.Contains;
import org.cidarlab.minieugene.predicates.counting.Exactly;
import org.cidarlab.minieugene.predicates.counting.MoreThan;
import org.cidarlab.minieugene.predicates.counting.SameCount;
import org.cidarlab.minieugene.predicates.interaction.Drives;
import org.cidarlab.minieugene.predicates.interaction.Induces;
import org.cidarlab.minieugene.predicates.interaction.Interaction;
import org.cidarlab.minieugene.predicates.interaction.Represses;
import org.cidarlab.minieugene.predicates.orientation.AllForward;
import org.cidarlab.minieugene.predicates.orientation.AllReverse;
import org.cidarlab.minieugene.predicates.orientation.AlternateOrientation;
import org.cidarlab.minieugene.predicates.orientation.AllSameOrientation;
import org.cidarlab.minieugene.predicates.orientation.SomeForward;
import org.cidarlab.minieugene.predicates.orientation.SomeReverse;
import org.cidarlab.minieugene.predicates.orientation.SomeSameOrientation;
import org.cidarlab.minieugene.predicates.pairing.AlwaysNextTo;
import org.cidarlab.minieugene.predicates.pairing.Then;
import org.cidarlab.minieugene.predicates.pairing.With;
import org.cidarlab.minieugene.predicates.position.EndsWith;
import org.cidarlab.minieugene.predicates.position.Equals;
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
	 * @throws MiniEugeneException
	 */
	public Constraint buildNullaryPredicate(String p) 
			throws MiniEugeneException {
		if(RuleOperator.ALL_REVERSE.toString().equalsIgnoreCase(p)) {
			return new AllReverse(null);
		} else if(RuleOperator.SOME_REVERSE.toString().equalsIgnoreCase(p)) {
			return new SomeReverse(null);
		} else if(RuleOperator.ALL_FORWARD.toString().equalsIgnoreCase(p)) {
			return new AllForward(null);
		} else if(RuleOperator.SOME_FORWARD.toString().equalsIgnoreCase(p)) {
			return new SomeForward(null);
		} else if(RuleOperator.ALTERNATE_ORIENTATION.toString().equalsIgnoreCase(p)) {
			return new AlternateOrientation(null);
		}
		
		throw new MiniEugeneException(p+" is an invalid Nullary constraint!");

	}
	
	/**
	 * 
	 * @param p
	 * @param c
	 * @return
	 * @throws MiniEugeneException
	 */
	public Constraint buildUnary(String p, Identified c) 
			throws MiniEugeneException {
		
		if(RuleOperator.CONTAINS.toString().equalsIgnoreCase(p)) {
			return new Contains(new ConstraintOperand(c));
		} else if(RuleOperator.NOTCONTAINS.toString().equalsIgnoreCase(p)) {
			return new LogicalNot(new Contains(new ConstraintOperand(c)));
		} else if(RuleOperator.STARTSWITH.toString().equalsIgnoreCase(p)) {
			return new StartsWith(new ConstraintOperand(c));
		} else if(RuleOperator.ENDSWITH.toString().equalsIgnoreCase(p)) {
			return new EndsWith(new ConstraintOperand(c));
		} else if(RuleOperator.ALL_REVERSE.toString().equalsIgnoreCase(p) ||
				RuleOperator.REVERSE.toString().equalsIgnoreCase(p)) {
			return new AllReverse(new ConstraintOperand(c));
		} else if(RuleOperator.SOME_REVERSE.toString().equalsIgnoreCase(p)) {
			return new SomeReverse(new ConstraintOperand(c));
		} else if(RuleOperator.ALL_FORWARD.toString().equalsIgnoreCase(p) ||
				RuleOperator.FORWARD.toString().equalsIgnoreCase(p)) {
			return new AllForward(new ConstraintOperand(c));
		} else if(RuleOperator.SOME_FORWARD.toString().equalsIgnoreCase(p)) {
			return new SomeForward(new ConstraintOperand(c));
		} else if(RuleOperator.ALTERNATE_ORIENTATION.toString().equalsIgnoreCase(p)) {
			return new AlternateOrientation(new ConstraintOperand(c));
		}
		
		throw new MiniEugeneException("Invalid Unary Rule!");
	}

	/**
	 * 
	 * @param p
	 * @param c
	 * @return
	 * @throws MiniEugeneException
	 */
	public Constraint buildNegatedUnary(String p, Identified c) 
			throws MiniEugeneException {
		
		Constraint constraint = this.buildUnary(p, c);
		if(null != constraint) {
			return new LogicalNot(constraint);
		}

		throw new MiniEugeneException("Invalid Negated Unary Constraint!");
	}
	
	/**
	 * 
	 * @param lhs
	 * @param X
	 * @param rhs
	 * @return
	 * @throws MiniEugeneException
	 */
	public Constraint buildBinary(Identified lhs, String X, Identified rhs) 
			throws MiniEugeneException {

		if(RuleOperator.ALL_BEFORE.toString().equalsIgnoreCase(X) || 
				RuleOperator.BEFORE.toString().equalsIgnoreCase(X)) {
			return new AllBefore(new ConstraintOperand(lhs), new ConstraintOperand(rhs));
		} else if(RuleOperator.SOME_BEFORE.toString().equalsIgnoreCase(X)) {
			return new SomeBefore(new ConstraintOperand(lhs), new ConstraintOperand(rhs));
		} else if(RuleOperator.ALL_AFTER.toString().equalsIgnoreCase(X) || 
				RuleOperator.AFTER.toString().equalsIgnoreCase(X)) {
			return new AllAfter(new ConstraintOperand(lhs), new ConstraintOperand(rhs));
		} else if(RuleOperator.SOME_AFTER.toString().equalsIgnoreCase(X)) {
			return new SomeAfter(new ConstraintOperand(lhs), new ConstraintOperand(rhs));
		} else if(RuleOperator.ALL_NEXTTO.toString().equalsIgnoreCase(X) || 
				RuleOperator.NEXTTO.toString().equalsIgnoreCase(X)) {
			return new AllNextTo(new ConstraintOperand(lhs), new ConstraintOperand(rhs));
		} else if(RuleOperator.SOME_NEXTTO.toString().equalsIgnoreCase(X)) {
			return new SomeNextTo(new ConstraintOperand(lhs), new ConstraintOperand(rhs));
		} else if(RuleOperator.WITH.toString().equalsIgnoreCase(X)) {
			return new With(new ConstraintOperand(lhs), new ConstraintOperand(rhs));
		} else if(RuleOperator.NOTWITH.toString().equalsIgnoreCase(X)) {
			return new LogicalNot(new With(new ConstraintOperand(lhs), new ConstraintOperand(rhs)));
		} else if(RuleOperator.THEN.toString().equalsIgnoreCase(X)) {
			return new Then(new ConstraintOperand(lhs), new ConstraintOperand(rhs));
		} else if(RuleOperator.NOTTHEN.toString().equalsIgnoreCase(X)) {
			return new LogicalNot(new Then(new ConstraintOperand(lhs), new ConstraintOperand(rhs)));
		} else if(RuleOperator.CONTAINS.toString().equalsIgnoreCase(X)) {
			return new BinaryContains(new ConstraintOperand(lhs), new ConstraintOperand(rhs));
		} else if(RuleOperator.NOTCONTAINS.toString().equalsIgnoreCase(X)) {
			return new LogicalNot(new BinaryContains(new ConstraintOperand(lhs), new ConstraintOperand(rhs)));
		} else if(RuleOperator.SAME_ORIENTATION.toString().equalsIgnoreCase(X) ||
				RuleOperator.ALL_SAME_ORIENTATION.toString().equalsIgnoreCase(X)) {
			return new AllSameOrientation(new ConstraintOperand(lhs), new ConstraintOperand(rhs));
		} else if(RuleOperator.SOME_SAME_ORIENTATION.toString().equalsIgnoreCase(X)) {
			return new SomeSameOrientation(new ConstraintOperand(lhs), new ConstraintOperand(rhs));
		} else if(RuleOperator.SAME_COUNT.toString().equalsIgnoreCase(X)) {
			return new SameCount(new ConstraintOperand(lhs), new ConstraintOperand(rhs));
		} else if(RuleOperator.ALWAYS_NEXTTO.toString().equalsIgnoreCase(X)) {
			return new AlwaysNextTo(new ConstraintOperand(lhs), new ConstraintOperand(rhs));
		}

		throw new MiniEugeneException("Invalid Binary Rule!");
	}
	
	/**
	 * 
	 * @param a
	 * @param X
	 * @param b
	 * @return
	 * @throws MiniEugeneException
	 */
	public Constraint buildBinaryIndexed(String a, String X, String b, int maxN) 
			throws MiniEugeneException {
		
		// [i] EQUALS [j]
		if((a.startsWith("[") && a.endsWith("]") &&
			b.startsWith("[") && b.endsWith("]"))) {
			/*
			 * next, we need to get the index out of the strings a and b
			 */
			a = a.substring(1, a.length()-1);
			b = b.substring(1, b.length()-1);
			
			int idxA = this.toIndex(a, maxN);
			int idxB = this.toIndex(b, maxN);
			
			if(RuleOperator.EQUALS.toString().equalsIgnoreCase(X)) {
				return new Equals(idxA, idxB);
			} else if(RuleOperator.NOTEQUALS.toString().equalsIgnoreCase(X)) {
				return new LogicalNot(new Equals(idxA, idxB));
			}
			
		// [i] EQUALS p	
		} else if(a.startsWith("[") && a.endsWith("]") && 
				!b.startsWith("[") && !b.endsWith("]")) {

			a = a.substring(1, a.length()-1);
			int idxA = this.toIndex(a, maxN);
							
			if(RuleOperator.EQUALS.toString().equalsIgnoreCase(X)) {
				return new Equals(idxA, new ConstraintOperand(this.symbols.get(b)));
			} else if(RuleOperator.NOTEQUALS.toString().equalsIgnoreCase(X)) {
				return new LogicalNot(new Equals(idxA, new ConstraintOperand(this.symbols.get(b))));
			}
		}
		
		throw new MiniEugeneException(a+" "+X+" "+b+" is an invalid rule!");
	}
	
	private int toIndex(String a, int max) 
			throws MiniEugeneException {

		int idx = -1;
		try {
			idx = Integer.parseInt(a);
		} catch(NumberFormatException nfe) {
			throw new MiniEugeneException("Invalid index!");
		}

		if(idx < 0 || idx >= max) {
			throw new MiniEugeneException("Index "+idx+" is out of range!");
		}
		
		return idx;
	}


	
	/**
	 * 
	 * @param lhs
	 * @param p
	 * @param num
	 * @return
	 * @throws MiniEugeneException
	 */
	public Constraint buildBinary(Identified lhs, String p, int num) 
			throws MiniEugeneException {
		if(RuleOperator.EXACTLY.toString().equalsIgnoreCase(p)) {
			return new Exactly(new ConstraintOperand(lhs), num);
		} else if(RuleOperator.NOTEXACTLY.toString().equalsIgnoreCase(p)) {
			return new LogicalNot(new Exactly(new ConstraintOperand(lhs), num));
		} else if(RuleOperator.MORETHAN.toString().equalsIgnoreCase(p)) {
			return new MoreThan(new ConstraintOperand(lhs), num);
		} else if(RuleOperator.NOTMORETHAN.toString().equalsIgnoreCase(p)) {
			return new LogicalNot(new MoreThan(new ConstraintOperand(lhs), num));
		}
		
		throw new MiniEugeneException("Invalid Counting Rule!");
	}
	
	/**
	 * 
	 * @param i
	 * @param p
	 * @param j
	 * @return
	 * @throws MiniEugeneException
	 */
	public Constraint buildIndexedBinary(int i, String p, int j) 
			throws MiniEugeneException {
		if(RuleOperator.EQUALS.toString().equalsIgnoreCase(p)) {
			return new Equals(i, j);
		} else if(RuleOperator.NOTEQUALS.toString().equalsIgnoreCase(p)) {
			return new LogicalNot(new Equals(i, j));
		}
		
		throw new MiniEugeneException("Invalid Indexed Rule!");
	}
	
	/**
	 * 
	 * @param a
	 * @param X
	 * @param b
	 * @return
	 * @throws MiniEugeneException
	 */
	public Constraint buildInteraction(String a, String X, String b)
		throws MiniEugeneException {
		
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
			if(Interaction.InteractionType.INDUCES.toString().equalsIgnoreCase(X)) {
				
				return new Induces(
						new ConstraintOperand(new Component(a, null)),
						new ConstraintOperand(this.symbols.get(b)));
				
			} else if(Interaction.InteractionType.REPRESSES.toString().equalsIgnoreCase(X)) {
				
				return new Represses(
						new ConstraintOperand(this.symbols.get(a)),
						new ConstraintOperand(this.symbols.get(b)));
				
			} else if(Interaction.InteractionType.DRIVES.toString().equalsIgnoreCase(X)) {
				
				return new Drives(
						new ConstraintOperand(this.symbols.get(a)),
						new ConstraintOperand(this.symbols.get(b)));
				
			}			
		}
		
		throw new MiniEugeneException("Invalid Interaction Rule!");
 
	}

}
