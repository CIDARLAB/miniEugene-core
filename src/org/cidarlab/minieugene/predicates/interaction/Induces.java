package org.cidarlab.minieugene.predicates.interaction;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.interaction.Participation.Role;

import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/**
 * 
 * @author Ernst Oberortner
 *
 */
public class Induces 
	extends Interaction {
	
	/*
	 * the inducer induces the inducee
	 */
	public Induces(Component inducer, Component inducee) {
		super(inducer, InteractionType.INDUCES, inducee);

		this.getParticipations().add(new Participation(Role.INDUCEE, inducee));
		this.getParticipations().add(new Participation(Role.INDUCER, inducer));
	}

	@Override
	public String getOperator() {
		return this.getType().toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		return null;
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws MiniEugeneException {
		return null;
	}

}
