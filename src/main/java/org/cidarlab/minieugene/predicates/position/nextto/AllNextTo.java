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
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.BinaryConstraint;
import org.cidarlab.minieugene.predicates.ConstraintOperand;
import org.cidarlab.minieugene.predicates.position.PositioningConstraint;
import org.cidarlab.minieugene.solver.jacop.Variables;
import org.jacop.constraints.And;
import org.jacop.constraints.IfThen;
import org.jacop.constraints.Not;
import org.jacop.constraints.Or;
import org.jacop.constraints.PrimitiveConstraint;
import org.jacop.constraints.XeqC;
import org.jacop.constraints.XneqC;
import org.jacop.core.IntVar;
import org.jacop.core.Store;

/*
 * A NEXTTO B
 * 
 * Definition:  
 */
public class AllNextTo 
	extends BinaryConstraint 
	implements PositioningConstraint {

	public AllNextTo(ConstraintOperand a, ConstraintOperand b) {
		super(a, b);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA())
			.append(" ").append(RuleOperator.ALL_NEXTTO).append(" ")
			.append(this.getB());
		return sb.toString();
	}

	@Override
	public String getOperator() {
		return RuleOperator.ALL_NEXTTO.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {
		
		int a = (int)this.getA().getOperand().getId();
		int b = (int)this.getB().getOperand().getId();
		
		int va = this.getVariableIndex(this.getA());
		int vb = this.getVariableIndex(this.getB());
		
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
				constraintIndices(variables[va], variables[vb], idxA, a, b);

		return new And(pc);
	}

	/**
	 * A NEXTTO B
	 * 
	 * IF A appears at index idxA THEN 
	 * B cannot appear at index index but the index immediately 
	 * left or right to idxA
	 * 
	 * Example:
	 * IF idxA == 0 THEN
	 *    idxB not in {2, 3, ..., N-1}
	 * 
	 * IF idxA == N-1 THEN
	 *    idxB not in {0, 1, ..., N-3}
	 *    
	 * IF idxA == i THEN
	 *    idxB not in {0, ..., i-2, i, i+2, ... N-1}
	 */
	private PrimitiveConstraint[] constraintIndices(
			IntVar[] va, IntVar[] vb, int[] indices, int a, int b) {

		int N = va.length;
		PrimitiveConstraint[] pc = new PrimitiveConstraint[indices.length];
		for(int i=0; i<N; i++) {
			
			int idxA = indices[i];
			if(idxA > 0 && idxA<N-1) {
				
				// B cannot appear at any index but 
				// immediately left or right to A's index 
				// as well as at A's index itself
				PrimitiveConstraint[] pcIndexOfB = new PrimitiveConstraint[N-3];
				for(int idxB=0,j=0; idxB<N; idxB++) {
					if(idxB==idxA-1 || idxB==idxA ||idxB==idxA+1) continue;
					
					pcIndexOfB[j++] = new XneqC(vb[idxB], b);
				}
				
				/*
				 * if a is placed at any position (except the first and the last position),
				 * then place b either at the position immediately before or 
				 *                     at the position immediately after
				 */
				pc[i] = new IfThen(
							new XeqC(va[idxA], a),
							new And(pcIndexOfB));
				
			} else if(idxA == 0) {
				
				// B cannot appear at any index but 
				// immediately right to A's index (i.e. at index 1) 
				// as well as at A's index itself (i.e. at index 0)
				PrimitiveConstraint[] pcIndexOfB = new PrimitiveConstraint[N-2];
				for(int idxB=0,j=0; idxB<N; idxB++) {
					if(idxB==idxA || idxB==idxA+1) continue;
					
					pcIndexOfB[j++] = new XneqC(vb[idxB], b);
				}

				/*
				 * if a is placed at the first position,
				 * then place b at the second position
				 */
				pc[i] = new IfThen(
								new XeqC(va[idxA], a),
								new And(pcIndexOfB));
				
			} else if(idxA == N-1) {
				
				// B cannot appear at any index but 
				// immediately left to A's index (i.e. at index N-2) 
				// as well as at A's index itself (i.e. at index 0)
				PrimitiveConstraint[] pcIndexOfB = new PrimitiveConstraint[N-2];
				for(int idxB=0,j=0; idxB<N; idxB++) {
					if(idxB==idxA || idxB==idxA-1) continue;
					
					pcIndexOfB[j++] = new XneqC(vb[idxB], b);
				}

				/*
				 * if a is placed at the last position,
				 * then place b at the second last position
				 */
				pc[i] = new IfThen(
							new XeqC(va[idxA], a),
							new And(pcIndexOfB));
				
			}
		}
		
		return pc;
	}

	/*--------------------------------------
	 * NOT a NEXTTO b
	 *--------------------------------------*/
	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {

		int a = (int)this.getA().getOperand().getId();
		int b = (int)this.getB().getOperand().getId();
		
		int va = this.getVariableIndex(this.getA());
		int vb = this.getVariableIndex(this.getB());
		
		int N = variables[Variables.PART].length;
		
		if(N <= 1) {
			throw new MiniEugeneException("I cannot impose "+this.toString()+"! Invalid length of design!");
		}

		int[] idxA = new int[N];
		for(int i=0; i<N; i++) {
			idxA[i] = i;
		}

		PrimitiveConstraint[] pc = 
				constraintIndicesNot(variables[va], variables[vb], idxA, a, b);

		return new And(pc);
	}
	
	/**
	 * NOT A NEXTTO B
	 * 
	 * IF A appears at index idxA THEN 
	 * B cannot appear at any index that is 
	 * immediately left or right to idxA
	 * 
	 * Example:
	 * IF idxA == 0 THEN
	 *    idxB not in {1}
	 * 
	 * IF idxA == N-1 THEN
	 *    idxB not in {N-2}
	 *    
	 * IF idxA == i THEN
	 *    idxB not in {i-1, i+1}
	 */
	private PrimitiveConstraint[] constraintIndicesNot(
			IntVar[] va, IntVar[] vb, int[] indices, int a, int b) {

		int N = va.length;
		PrimitiveConstraint[] pc = new PrimitiveConstraint[indices.length];
		for(int i=0; i<N; i++) {
			
			int idxA = indices[i];
			if(idxA > 0 && idxA<N-1) {
				
				// B can only appear at any index but 
				// immediately left or right to A's index
				PrimitiveConstraint[] pcIndexOfB = new PrimitiveConstraint[2];
				for(int idxB=0,j=0; idxB<N; idxB++) {
					if(idxB==idxA-1 || idxB==idxA+1) {
						pcIndexOfB[j++] = new XneqC(vb[idxB], b);
					}
				}
				
				/*
				 * if a is placed at any position (except the first and the last position),
				 * then place b NOR at the position immediately before neither 
				 *                  at the position immediately after
				 */
				pc[i] = new IfThen(
							new XeqC(va[idxA], a),
							new And(pcIndexOfB));
				
			} else if(idxA == 0) {
				
				/*
				 * if a is placed at the first position,
				 * then place b NOT at the second position
				 */
				pc[i] = new IfThen(
								new XeqC(va[idxA], a),
								new XneqC(vb[idxA+1], b));
				
			} else if(idxA == N-1) {

				/*
				 * if a is placed at the last position,
				 * then place b NOT at the second last position
				 */
				pc[i] = new IfThen(
							new XeqC(va[idxA], a),
							new XneqC(vb[idxA-1], b));
				
			}
		}
		
		return pc;
	}
}
