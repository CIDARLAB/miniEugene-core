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

package org.cidarlab.minieugene.dom;

import org.cidarlab.minieugene.constants.PredefinedTypes;
import org.cidarlab.minieugene.constants.PredefinedTypes.PartType;

/**
 * 
 * @author Ernst Oberortner
 */
public class ComponentType 
		extends Identified {

	private static final long serialVersionUID = -8290152785547467536L;
	
	private PartType pt;
	
	public ComponentType(String name) {
		super(name);

		/*
		 * here we need to check if the name equals 
		 * to a predefined name
		 */
		if(PredefinedTypes.isPredefined(name)) {
			this.pt = PredefinedTypes.toPartType(name);
		} else {
			this.pt = null;
		}

	}
	
	public ComponentType(PartType pt) {
		super(pt.toString());
		this.pt = pt;
	}
	
	public PartType getPartType() {
		return this.pt;
	}
	
	@Override
	public int getId() {
		if(null != this.getPartType()) {
			return PredefinedTypes.toId(this.getPartType());
		}
		return super.getId();
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
