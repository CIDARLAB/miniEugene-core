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

package org.cidarlab.minieugene.predicates.counting;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.predicates.pairing.PairingPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.Count;
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
public class With 
		extends BinaryPredicate
		implements PairingPredicate {

	public With(Component a, Component b) {
		super(a, b);
	}

	@Override	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA()).append(" ")
			.append(RuleOperator.WITH).append(" ")
			.append(this.getB());
		return sb.toString();
	}

	@Override
	public String getOperator() {
		return RuleOperator.WITH.toString();
	}


	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {
		
		/*
		 * CONTAINS a /\ CONTAINS b
		 */
		return new And(
				new Contains(this.getA()).toJaCoP(store, variables), 
				new Contains(this.getB()).toJaCoP(store, variables));
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		
		// a's counter
		IntVar counterA = (IntVar)store.findVariable(this.getA().getName()+"-counter");
		if(null == counterA) {
			counterA = new IntVar(store, 
					this.getA().getName()+"-counter", 
					0, 
					variables[Variables.PART].length);
			store.impose(new Count(variables[Variables.PART], counterA, this.getA().getId()));
		}

		// b's counter
		IntVar counterB = (IntVar)store.findVariable(this.getB().getName()+"-counter");
		if(null == counterB) {
			counterB = new IntVar(store, 
					this.getB().getName()+"-counter", 
					0, 
					variables[Variables.PART].length);
			store.impose(new Count(variables[Variables.PART], counterB, this.getB().getId()));
		}

		/*
		 * NOT (CONTAINS a /\ CONTAINS b)
		 * <=>
		 * NOT CONTAINS a \/ NOT CONTAINS b
		 */
		return new Or(
				new XeqC(counterA, 0), 
				new XeqC(counterB, 0));
	}

}
