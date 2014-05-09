package org.cidarlab.minieugene.interaction;

import java.util.List;
import java.util.ArrayList;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.interaction.Participation.Role;

public class Interaction {

	private String a;
//	private String type;
	private String b;
	
	private InteractionType type;
	private List<Participation> participations;
	
	public static enum InteractionType {
		INDUCES, DRIVES, REPRESSES, BINDS 
	}
	
	public Interaction(InteractionType type) {
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
	
	public String toEugene() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.a).append(" ").append(this.type).append(" ").append(this.b).append(";");
		return sb.toString();
	}
}
