package org.cidarlab.minieugene.fsm;

import JaCoP.constraints.Sequence;
import JaCoP.constraints.Stretch;
import JaCoP.core.IntVar;
import JaCoP.core.IntervalDomain;
import JaCoP.core.Store;
import JaCoP.search.DepthFirstSearch;
import JaCoP.search.IndomainMin;
import JaCoP.search.MostConstrainedStatic;
import JaCoP.search.Search;
import JaCoP.search.SelectChoicePoint;
import JaCoP.search.SimpleSelect;

public class SequenceExample {

	private Store store;
	
	public SequenceExample() {
		this.store = new Store();
	}
	
	public IntVar[] modelSequence() {
		IntVar[] var = new IntVar[4]; 
		for (int i=0; i<var.length; i++) { 
			var[i] = new IntVar(store, "v"+i, 0, 10);
		}
		 
		// Each sub-sequence of size 2 must contain 2 (min = 2 and max = 2) values 1 or 2.
		store.imposeDecomposition(new Sequence(var,                  	// variable list 
											new IntervalDomain(2, 2), 	// set of values 
											2, 							// q, sequence length 
											1, 							// min 
											1  							// max 
								));	
		 
		return var;
	}
	
	
	public IntVar[] modelStretch() {
		IntVar[] var = new IntVar[3]; 
		for (int i=0; i<var.length; i++) { 
			var[i] = new IntVar(store, "v"+i, 1, 3);
		}

		store.imposeDecomposition( 
                new Stretch(new int[] {1, 2, 3},  // values 
                            new int[] {2, 1, 3},  // min for 1 & 2 
                            new int[] {3, 3, 3},  // max for 1 & 2 
                            var // variables 
                       ));
		return var;
	}
	
	public void search(IntVar[] variables) {
		SelectChoicePoint<IntVar> select = 
				new SimpleSelect<IntVar>(
						variables,
						new MostConstrainedStatic<IntVar>(), 
						new IndomainMin<IntVar>());

		Search<IntVar> label = new DepthFirstSearch<IntVar>();
		
		label.getSolutionListener().searchAll(true);
		label.getSolutionListener().recordSolutions(true);
		label.setAssignSolution(true);
		
		boolean result = label.labeling(store, select);
		if(result) {
			System.out.println("solutions found!");
			label.getSolutionListener().printAllSolutions();
		}
	}

	public static void main(String[] args) {
		
		SequenceExample se = new SequenceExample();
		// SEQUENCE
//		se.search(se.modelSequence());
		
		// STRETCH
		se.search(se.modelStretch());
	}

}
