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
import org.cidarlab.minieugene.dom.ComponentType;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.ConstraintOperand;
import org.cidarlab.minieugene.predicates.UnaryConstraint;
import org.cidarlab.minieugene.solver.jacop.Variables;

import org.jacop.constraints.IfThen;
import org.jacop.constraints.Not;
import org.jacop.constraints.Or;
import org.jacop.constraints.PrimitiveConstraint;
import org.jacop.constraints.XeqC;
import org.jacop.core.IntVar;
import org.jacop.core.Store;

/*
 * SOME_REVERSE a
 * 
 * X := the set of all symbols (defined by the user)
 * a element_of X
 * exists a : direction(a) = '-'
 */
public class SomeReverse
	extends UnaryConstraint
	implements OrientationConstraint {

	public SomeReverse(ConstraintOperand a) {
		super(a);
	}

	@Override
	public String getOperator() {
		return RuleOperator.SOME_REVERSE.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getOperator()).append(" ").append(this.getA());
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {

		/*
		 * SOME_REVERSE a ...
		 * CONTAINS a =>
		 * the number of a components that are reverse oriented must be greater or equal to 1
		 */
		PrimitiveConstraint[] pc = new PrimitiveConstraint[variables[Variables.ORIENTATION].length];
		if(this.getA() == null) {
			for(int i=0; i<variables[Variables.ORIENTATION].length; i++) {
				pc[i] = new XeqC(variables[Variables.ORIENTATION][i], -1);
			}
		} else {
			if(null != this.getA().getOperand()) {
				for(int i=0; i<variables[Variables.ORIENTATION].length; i++) {
					if(this.getA().getOperand() instanceof Component) {
						pc[i] = new IfThen(
									new XeqC(variables[Variables.PART][i], this.getA().getOperand().getId()),
									new XeqC(variables[Variables.ORIENTATION][i], -1));
					} else if(this.getA().getOperand() instanceof ComponentType) {
						pc[i] = new IfThen(
									new XeqC(variables[Variables.TYPE][i], this.getA().getOperand().getId()),
									new XeqC(variables[Variables.ORIENTATION][i], -1));
					}
				}
			} else if((-1) > this.getA().getIndex() &&
					this.getA().getIndex() < variables[Variables.ORIENTATION].length) {
				return new XeqC(variables[Variables.ORIENTATION][this.getA().getIndex()], -1);
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
