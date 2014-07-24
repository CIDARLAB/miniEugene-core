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

/**
 * 
 * @author Ernst Oberortner
 *
 */
public abstract class LogicalPredicate 
	extends Constraint {

	private LogicalOperator op;
	private List<Constraint> constraints;
	
	/**
	 * 
	 * @param op
	 * @param predicate
	 */
	public LogicalPredicate(LogicalOperator op, Constraint predicate) {
		this.op = op;
		this.constraints = new ArrayList<Constraint>();
		this.constraints.add(predicate);
	}

	/**
	 * 
	 * @param op
	 * @param predicates
	 */
	public LogicalPredicate(LogicalOperator op, List<Constraint> predicates) {
		this.op = op;
		this.constraints = predicates;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Constraint> getConstraints() {
		return this.constraints;
	}
	
	/**
	 * 
	 */
	public String getOperator() {
		return this.op.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public abstract int getMinimumLength();

	/**
	 * 
	 * @return
	 */
	public abstract int getNumberOfRules();

}
