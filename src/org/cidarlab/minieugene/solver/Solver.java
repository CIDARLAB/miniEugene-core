package org.cidarlab.minieugene.solver;

import java.util.List;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.Predicate;

public interface Solver {
	
	/*
	 * N          ... size of the design
	 * ids        ... the domains of the variables
	 * predicates ... the Eugene constraints
	 * number     ... number of desired solutions
	 */
	public List<Component[]> solve(int N, Component[] symbols, Predicate[] predicates, int number)
			throws EugeneException;
	
}
