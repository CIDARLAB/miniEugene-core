package org.cidarlab.minieugene.solver.jacop;

import java.util.Arrays;
import java.util.List;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.symbol.SymbolTables;

import JaCoP.core.Domain;
import JaCoP.core.ValueEnumeration;
import JaCoP.core.Var;
import JaCoP.search.Search;
import JaCoP.search.SelectChoicePoint;
import JaCoP.search.SimpleSolutionListener;
import JaCoP.search.SolutionListener;

public class MiniEugeneSolutionListener<T extends Var> 
	extends SimpleSolutionListener<T> 
	implements SolutionListener<T> {

	private SymbolTables symbols; 

	private SolutionPoolManager poolManager;
	
	public MiniEugeneSolutionListener(SymbolTables symbols, int N) {
		this.symbols = symbols;
		
		/*
		 * Solution Pool Manager
		 */
		this.poolManager = new SolutionPoolManager(5000, N);			
	}
	
	@Override
	public boolean executeAfterSolution(
			Search<T> search, 
			SelectChoicePoint<T> select) {
		boolean parent = super.executeAfterSolution(search, select);

		this.processSolution(search.getSolution());

		if(this.poolManager.getCurrentNumberOfSolutions() > 20000) {
			// i.e. we stop searching
			return true;
		}
		
		return parent;
	}
	
	private void processSolution(Domain[] solution) {
		Component[] sol = this.poolManager.getFreeSpot();

		int si = 0;
		
		/*
		 * PART
		 */
		for(int i=0; i<solution.length/3; i++, si++) {
			
			Domain part = solution[i];
			ValueEnumeration ve = part.valueEnumeration();
			while(ve.hasMoreElements()) {
				int k = ve.nextElement();
				sol[si] = new Component(this.symbols.get(k).getName());
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
