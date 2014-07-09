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
public class AllNextTo 
	extends BinaryPredicate 
	implements PositioningPredicate {

	public AllNextTo(Component a, Component b) {
		super(a, b);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA().getName())
			.append(" ").append(RuleOperator.ALL_NEXTTO).append(" ")
			.append(this.getB().getName());
		return sb.toString();
	}

	@Override
	public String getOperator() {
		return RuleOperator.ALL_NEXTTO.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {
		
		int a = (int)this.getA().getId();
		int b = (int)this.getB().getId();
		int N = variables[Variables.PART].length;
		
		if(N <= 1) {
			throw new MiniEugeneException("I cannot impose "+this.toString()+"! Invalid length of design!");
		}

		int[] idxA = new int[N];
		for(int i=0; i<N; i++) {
			idxA[i] = i;
		}

		/*
		 * a NEXTTO b
		 */
		PrimitiveConstraint[] pc = 
				constraintIndices(variables[Variables.PART], idxA, a, b);

		return new And(pc);
	}

	/*
	 * a NEXTTO b
	 */
	private static PrimitiveConstraint[] constraintIndices(IntVar[] parts, int[] indices, int a, int b) {
		int N = parts.length;
		PrimitiveConstraint[] pc = new PrimitiveConstraint[indices.length];
		for(int i=0; i<indices.length; i++) {
			int idx = indices[i];
			if(idx > 0 && idx<N-1) {
				
				/*
				 * if a is placed at any position (except the first and the last position),
				 * then place b either at the position immediately before or 
				 *                     at the position immediately after
				 */
				pc[i] = new IfThen(
							new XeqC(parts[idx], a),
							new Or(
								new XeqC(parts[idx-1], b),
								new XeqC(parts[idx+1], b)));
			} else if(idx == 0) {
				
				/*
				 * if a is placed at the first position,
				 * then place b at the second position
				 */
				pc[i] = new IfThen(
							new XeqC(parts[idx], a),
							new XeqC(parts[idx+1], b));
			} else if(idx == N-1) {
				
				/*
				 * if a is placed at the last position,
				 * then place b at the second last position
				 */
				pc[i] = new IfThen(
							new XeqC(parts[idx], a),
							new XeqC(parts[idx-1], b));
			}
		}
		
		return pc;
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		return new Not(this.toJaCoP(store, variables));
	}
	
}
