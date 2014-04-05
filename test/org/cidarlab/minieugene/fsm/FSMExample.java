package org.cidarlab.minieugene.fsm;

import JaCoP.constraints.XltY;
import JaCoP.constraints.regular.Regular;
import JaCoP.core.IntVar;
import JaCoP.core.IntervalDomain;
import JaCoP.core.Store;
import JaCoP.search.DepthFirstSearch;
import JaCoP.search.IndomainMin;
import JaCoP.search.MostConstrainedStatic;
import JaCoP.search.Search;
import JaCoP.search.SelectChoicePoint;
import JaCoP.search.SimpleSelect;
import JaCoP.util.fsm.FSM;
import JaCoP.util.fsm.FSMState;
import JaCoP.util.fsm.FSMTransition;

public class FSMExample {

	private Store store;
	
	public FSMExample() {
		this.store = new Store();
	}
	public static void main(String[] args) {
		FSMExample ex = new FSMExample();
		
		ex.search(
				ex.model(4));
	}
	
	public IntVar[] model(int N) {
		FSM g = new FSM(); 

		IntVar[] var = new IntVar[N]; 
		for(int i=0; i<N; i++) {
			var[i] = new IntVar(store, "v"+i, 0, N-1); 
		}
		 
//		for(int i=0; i<N-1; i++) {
//			store.impose(
//					new XltY(
//							(IntVar)store.findVariable("v"+i), 
//							(IntVar)store.findVariable("v"+(i+1))));
//		}
		
		// STATES
		FSMState[] s = new FSMState[N+1]; 
		for (int i=0; i<s.length; i++) { 
		      s[i] = new FSMState(); 
		      g.allStates.add(s[i]); 
		} 
		g.initState = s[0]; 
		g.finalStates.add(s[N]);
		
		
		// TRANSITIONS
    	s[0].transitions.add(
  			  new FSMTransition(
  					  new IntervalDomain(0, N-1), 
  					  s[1])); 
		for(int i=1; i<N; i++) {
			s[i].transitions.add(
	    			  new FSMTransition(
	    					  new IntervalDomain(i, i), 
	    					  s[N]));
			
			for(int j=0; j<N; j++) {
				if(j != i) {
					s[i].transitions.add(
							new FSMTransition(
									new IntervalDomain(j, j), 
									s[i+1]));
				}
			}
			
			s[i].transitions.add(
	    			  new FSMTransition(
	    					  new IntervalDomain(i-1, i-1), 
	    					  s[i-1]));

			g.finalStates.add(s[i]);
		}
//    	s[N-1].transitions.add(
//    			  new FSMTransition(
//    					  new IntervalDomain(0, N-1), 
//    					  s[N-1])); 
		 
		store.impose(new Regular(g, var));
		
		store.print();
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
}
