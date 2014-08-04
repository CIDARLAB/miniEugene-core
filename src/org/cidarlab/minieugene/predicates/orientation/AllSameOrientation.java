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
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.BinaryConstraint;
import org.cidarlab.minieugene.predicates.ConstraintOperand;

import org.jacop.constraints.PrimitiveConstraint;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.constraints.Or;
import org.jacop.constraints.And;
import org.jacop.constraints.Not;

/*
 * a SAME_ORIENTATION b
 * 
 */
public class AllSameOrientation 
	extends BinaryConstraint 
	implements OrientationConstraint {

	public AllSameOrientation(ConstraintOperand a, ConstraintOperand b) {
		super(a, b);
	}

	@Override
	public String getOperator() {
		return RuleOperator.ALL_SAME_ORIENTATION.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();	
		sb.append(this.getA()).append(" ").append(this.getOperator()).append(" ").append(this.getB());
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {

		AllForward afa = new AllForward(this.getA());
		AllForward afb = new AllForward(this.getB());
		
		AllReverse ara = new AllReverse(this.getA());
		AllReverse arb = new AllReverse(this.getB());
		
		// a SAME_ORIENTATION b <=>
		//     ALL_FORWARD a /\ ALL_FORWARD b  \/
		//     ALL_REVERSE a /\ ALL_REVERSE b
		
		return new Or(
				new And(afa.toJaCoP(store, variables), afb.toJaCoP(store, variables)),
				new And(ara.toJaCoP(store, variables), arb.toJaCoP(store, variables)));
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		
		return new Not(this.toJaCoP(store, variables));
	}

}
