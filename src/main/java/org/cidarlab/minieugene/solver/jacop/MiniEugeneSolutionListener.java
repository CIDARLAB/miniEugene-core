/*
 * Copyright (c) 2014, Boston University
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 *    
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *    
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.minieugene.solver.jacop;

import java.util.List;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.dom.Identified;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.symbol.SymbolTables;

import org.jacop.core.Domain;
import org.jacop.core.ValueEnumeration;
import org.jacop.core.Var;
import org.jacop.search.Search;
import org.jacop.search.SelectChoicePoint;
import org.jacop.search.SimpleSolutionListener;
import org.jacop.search.SolutionListener;

public class MiniEugeneSolutionListener<T extends Var> 
	extends SimpleSolutionListener<T> 
	implements SolutionListener<T> {

	private SymbolTables symbols; 
	private SolutionPoolManager poolManager;
	
	public static final int MAX_NR_OF_SOLUTIONS = 50000;
	
	public MiniEugeneSolutionListener(SymbolTables symbols, int N) {
		this.symbols = symbols;
		
		/*
		 * Solution Pool Manager
		 */
		this.poolManager = new SolutionPoolManager(MAX_NR_OF_SOLUTIONS, N);			
	}
	
	@Override
	public boolean executeAfterSolution(
			Search<T> search, 
			SelectChoicePoint<T> select) {
		boolean parent = super.executeAfterSolution(search, select);

		try {
			this.processSolution(search.getSolution());
		} catch(MiniEugeneException mee) {
			// we ignore this, for the time being
		}

		if(this.poolManager.getCurrentNumberOfSolutions() > MAX_NR_OF_SOLUTIONS) {
			// i.e. we stop searching
			return true;
		}
		
		return parent;
	}
	
	private void processSolution(Domain[] solution) 
			throws MiniEugeneException {
		Component[] sol = this.poolManager.getFreeSpot();

		int si = 0;
		
		/*
		 * PART
		 */
		for(int i=0; i<solution.length/3; i++, si++) {
			
			Domain part = solution[i];
			ValueEnumeration ve = part.valueEnumeration();
			while(ve.hasMoreElements()) {
				int id = ve.nextElement();
				Identified idObj = this.symbols.get(id);
				if(null == idObj) {
					throw new MiniEugeneException("I cannot find any component with id "+id);
				}
				if(idObj instanceof Component) {
					// we need to create a new Component instance in memory
					sol[si] = new Component(
							((Component)idObj).getName(), 
							((Component)idObj).getType());
				}
			}
			
			Domain orient = solution[i+2*(solution.length/3)];
			ve = orient.valueEnumeration();
			while(ve.hasMoreElements()) {
				if(ve.nextElement() == (-1)) {
					sol[si].setForward(false);
				}
			}						
		}
		
//		System.out.println(Arrays.toString(sol));
		
		/*
		 * here, we put the solution back intoto the solutionpool
		 */
		this.poolManager.putSolution(sol);
	}
	
	public List<Component[]> getMiniEugeneSolutions() {
		return this.poolManager.getSolutions();
	}
}
