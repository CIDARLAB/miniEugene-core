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
import org.cidarlab.minieugene.dom.CompositeComponent;
import org.cidarlab.minieugene.dom.Identified;

/**
 * The ConstraintOperand class represents the operands 
 * of miniEugene constraints.
 * 
 * Example:
 * contains p  ... p is the operand of the contains constraint
 * forward [i] ... i is the operand of the forward constraint 
 * a before b  ... a and b are operands of the before constraint
 *  
 * @author Ernst Oberortner
 */
public class ConstraintOperand {

	private Identified operand;
	private int index;
	
	public ConstraintOperand(Identified operand) {
		this.operand = operand;
		this.index = -1;
	}
	
	public ConstraintOperand(int index) {
		this.index = index;
		this.operand = null;
	}
	
	public Identified getOperand() {
		return this.operand;
	}
	
	public boolean isPrimitiveComponent() {
		return null != this.getOperand() && this.getOperand() instanceof Component;
	}
	
	public boolean isType() {
		return null != this.getOperand() && this.getOperand() instanceof ComponentType;
	}

	public boolean isComposite() {
		return null != this.getOperand() && this.getOperand() instanceof CompositeComponent;
	}

	public int getIndex() {
		return this.index;
	}
	
	public boolean isIndex() {
		return this.getIndex() != -1;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(null != this.getOperand()) {
			sb.append(this.getOperand().getName());
		} else if((-1) != this.getIndex()) {
			sb.append("[").append(this.getIndex()).append("]");
		}
		return sb.toString();
	}
	
}
