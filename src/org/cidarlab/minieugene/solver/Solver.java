package org.cidarlab.minieugene.solver;

import java.util.List;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.LogicalAnd;

public interface Solver {
	
	/**
	 * 
	 * @param symbols   ... all components bound by constraints
	 * @param and       ... the conjunction of miniEugene constraints
	 * @param solutions ... the number of desired solutions
	 * @return
	 * @throws EugeneException
	 */
	public List<Component[]> solve(Component[] components, LogicalAnd and, int solutions)
			throws EugeneException;
	
}
