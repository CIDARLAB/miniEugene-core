package org.cidarlab.minieugene.interaction;

import org.cidarlab.minieugene.constants.RuleOperator;

public class Interaction {

	private String a;
	private String type;
	private String b;
	
	public Interaction(String a, String type, String b) {
		this.a = a;
		this.type = type;
		this.b = b;
	}
	
	public String toPigeon() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.a);
		if(RuleOperator.REPRESSES.toString().equalsIgnoreCase(this.type)) {
			sb.append(" rep ");
		} else if(RuleOperator.INDUCES.toString().equalsIgnoreCase(this.type)) {
			sb.append(" ind ");
		}
		sb.append(this.b);
		return sb.toString();
	}
	
	public String toEugene() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.a).append(" ").append(RuleOperator.REPRESSES.toString()).append(" ").append(this.b).append(";");
		return sb.toString();
	}
}
