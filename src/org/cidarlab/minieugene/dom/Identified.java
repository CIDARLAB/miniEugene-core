package org.cidarlab.minieugene.dom;

public abstract class Identified 
		extends NamedElement {

	private static final long serialVersionUID = -6027791708197832033L;
	
	/*
	 * id
	 */
	private int id;
	
	public Identified(String name) {
		super(name);
		if(null != name && !name.isEmpty()) {
			this.setId(hash(name));
		} else {
			this.setId(-1);
		}
	}
	
	/**
	 * getID/0 returns the ID of the symbol
	 * 
	 * @return int ... the automatically generated ID of the symbol
	 */
	public int getId() {
		return this.id;
	}
	
	protected void setId(int id) {
		this.id = id;
	}

	// to convert the name to an integer
	// for the constraint solver.
	// NEEDS IMPROVEMENT!
	private int hash(String name) {
		int hash = name.hashCode();
		if(hash < 0) {
			return hash * -1;
		}
		return hash;
	}
	
}
