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

package org.cidarlab.minieugene.predicates.pairing;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.Or;
import JaCoP.constraints.Not;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.IfThen;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/**
 * 
 * @author Ernst Oberortner
 *
 */
public class AlwaysNextTo 
		extends BinaryPredicate
		implements PairingPredicate {

	public AlwaysNextTo(Component a, Component b) {
		super(a, b);
	}

	@Override	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA()).append(" ")
			.append(this.getOperator()).append(" ")
			.append(this.getB());
		return sb.toString();
	}

	@Override
	public String getOperator() {
		return RuleOperator.ALWAYS_NEXTTO.toString();
	}


	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {

//		System.out.println("imposing "+this.toString());
		
		int N = variables[Variables.PART].length;
		int a = this.getA().getId();
		int b = this.getB().getId();
		
		PrimitiveConstraint[] pc = new PrimitiveConstraint[N];
		pc[0] = new IfThen(
					new XeqC(variables[Variables.PART][0], a),
					new XeqC(variables[Variables.PART][1], b));
		
		for(int i=1; i< N-1; i++) {
			pc[i] = new IfThen(
						new XeqC(variables[Variables.PART][i], a),
						new Or(
							new XeqC(variables[Variables.PART][i-1], b),
							new XeqC(variables[Variables.PART][i+1], b)));
		}

		pc[N-1] = new IfThen(
					new XeqC(variables[Variables.PART][N-1], a),
					new XeqC(variables[Variables.PART][N-1], b));
		
		return new And(pc);
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {

		return new Not(this.toJaCoP(store, variables));
	}

}
