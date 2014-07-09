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

package org.cidarlab.minieugene.predicates.orientation;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.BinaryPredicate;

import JaCoP.constraints.And;
import JaCoP.constraints.Not;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/*
 * a SAME_ORIENTATION b
 * 
 */
public class SomeSameOrientation 
	extends BinaryPredicate 
	implements OrientationPredicate {

	public SomeSameOrientation(Component a, Component b) {
		super(a, b);
	}

	@Override
	public String getOperator() {
		return RuleOperator.SOME_SAME_ORIENTATION.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();		
		sb.append(this.getA().getName()).append(" ")
			.append(this.getOperator()).append(" ")
			.append(this.getB().getName());
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {

		System.out.println("IMPOSING "+this.toString());
		
		// the number of forward oriented a's and the number of forward oriented b's
		// MUST be >= 1
		// OR
		// the number of reverse oriented a's and the number of reverse oriented b's
		// MUST be >= 1
		SomeForward sfa = new SomeForward(this.getA());
		SomeForward sfb = new SomeForward(this.getB());
		
		SomeReverse sra = new SomeReverse(this.getA());
		SomeReverse srb = new SomeReverse(this.getB());
		
		// a SOME_SAME_ORIENTATION b <=>
		//     SOME_FORWARD a /\ SOME_FORWARD b  \/
		//     SOME_REVERSE a /\ SOME_REVERSE b
		
		return new And(
				new And(sfa.toJaCoP(store, variables), sfb.toJaCoP(store, variables)),
				new And(sra.toJaCoP(store, variables), srb.toJaCoP(store, variables)));
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		
		return new Not(this.toJaCoP(store, variables));
	}

}
