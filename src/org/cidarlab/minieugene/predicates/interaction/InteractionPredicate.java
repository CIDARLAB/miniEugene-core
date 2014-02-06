package org.cidarlab.minieugene.predicates.interaction;

import org.cidarlab.minieugene.predicates.BinaryPredicate;

/*
 * for rules like:
 * REPRESSES, INDUCES, BINDS, ORTHO, DRIVES
 */
public abstract class InteractionPredicate 
	extends BinaryPredicate {

	public InteractionPredicate(int a, int b) {
		super(a, b);
	}

}
