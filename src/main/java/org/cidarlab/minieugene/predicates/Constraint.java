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

package org.cidarlab.minieugene.predicates;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.dom.ComponentType;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.solver.jacop.Variables;

import org.jacop.constraints.Count;
import org.jacop.constraints.PrimitiveConstraint;
import org.jacop.core.IntVar;
import org.jacop.core.Store;

/**
 *
 * @author Ernst Oberortner
 */
public abstract class Constraint {
	
	/**
	 * 
	 * @return
	 */
	public abstract String getOperator();
	
	/**
	 * 
	 * @param store
	 * @param variables
	 * @return
	 * @throws MiniEugeneException
	 */
	public abstract PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
		throws MiniEugeneException;
	
	/**
	 * 
	 * @param store
	 * @param variables
	 * @return
	 * @throws MiniEugeneException
	 */
	public abstract PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException;
	
	
	/**
	 * The createCounter/3 method imposes a variable that counts the occurrence 
	 * of a given constraint operand.
	 * 
	 *  If the counter variable exists, then we return the counter variable. Otherwise 
	 *  we impose a new counter variable.
	 *  
	 * @param store ... the variable and constraint stor
	 * @param variables ... the variables
	 * @param op ... the constraint operand
	 * 
	 * @return the counter variable
	 */
	protected IntVar createCounter(Store store, IntVar[][] variables, ConstraintOperand op) {

		IntVar counter = null;
		if(op.getOperand() instanceof Component) {
			
			Component c = (Component)(op.getOperand());

			counter = (IntVar)store.findVariable(c.getName()+"-counter");
			if(null == counter) {
				counter = new IntVar(store, 
						c.getName()+"-counter", 
						0, 
						variables[Variables.PART].length);
				store.impose(new Count(variables[Variables.PART], counter, c.getId()));
			}

		} else if(op.getOperand() instanceof ComponentType) {
			
			ComponentType t = (ComponentType)(op.getOperand());

			counter = (IntVar)store.findVariable(t.getName()+"-counter");
			if(null == counter) {
				counter = new IntVar(store, 
						t.getName()+"-counter", 
						0, 
						variables[Variables.TYPE].length);
				store.impose(new Count(variables[Variables.TYPE], counter, t.getId()));
			}
		}
		
		return counter;
	}
	
	/**
	 * The getVariableIndex/1 method takes as input a constraint operand 
	 * and returns the row index of the variable matrix.
	 * 
	 * @param co  ... the constraint operand
	 * @return  ... the variable row index
	 */
	protected int getVariableIndex(ConstraintOperand co) {
		if(co.getOperand() instanceof ComponentType) {
			return Variables.TYPE;
		}
		return Variables.PART;
	}

	
}
