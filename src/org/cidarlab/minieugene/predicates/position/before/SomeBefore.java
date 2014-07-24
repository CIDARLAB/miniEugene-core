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

package org.cidarlab.minieugene.predicates.position.before;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.BinaryConstraint;
import org.cidarlab.minieugene.predicates.ConstraintOperand;
import org.cidarlab.minieugene.predicates.position.PositioningConstraint;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/**
 * 
 * @author Ernst Oberortner
 *
 */
public class SomeBefore 
	extends BinaryConstraint 
	implements PositioningConstraint {
	
	public SomeBefore(ConstraintOperand a, ConstraintOperand b) {
		super(a, b);
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.SOME_BEFORE.toString();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append(this.getA())
				.append(" ").append(RuleOperator.SOME_BEFORE).append(" ")
				.append(this.getB());
		} catch(Exception e) {
			// do nothing...
		}
		return sb.toString();
	}
	
	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
			throws MiniEugeneException {
		
		int a = this.getA().getOperand().getId();
		int b = this.getB().getOperand().getId();
		int N = variables[Variables.PART].length;
		
		int va = this.getVariableIndex(this.getA());
		int vb = this.getVariableIndex(this.getB());

		/*
		 * a SOME_BEFORE b
		 * 
		 * contains(a) && contains(b) => 
		 *     exists a: position(a) < position(b)
		 */
		
		PrimitiveConstraint pc[] = new PrimitiveConstraint[N];
		
		// a can appear at the first position
		pc[0] = new XeqC(variables[va][0], a);
 
		for(int i=1; i<N; i++) {

			// if a appears at the i-th position,
			// then at least one element after a must be b 
			
			PrimitiveConstraint[] pcB = new PrimitiveConstraint[N-i];
			for(int j=i; j<N; j++) {
				pcB[j-i] = new XeqC(variables[vb][j], b);
			}
			
			pc[i] = new And(
						new XeqC(variables[va][i], a),
						new Or(pcB));
		}			

		return new Or(pc);

	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		return new Not(this.toJaCoP(store, variables));
	}
		
}
