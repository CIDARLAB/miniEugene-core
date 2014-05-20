package org.cidarlab.minieugene.predicates.interaction;

import java.util.List;
import java.util.ArrayList;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.predicates.interaction.Participation.Role;

public abstract class Interaction 
		extends BinaryPredicate {

	private InteractionType type;
	private List<Participation> participations;
	
	public static enum InteractionType {
		INDUCES, DRIVES, REPRESSES, BINDS 
	}
	
	public Interaction(Component a, InteractionType type, Component b) {		
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
		sb.append(this.getA().getName())
			.append(" ").append(this.getType()).append(" ")
			.append(this.getB().getName());
		return sb.toString();
	}


}
