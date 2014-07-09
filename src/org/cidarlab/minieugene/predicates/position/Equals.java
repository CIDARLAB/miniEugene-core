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

package org.cidarlab.minieugene.predicates.position;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XeqY;
import JaCoP.constraints.XneqC;
import JaCoP.constraints.XneqY;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/**
 * [i] EQUALS [j] ... the elements at index i and j must be equal regarding name and type
 * [i] EQUALS p   ... the element at index i must be p
 * 
 * @author Ernst Oberortner
 *
 */
public class Equals 
	extends BinaryPredicate 
	implements PositioningPredicate {
	
	private int i;
	private int j;
	
	public Equals(int i, int j) {
		super(null, null);
		this.i = i;
		this.j = j;
	}
	
	public Equals(int i, Component a) {
		super(a, null);
		this.i = i;
		this.j = -1;		
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.EQUALS.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		
		if(-1 == this.j) {
			return new XeqC(variables[Variables.PART][i], this.getA().getId());
		}
		
		return new XeqY(variables[Variables.PART][i], variables[Variables.PART][j]);
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		
		if(-1 == this.j) {
			return new XneqC(variables[Variables.PART][i], this.getA().getId());
		}
		
		return new XneqY(variables[Variables.PART][i], variables[Variables.PART][j]);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(this.i).append("] ")
			.append(this.getOperator()).append(" ");
		if(-1 == this.j) {
			sb.append(this.getA().getName());			
		} else {
			sb.append("[").append(this.j).append("]");			
		}		
		return sb.toString();
	}
}
