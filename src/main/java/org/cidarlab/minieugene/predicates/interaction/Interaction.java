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

import java.util.List;
import java.util.ArrayList;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.predicates.BinaryConstraint;
import org.cidarlab.minieugene.predicates.ConstraintOperand;
import org.cidarlab.minieugene.predicates.interaction.Participation.Role;

public abstract class Interaction 
		extends BinaryConstraint 
		implements InteractionConstraint {

	private InteractionType type;
	private List<Participation> participations;
	
	public static enum InteractionType {
		INDUCES, DRIVES, REPRESSES, BINDS 
	}
	
	public Interaction(ConstraintOperand a, InteractionType type, ConstraintOperand b) {		
		super(a, b);
		
		this.type = type;
		this.participations = new ArrayList<Participation>();		
	}
	
	public void addParticipation(Role role, Component participant) {
		this.getParticipations().add(new Participation(role, participant));
	}
	
	public List<Participation> getParticipations() {
		return this.participations;
	}
		
	public InteractionType getType() {
		return this.type;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();	
		if(null != this.getA().getOperand().getName()) {
			sb.append(this.getA().getOperand().getName()).append(" ");
		} else if((-1) != this.getA().getIndex()) {
			sb.append("[").append(this.getA().getIndex()).append("] ");
		}
		sb.append(this.getOperator()).append(" ");
		if(null != this.getB().getOperand().getName()) {
			sb.append(this.getB().getOperand().getName()).append(" ");
		} else if((-1) != this.getB().getIndex()) {
			sb.append("[").append(this.getB().getIndex()).append("] ");
		}
		return sb.toString();
	}


}
