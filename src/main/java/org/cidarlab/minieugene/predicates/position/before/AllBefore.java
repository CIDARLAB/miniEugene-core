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
import org.cidarlab.minieugene.predicates.counting.Contains;
import org.cidarlab.minieugene.predicates.position.PositioningConstraint;
import org.cidarlab.minieugene.solver.jacop.Variables;

import org.jacop.constraints.And;
import org.jacop.constraints.IfThen;
import org.jacop.constraints.Not;
import org.jacop.constraints.PrimitiveConstraint;
import org.jacop.constraints.XeqC;
import org.jacop.constraints.XneqC;
import org.jacop.core.IntVar;
import org.jacop.core.Store;


/**
 * 
 * @author Ernst Oberortner
 *
 */
public class AllBefore 
		extends BinaryConstraint 
		implements PositioningConstraint {

	public AllBefore(ConstraintOperand a, ConstraintOperand b) {
		super(a, b);
	}

	@Override
	public String getOperator() {
		return RuleOperator.ALL_BEFORE.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append(this.getA())
				.append(" ").append(RuleOperator.ALL_BEFORE).append(" ")
				.append(this.getB());
		} catch(Exception e) {
			// do nothing...
		}
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {

		/*
		 * a ALL_BEFORE b
		 * 
		 * contains(a) /\ contains (b) => 
		 * 		for all a, b: position(a) < position(b)
		 * otherwise => TRUE
		 */

		PrimitiveConstraint a_before_b = this.before(
				this.getA(), this.getB(), store, variables);

		return a_before_b;
	}

	
	private PrimitiveConstraint before(ConstraintOperand a, ConstraintOperand b,
			Store store, IntVar[][] variables) 
			throws MiniEugeneException {
		
		int N = variables[Variables.PART].length;
		
		Contains containsA = new Contains(this.getA());

		int va = this.getVariableIndex(a);
		int vb = this.getVariableIndex(b);
		
		PrimitiveConstraint pc[] = new PrimitiveConstraint[N];
		pc[0] = new IfThen(
					containsA.toJaCoP(store, variables),
					new XneqC(variables[vb][0], b.getOperand().getId()));

		for(int i=1; i<N; i++) {

			// b cannot appear before a
			PrimitiveConstraint[] pcB = new PrimitiveConstraint[i];
			for(int j=0; j<i; j++) {
				pcB[j] = new XneqC(variables[vb][j], b.getOperand().getId());
			}

			pc[i] = new IfThen(
						new XeqC(variables[va][i], a.getOperand().getId()),
						new And(pcB));
		}
		
		return new And(pc);
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		return new Not(
				this.toJaCoP(store, variables));
	}
	
}
