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

import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class LogicalOr 
	extends LogicalPredicate {

	public LogicalOr() {
		super(LogicalOperator.OR, new ArrayList<Constraint>());
	}
	
	public LogicalOr(List<Constraint> predicates) {
		super(LogicalOperator.OR, predicates);
	}
	
	
	@Override
	public String getOperator() {
		return LogicalOperator.OR.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<this.getConstraints().size(); i++) {
			sb.append(this.getConstraints().get(i));
			if(i < this.getConstraints().size() - 1) {
				sb.append(" \\/ ");
			}
		}
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		
		int i = 0;
		PrimitiveConstraint[] pc = new PrimitiveConstraint[this.getConstraints().size()];
		for(Constraint predicate : this.getConstraints()) {
			pc[i++] = predicate.toJaCoP(store, variables);
		}
		
		return new Or(pc);
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		int i = 0;
		PrimitiveConstraint[] pc = new PrimitiveConstraint[this.getConstraints().size()];
		for(Constraint predicate : this.getConstraints()) {
			pc[i++] = predicate.toJaCoPNot(store, variables);
		}
		
		return new Or(pc);
	}

	@Override
	public int getMinimumLength() {
		int minN = Integer.MAX_VALUE; 
		for(Constraint p : this.getConstraints()) {
			if(p instanceof CountingConstraint) {
				
				int pN = ((CountingConstraint)p).getMinimumLength();
				
				if (pN < minN) {
					minN = pN;
				}
			} else if(p instanceof LogicalPredicate) {
				int pN = ((LogicalPredicate)p).getMinimumLength();				
				if (pN < minN) {
					minN = pN;
				}
			}
		}
		return minN;
	}

	@Override
	public int getNumberOfRules() {
		return this.getConstraints().size();
	}

}
