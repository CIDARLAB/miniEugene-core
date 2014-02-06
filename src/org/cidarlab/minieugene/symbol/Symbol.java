package org.cidarlab.minieugene.symbol;

import org.cidarlab.minieugene.solver.jacop.PartTypes;

public class Symbol {

	/*
	 * id
	 */
	private int id;
	
	/*
	 * name
	 */
	private String name;
	
	/*
	 * direction
	 */
	private boolean forward;
	
	public Symbol(String name) {
		this.name = name;
		this.forward = true;

		this.id = this.name.hashCode();
	}
	
	public Symbol(String name, boolean forward) {
		this.name = name;
		this.forward = forward;
		
		this.id = this.name.hashCode();
	}
	
	public boolean isForward() {
		return forward;
	}
	
	public void setForward(boolean forward) {
		this.forward = forward;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}

	public int getType() {
		if('p' == this.name.charAt(0) || 'P' == this.name.charAt(0)) {
			return PartTypes.get("PROMOTER");
		} else if('r' == this.name.charAt(0) || 'R' == this.name.charAt(0)) {
			return PartTypes.get("RBS");  
		} else if('c' == this.name.charAt(0) || 'C' == this.name.charAt(0)) {
			return PartTypes.get("GENE");  
		} else if('t' == this.name.charAt(0) || 'T' == this.name.charAt(0)) {
			return PartTypes.get("TERMINATOR");  
		} 
		return 5;
	}
	public String toPigeon() {
		if(!this.isForward()) {
			return "<"+this.getName();
		}
		return this.getName();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append(this.getId()).append(", ").append(this.getName()).append(", ");
		if(this.isForward()) {
			sb.append("->");
		} else {
			sb.append("<-");
		}
		sb.append("}");
		return sb.toString();
	}
}
