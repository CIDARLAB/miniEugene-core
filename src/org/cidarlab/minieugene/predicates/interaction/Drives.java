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

package org.cidarlab.minieugene.predicates.interaction;

import org.cidarlab.minieugene.constants.PartTypesTable;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.orientation.AllForward;
import org.cidarlab.minieugene.predicates.orientation.AllReverse;
import org.cidarlab.minieugene.predicates.orientation.AllSameOrientation;
import org.cidarlab.minieugene.predicates.position.before.AllBefore;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XneqC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class Drives 
	extends Interaction {
	
	public Drives(Component a, Component b) {		
		super(a, InteractionType.DRIVES, b);
	}
		

	@Override
	public String getOperator() {
		return Interaction.InteractionType.DRIVES.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws MiniEugeneException {

//		System.out.println("**** "+this.toString()+" ***");
		
    	// FORWARD ORIENTED
    	PrimitiveConstraint[] pcForward = new PrimitiveConstraint[3];
		pcForward[0] = noTerminatorBetween(variables, this.getA(), this.getB());
		pcForward[1] = new AllBefore(this.getA(), this.getB()).toJaCoP(store, variables);
		pcForward[2] = new AllForward(this.getA()).toJaCoP(store, variables);
		
		// REVERSE ORIENTED
		PrimitiveConstraint[] pcReverse = new PrimitiveConstraint[3];
		pcReverse[0] = noTerminatorBetween(variables, this.getB(), this.getA());
		pcReverse[1] = new AllBefore(this.getB(), this.getA()).toJaCoP(store, variables);
		pcReverse[2] = new AllReverse(this.getA()).toJaCoP(store, variables);

		
		// a drives b <=>
		//     a same_orientation b /\
		//     ( a before b => position(a) < position(b) \/
		//       a after b  => position(b) < position(a) ) /\
		//     no terminator in between
		PrimitiveConstraint[] pcReturn = new PrimitiveConstraint[2];
		pcReturn[0] = new AllSameOrientation(this.getA(), this.getB()).toJaCoP(store, variables);
		pcReturn[1] = new Or(
						new And(pcForward), 
						new And(pcReverse));
		
		return new And(pcReturn);
	}
	
	
	private PrimitiveConstraint noTerminatorBetween(
			IntVar[][] variables, Component A, Component B) {
		
		int termId = PartTypesTable.toId(PartTypesTable.toPartType("TERMINATOR"));
		
		int N = variables[Variables.PART].length;
		
		PrimitiveConstraint pc[] = new PrimitiveConstraint[N];
		for(int i=0; i<N; i++) {
			
			PrimitiveConstraint[] downstream = new PrimitiveConstraint[N];
			for(int j=0; j<N; j++) {
				if(i < j) {
					PrimitiveConstraint[] noTerminator = new PrimitiveConstraint[Math.abs(i-j)];
					for(int k=i; k<j; k++) {
						noTerminator[k-i] = new XneqC(variables[Variables.TYPE][k], termId);
					}
					downstream[j] = new IfThen(new XeqC(variables[Variables.PART][j], B.getId()), new And(noTerminator));
				} else if (i==j) {
					downstream[j] = new XneqC(variables[Variables.PART][j], B.getId());
				} else { // i >= j
					PrimitiveConstraint[] noTerminator = new PrimitiveConstraint[Math.abs(i-j)];
					for(int k=j; k<i; k++) {
						noTerminator[k-j] = new XneqC(variables[Variables.TYPE][k], termId);
					}
					downstream[j] = new IfThen(new XeqC(variables[Variables.PART][j], B.getId()), new And(noTerminator));
				}
			}
			
			pc[i] = new IfThen(new XeqC(variables[Variables.PART][i], A.getId()), new And(downstream));
		}
		
		return new And(pc);
	}
	

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		
		// we just negate the toJaCoP primitive constraint
		return new Not((PrimitiveConstraint)this.toJaCoP(store, variables));
	}

}
