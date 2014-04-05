/*
Copyright (c) 2012 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
 */

package org.cidarlab.minieugene.dom;

import org.cidarlab.minieugene.constants.PropertyType;

public class Property 
	extends NamedElement {

	private static final long serialVersionUID = 8579155836433787367L;
	protected PropertyType type;

	public Property(String name, PropertyType type) {
		super(name);
		this.type = type;
	}

//	public Property(String name) {
//		super(name);
//		this.type = null;
//	}
//
//	public void setType(String sType) {
//		// sType must be one of the following:
//		// num, num[], txt, txt[], or boolean
//		if (null != sType
//				&& ("num".equals(sType) || "num[]".equals(sType)
//						|| "txt".equals(sType) || "txt[]".equals(sType) || "boolean"
//							.equals(sType))) {
//			this.type = sType;
//		}
//	}

	public PropertyType getType() {
		return this.type;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Property ").append(this.getName()).append("(")
				.append(this.type).append(")");
		return sb.toString();
	}
}