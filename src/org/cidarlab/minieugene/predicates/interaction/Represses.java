package org.cidarlab.minieugene.predicates.interaction;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.interaction.Participation.Role;

import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class Represses 
	extends Interaction {
	
	public Represses(Component repressor, Component repressee) {
		super(repressor, InteractionType.REPRESSES, repressee);

		this.getParticipations().add(new Participation(Role.REPRESSOR, repressor));
		this.getParticipations().add(new Participation(Role.REPRESSEE, repressee));
	}
		

	@Override
	public String getOperator() {
		return Interaction.InteractionType.REPRESSES.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {
		return null;
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		return null;
	}
	
}
