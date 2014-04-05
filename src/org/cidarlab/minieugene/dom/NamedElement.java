package org.cidarlab.minieugene.dom;

import java.io.Serializable;

public abstract class NamedElement 
		implements Serializable {

	private static final long serialVersionUID = 7803721755204206476L;
	private String name;
	
	public NamedElement(String name) {
		this.name = name;
	}
	
	/**
	 * getName/0 returns the name of the symbol
	 * 
	 * @return String ... the name of the symbol
	 */
	public String getName() {
		return this.name;
	}
}
