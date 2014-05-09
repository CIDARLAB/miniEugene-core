package org.cidarlab.minieugene.interaction;

import org.cidarlab.minieugene.dom.Component;

public class Participation {

	private Role role;
	private Component participant;
	
	public enum Role {
		REPRESSEE, REPRESSOR, 
		INDUCER, INDUCEE, 
		DRIVER, DRIVEE
	}
	
	protected Participation(Role role, Component participant) {
		this.role = role;
		this.participant = participant;		
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public Component getParticipant() {
		return this.participant;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getRole()).append(": ").append(this.getParticipant().getName());
		return sb.toString();
	}
}
