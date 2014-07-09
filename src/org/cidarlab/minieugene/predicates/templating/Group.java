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

package org.cidarlab.minieugene.predicates.templating;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.Not;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class Group 
	extends TemplatingPredicate {
		
	public Group() {
		super(null, new ArrayList<List<Component>>(), false);
	}
	
	public Group(String name) {
		super(name, new ArrayList<List<Component>>(), false);
	}
	
	public Group(String name, List<List<Component>> components) {
		super(name, components, false);
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.GROUP.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		
		if(this.isNegated()) {
			return this.toJaCoPNot(store, variables);
		}
		
		int maxN = variables[Variables.PART].length;
		
//		if(maxN < this.getComponents().size()) {
//			throw new EugeneException("I cannot impose the strict template!");
//		} else if(maxN % this.getComponents().size() != 0) {
//			throw new EugeneException(
//					"The max. length "+maxN+" is not a multiple of the template size ("+this.getComponents().size()+")");
//		}
		
		return createTemplate(variables, maxN);
	}
	
	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		int maxN = variables[Variables.PART].length;
		
		if(maxN < this.getComponents().size()) {
			throw new MiniEugeneException("I cannot impose the strict template!");
		} else if(maxN % this.getComponents().size() != 0) {
			throw new MiniEugeneException(
					"The max. length "+maxN+" is not a multiple of the template size ("+this.getComponents().size()+")");
		}
		
		return new Not(createTemplate(variables, maxN));
	}
	
	private PrimitiveConstraint createTemplate(IntVar[][] variables, int maxN) {
		
//		PrimitiveConstraint[] pc = null;
//		for(int i=0; i + this.getComponents().size() <= maxN; i++) {
//			
//			// get the first component
//			Component c = this.getComponents().get(0);
//			PrimitiveConstraint[] pcTemplate = new PrimitiveConstraint[this.getComponents().size() - 1];
//			for(int j=1; j<this.getComponents().size(); j++) {
//				Component cj = this.getComponents().get(j);
//				pcTemplate[j-1] = new XeqC(variables[Variables.PART][j+i], cj.getId());
//			}
//			
//			if(pc == null) {
//				pc = new PrimitiveConstraint[1];
//				pc[0] =	new And(
//							new XeqC(variables[Variables.PART][i], c.getId()),
//							new And(pcTemplate)); 
//			} else {
//				pc = ArrayUtils.add(pc, 
//					new And(
//						new XeqC(variables[Variables.PART][i], c.getId()),
//						new And(pcTemplate)));
//			}
//		}
//
////		return new And(pc);
//		return new Or(pc);
		
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		// NEGATED?
		if(this.isNegated()) {
			sb.append("NOT ");
		}
		
		// NAME
		if(null != this.getName() && !this.getName().isEmpty()) {
			sb.append(this.getName()).append(" ");
		}
		
		// OPERATOR
		sb.append(this.getOperator()).append(" ");

		// SELECTION
		for(int i=0; i<this.getComponents().size(); i++) {
			
			// SELECTION
			sb.append("[");
			List<Component> lst_selection = this.getComponents().get(i);
			for(int j=0; j<lst_selection.size(); j++) {	
				sb.append(lst_selection.get(j).getName());
				
				if(j < lst_selection.size() - 1) {
					sb.append("|");
				}
			}
					
			sb.append("]");
			
			if(i<this.getComponents().size()-1) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}
}
