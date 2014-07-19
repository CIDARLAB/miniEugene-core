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

package org.cidarlab.minieugene;

import java.util.Arrays;
import java.util.List;

import org.cidarlab.minieugene.builder.PredicateBuilder;
import org.cidarlab.minieugene.constants.MiniEugeneRules;
import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.constants.TemplateType;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.LogicalNot;
import org.cidarlab.minieugene.predicates.LogicalOperator;
import org.cidarlab.minieugene.predicates.Predicate;
import org.cidarlab.minieugene.predicates.position.Equals;
import org.cidarlab.minieugene.predicates.templating.*;
import org.cidarlab.minieugene.symbol.SymbolTables;

public class Interp {
	
	private SymbolTables symbols;
	private PredicateBuilder pb;
	private int minN;
	private int maxN;
	
	public Interp(SymbolTables symbols) {
		this.symbols = symbols;		
		this.pb = new PredicateBuilder(this.symbols);
		this.minN = 0;
		this.maxN = -1;
	}
	
	public int getMinN() {
		return this.minN;
	}
	
	public void setMinN(int minN) {
		this.minN = minN;
	}

	public int getMaxN() {
		return this.maxN;
	}
	
	public void setMaxN(int maxN) {
		this.maxN = maxN;
	}
	
	/*
	 * 
	 */
	public Predicate interpreteRule(String[] tokens) 
			throws MiniEugeneException {

		switch(tokens.length) {
		case 1:
			/*
			 * PREDICATES w/o a rule operand
			 * nullary predicate
			 * e.g. ALL_REVERSE
			 */
			return createNullaryPredicate(tokens[0]);
		case 2:
			/*
			 * unary rule \/ negated nullary rule
			 */
			return createUnaryPredicate(tokens[0], tokens[1]);
		case 3:
			/*
			 * binary rule \/ negated unary rule
			 */
			if(LogicalOperator.NOT.toString().equalsIgnoreCase(tokens[0])) {
				return new LogicalNot(createUnaryPredicate(tokens[1], tokens[2]));
			}
			return createBinaryPredicate(tokens[0], tokens[1], tokens[2]);
		case 4:
			/*
			 * negated binary rule
			 */
			if(LogicalOperator.NOT.toString().equalsIgnoreCase(tokens[0]) && 
					!(LogicalOperator.NOT.toString().equalsIgnoreCase(tokens[1]))) {
				return new LogicalNot(createBinaryPredicate(tokens[1], tokens[2], tokens[3]));
			}
		default:
			throw new MiniEugeneException(Arrays.toString(tokens) + " is an invalid rule. Wrong number of tokens.");
		}
	}
	
	public TemplatingPredicate createTemplatingConstraint(
			TemplateType template, String name, List<List<String>> ids) {
		return template.createPredicate(this.symbols, name, ids);
	}
	
	private Predicate createNullaryPredicate(String s) 
			throws MiniEugeneException {
		return this.pb.buildNullaryPredicate(s);
	}
	
