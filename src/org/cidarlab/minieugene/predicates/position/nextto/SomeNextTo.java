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

package org.cidarlab.minieugene.predicates.position.nextto;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.predicates.position.PositioningPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/*
 * A NEXTTO B
 * 
 * Definition:  
 */
public class SomeNextTo 
	extends BinaryPredicate 
	implements PositioningPredicate {

	public SomeNextTo(Component a, Component b) {
		super(a, b);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append(this.getA())
				.append(" ").append(RuleOperator.SOME_NEXTTO).append(" ")
				.append(this.getB());
		} catch(Exception e) {}
		return sb.toString();
	}

	@Override
	public String getOperator() {
		return RuleOperator.SOME_NEXTTO.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {

		int a = (int)this.getA().getId();
		int b = (int)this.getB().getId();
		int N = variables[Variables.PART].length;
		
		int[] idxA = new int[N];
//			int[] idxB = new int[N];
		for(int i=0; i<N; i++) {
			idxA[i] = i;
		}

		/*
		 * a NEXTTO b
		 */
		PrimitiveConstraint containsA = contains(variables[Variables.PART], idxA, a);
//			PrimitiveConstraint containsB = contains(variables, idxB, b);
		
					
		PrimitiveConstraint positionA = constraintIndices(variables[Variables.PART], idxA, a, b);
//			PrimitiveConstraint positionB = constraintIndices(variables, idxB, b, a);

		/*
		 * contains(a) => exists(b): position(a)==position(b-1) V position(a)==position(b+1)
		 */
		return new IfThen(containsA, positionA);			
	}

	/*
	 * a NEXTTO b
	 */
	private static PrimitiveConstraint contains(IntVar[] variables, int[] indices, int a) {
		PrimitiveConstraint[] pc = new PrimitiveConstraint[indices.length];
		for(int i=0; i<indices.length; i++) {
			pc[i] = new XeqC(variables[indices[i]], a);
		}		
		return new Or(pc);
	}
	
	private static PrimitiveConstraint constraintIndices(IntVar[] variables, int[] indices, int a, int b) {
		int N = variables.length;
		
		PrimitiveConstraint[] pc = new PrimitiveConstraint[indices.length];
		for(int i=0; i<indices.length; i++) {
			int idx = indices[i];
			if(idx == 0) {
				pc[0] = new And(new XeqC(variables[0], a), new XeqC(variables[1], b));								
			} else if(idx == N-1) { 
				pc[i] = new And(
							new XeqC(variables[N-1], a), 
							new XeqC(variables[N-2], b));				
			} else {
				pc[i] = new And(
							new XeqC(variables[idx], a), 
							new Or(
								new XeqC(variables[idx-1], b),
								new XeqC(variables[idx+1], b)));
			}
		}

		return new Or(pc);
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		return new Not(this.toJaCoP(store, variables));
	}

}
