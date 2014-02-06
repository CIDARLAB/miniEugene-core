package org.cidarlab.minieugene.solver;

import java.util.List;

import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.Predicate;
import org.cidarlab.minieugene.symbol.Symbol;

public interface Solver {
	
	/*
	 * N          ... size of the design
	 * ids        ... the domains of the variables
	 * predicates ... the Eugene constraints
	 * number     ... number of desired solutions
	 */
	public List<Symbol[]> solve(int N, Symbol[] symbols, Predicate[] predicates, int number)
			throws EugeneException;
	
}
