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
import org.cidarlab.minieugene.dom.ComponentType;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.LogicalNot;
import org.cidarlab.minieugene.predicates.LogicalOperator;
import org.cidarlab.minieugene.predicates.Constraint;
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
	
	/**
	 * The insertFact/2 method takes as input two Strings.
	 * The first String indicates the name of the component.
	 * The second String indicates the type of the component.
	 * 
	 * Example:
	 * insertFact("p", "Promoter");
	 * indicates that the component p is of type promoter
	 * 
	 * @param c ... the name of the component
	 * @param t ... the name of the component type
	 * 
	 * @throws MiniEugeneException
	 */
	public void insertFact(String c, String t) 
			throws MiniEugeneException {
		
		if(null != c && null != t) {

			if(c.equals(t)) {
				throw new MiniEugeneException(c+" is_a "+t+" is TRUE!");
			}
			
			ComponentType ct = this.symbols.getType(t); 
			if(null == ct) {
				ct = this.symbols.putType(t);
			}

			this.symbols.put(c, ct);
			
			return;
		}
		
		throw new MiniEugeneException(c+" is_a "+t+" is an invalid specification of facts.");
		
	}

	/**
	 * The interpreteRule/1 method takes as input an array of Strings, 
	 * which represents a miniEugene constraint.
	 * 
	 * Example:
	 * the String array ["p", "before", "g"] represents the 
	 * p before g miniEugene constraint.
	 * 
	 * The interpreteRule/1 method returns a Predicate object which 
	 * represents the miniEugene constraint.
	 * 
	 * 
	 * @param tokens  ... a String array representing a miniEugene constraint
	 * @return a Predicate object
	 * 
	 * @throws MiniEugeneException
	 */
	public Constraint interpreteRule(String[] tokens) 
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
	
	private Constraint createNullaryPredicate(String s) 
			throws MiniEugeneException {
		return this.pb.buildNullaryPredicate(s);
	}
	
	private Constraint createUnaryPredicate(String p, String s) 
			throws MiniEugeneException {

		if(LogicalOperator.NOT.toString().equalsIgnoreCase(p)) {
			
			return new LogicalNot(this.pb.buildNullaryPredicate(s));
			
		} else if(MiniEugeneRules.isUnaryRule(p)) {

			/*
			 * build the predicate (by the predicate builder)
			 * and store it in the symbol tables
			 */
			return this.pb.buildUnary(p, this.symbols.get(s));
		}
		
		throw new MiniEugeneException(p + " is an invalid unary rule operand!");		
	}

	
	private Constraint createBinaryPredicate(String a, String X, String b) 
			throws MiniEugeneException {
	
		if(LogicalOperator.NOT.toString().equalsIgnoreCase(a)) {
			/*
			 * negated unary rule
			 * e.g. NOT CONTAINS a
			 */
			
			return this.pb.buildNegatedUnary(X, this.symbols.get(b));
		} else if(RuleOperator.EQUALS.toString().equalsIgnoreCase(X) ||
				RuleOperator.NOTEQUALS.toString().equalsIgnoreCase(X)) {
			
			
			return this.pb.buildBinaryIndexed(a, X, b, this.maxN);
			
			
		} else if(MiniEugeneRules.isInteractionRule(X)) {
			
			return this.pb.buildInteraction(a, X, b);
		}
		
		
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

					return this.pb.buildBinary(
							this.symbols.get(a), 
							X, 
							this.symbols.get(b));
				}
				
				throw new MiniEugeneException(a+" "+X+" "+b+" is an invalid rule!");
			}

			/*
			 * 	0 <= b <= N
			 */
			// idB > this.maxN commented out...
			// => because of OR
			// e.g. p exactly 2 \/ p exactly 4
			if(idB < 0 /* || idB > this.maxN */) {
				throw new MiniEugeneException(a+" "+X+" "+b+" is an invalid rule!");
			}
			
			// create the counting rule object
			return this.pb.buildBinary(
					this.symbols.get(a), 
					X, 
					idB);
			
		} else if(MiniEugeneRules.isPositioningRule(X) || MiniEugeneRules.isPairingRule(X) || 
				MiniEugeneRules.isOrientationRule(X)) {
			
			return this.pb.buildBinary(this.symbols.get(a), X, this.symbols.get(b));
		}

		
		throw new MiniEugeneException(a+" "+X+" "+b+" is an invalid rule!");
	}

	
}