	private Predicate createUnaryPredicate(String p, String s) 
			throws MiniEugeneException {

		if(LogicalOperator.NOT.toString().equalsIgnoreCase(p)) {
			
			return new LogicalNot(this.pb.buildNullaryPredicate(s));
//			// negated nullary constraint 
//			if(RuleOperator.ALL_REVERSE.toString().equalsIgnoreCase(s)) {
//				return new LogicalNot(new AllReverse(null));
//			} else if(RuleOperator.ALL_FORWARD.toString().equalsIgnoreCase(s)) {
//				return new LogicalNot(new AllForward(null));
//			} else if(RuleOperator.ALTERNATE_ORIENTATION.toString().equalsIgnoreCase(s)) {
//				return new LogicalNot(new AlternateOrientation(null));
//			}

			
		} else if(MiniEugeneRules.isUnaryRule(p)) {
			/*
			 * get the id from the symbol
			 */
			int id = -1;
			if(this.symbols.contains(s)) {
				id = this.symbols.getId(s);
			} else {
				/*
				 * if the symbol does not exist, 
				 * then add it to the symbol tables
				 */
				id = this.symbols.put(s);
			}

			/*
			 * build the predicate (by the predicate builder)
			 * and store it in the symbol tables
			 */
			return this.pb.buildUnary(p, this.symbols.get(id));
		}
		
		throw new MiniEugeneException(p + " is an invalid unary rule operand!");		
	}

	
	private Predicate createBinaryPredicate(String a, String X, String b) 
			throws MiniEugeneException {
	
		if(LogicalOperator.NOT.toString().equalsIgnoreCase(a)) {
			/*
			 * negated unary rule
			 * e.g. NOT CONTAINS a
			 */
			
			/*
			 * get the id from the symbol
			 */
			int id = -1;
			if(this.symbols.contains(b)) {
				id = this.symbols.getId(b);
			} else {
				/*
				 * if the symbol does not exist, 
				 * then add it to the symbol tables
				 */
				id = this.symbols.put(b);
			}
			
			return this.pb.buildNegatedUnary(X, this.symbols.get(id));
		} else if(RuleOperator.EQUALS.toString().equalsIgnoreCase(X) ||
				RuleOperator.NOTEQUALS.toString().equalsIgnoreCase(X)) {
				
			// [i] EQUALS [j]
			if((a.startsWith("[") && a.endsWith("]") &&
				b.startsWith("[") && b.endsWith("]"))) {
				/*
				 * next, we need to get the index out of the strings a and b
				 */
				a = a.substring(1, a.length()-1);
				b = b.substring(1, b.length()-1);
				
				int idxA = this.toIndex(a);
				int idxB = this.toIndex(b);
				
				if(RuleOperator.EQUALS.toString().equalsIgnoreCase(X)) {
					return new Equals(idxA, idxB);
				} else if(RuleOperator.NOTEQUALS.toString().equalsIgnoreCase(X)) {
					return new LogicalNot(new Equals(idxA, idxB));
				}
				
			// [i] EQUALS p	
			} else if(a.startsWith("[") && a.endsWith("]") && 
					!b.startsWith("[") && !b.endsWith("]")) {

				a = a.substring(1, a.length()-1);
				int idxA = this.toIndex(a);
								
				if(RuleOperator.EQUALS.toString().equalsIgnoreCase(X)) {
					return new Equals(idxA, this.symbols.get(this.symbols.put(b)));
				} else if(RuleOperator.NOTEQUALS.toString().equalsIgnoreCase(X)) {
					return new LogicalNot(new Equals(idxA, this.symbols.get(this.symbols.put(b))));
				}
			}
			
			throw new MiniEugeneException(a+" "+X+" "+b+" is an invalid rule!");
			
			
		} else if(MiniEugeneRules.isInteractionRule(X)) {
			
			return this.pb.buildInteraction(a, X, b);
		}
		
		
		/*
		 * get a's id from the symbol
		 */
		int idA = this.symbols.getId(a);
		
		int idB = -1;
		if(MiniEugeneRules.isCountingRule(X)) {

			/*
			 * b can be a decimal non-negative number
			 */
			try {
				idB = Integer.parseInt(b);
			} catch(Exception e) {
				
				/*
				 * if b is not a number, then
				 * it can be a binary CONTAINS rule
				 */
				if(RuleOperator.CONTAINS.toString().equalsIgnoreCase(X) ||
					RuleOperator.NOTCONTAINS.toString().equalsIgnoreCase(X) ||
					RuleOperator.SAME_COUNT.toString().equalsIgnoreCase(X)) {
					/*
					 * get the id from the symbol
					 */
					if(this.symbols.contains(b)) {
						idB = this.symbols.getId(b);
					} else {
						/*
						 * if the symbol does not exist, 
						 * then add it to the symbol tables
						 */
						idB = this.symbols.put(b);
					}

					return this.pb.buildBinary(this.symbols.get(idA), X, this.symbols.get(idB));
				}
				throw new MiniEugeneException("Invalid rule!");
			}

			/*
			 * 	0 <= b <= N
			 */
			// idB > this.maxN commented out...
			// => because of OR
			// e.g. p exactly 2 \/ p exactly 4
			if(idB < 0 /* || idB > this.maxN */) {
				throw new MiniEugeneException("Invalid rule!");
			}
			
			// create the counting rule object
			return this.pb.buildBinary(this.symbols.get(idA), X, idB);
			
		} else if(MiniEugeneRules.isPositionalRule(X) || MiniEugeneRules.isPairingRule(X) || 
				MiniEugeneRules.isOrientationRule(X)) {
			
			idB = this.symbols.getId(b);

		}

		/*
		 * build the predicate (by the predicate builder)
		 * and store it in the symbol tables
		 */
		if( idB != (-1)) {
			return this.pb.buildBinary(this.symbols.get(idA), X, this.symbols.get(idB));
		}
		
		throw new MiniEugeneException("Invalid rule!");
	}

	private int toIndex(String a) 
			throws MiniEugeneException {

		int idx = -1;
		try {
			idx = Integer.parseInt(a);
		} catch(NumberFormatException nfe) {
			throw new MiniEugeneException("Invalid index!");
		}

		if(idx < 0 || idx >= this.maxN) {
			throw new MiniEugeneException("Index "+idx+" is out of range!");
		}
		
		return idx;
	}

	
}
