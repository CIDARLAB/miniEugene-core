package org.cidarlab.minieugene.solver;

import java.util.List;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.LogicalAnd;

public interface Solver {
	
	/**
	 * 
	 * @param N  ... size of the design
	 * @param symbols ... the domains of the variables
	 * @param and ... the Eugene constraints
	 * @param number ... number of desired solutions
	 * @return
	 * @throws EugeneException
	 */
	public List<Component[]> solve(int N, Component[] symbols, LogicalAnd and, int number)
			throws EugeneException;
	
}
