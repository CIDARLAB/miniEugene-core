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

package org.cidarlab.minieugene.predicates;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.counting.CountingConstraint;

import org.jacop.constraints.PrimitiveConstraint;
import org.jacop.core.IntVar;
import org.jacop.core.Store;

public class LogicalAnd 
	extends LogicalPredicate {

	private int minN;
	private int maxN;
	
	public LogicalAnd() {
		super(LogicalOperator.AND, new ArrayList<Constraint>());
		this.setMinN(-1);
	}
	
	public LogicalAnd(List<Constraint> predicates) {
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
		for(Constraint predicate : this.getConstraints()) {
			if(predicate instanceof LogicalPredicate) {
				sb.append(" ( ").append(predicate).append(" ) "); 
			} else {
				sb.append(predicate);
			}
			
			if((++i) < this.getConstraints().size()) {
				sb.append(" /\\ ");
			}
		}
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		System.out.println("[toJaCop] -> "+this.toString());
		return null;
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMinimumLength() {
		int minN = 0;
		for(Constraint p : this.getConstraints()) {
			if(p instanceof LogicalPredicate) {
				minN += ((LogicalPredicate)p).getMinimumLength();
			} else if(p instanceof CountingConstraint) {
				minN += ((CountingConstraint)p).getMinimumLength();
			}
		}
		return minN;
	}

	@Override
	public int getNumberOfRules() {
		int n = 0;
		for(Constraint p : this.getConstraints()) {
			if(p instanceof LogicalPredicate) {
				n += ((LogicalPredicate)p).getNumberOfRules();
			} else {
				n++;
			}
		}
		return n;
	}

}
