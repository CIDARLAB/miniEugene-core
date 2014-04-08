package org.cidarlab.minieugene.fsm;

import java.io.StreamTokenizer;
import java.io.StringReader;

import org.cidarlab.minieugene.fsm.RegularExpressionParser.Expression;

import JaCoP.constraints.Reified;
import JaCoP.constraints.regular.Regular;
import JaCoP.core.BooleanVar;
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

public class FSMExample {

	private Store store;
	
	public FSMExample() {
		this.store = new Store();
	}
	public static void main(String[] args) {
		FSMExample ex = new FSMExample();
		
		ex.search(
				ex.model((int)Math.pow(2, 2)));
	}
	
	public IntVar[] model(int N) {
//		FSM g = new FSM(); 
//
		IntVar[] var = new IntVar[N]; 
		for(int i=0; i<N; i++) {
			var[i] = new IntVar(store, "v"+i, 1, N); 
		}
//		 
////		for(int i=0; i<N-1; i++) {
////			store.impose(
////					new XltY(
////							(IntVar)store.findVariable("v"+i), 
////							(IntVar)store.findVariable("v"+(i+1))));
////		}
//		
//		// STATES
//		FSMState[] s = new FSMState[N+1]; 
//		for (int i=0; i<s.length; i++) { 
//		      s[i] = new FSMState(); 
//		      g.allStates.add(s[i]); 
//		} 
//		g.initState = s[0]; 
//		g.finalStates.add(s[N]);
//		
//		
//		for(int i=0; i<N-1; i+=2) {
//			// TRANSITIONS
//			
//			int k=1;
//	    	s[i].transitions.add(
//	  			  new FSMTransition(
//	  					  new IntervalDomain(k, k), 
//	  					  s[i+1])); 
//
//	    	k++;
//	    	
//	    	s[i+1].transitions.add(
//		  			  new FSMTransition(
//		  					  new IntervalDomain(k, k), 
//		  					  s[i+2])); 
//	    	
//	    	k++;
//	    	
//	    	g.finalStates.add(s[i]);
//		}
//    	
//		Regular r = new Regular(g, var);
//		r.earlyTerminationOK = true;
//		store.imposeDecomposition(r);
//		//store.impose(r);
//		
//		store.print();
		
		StringReader sr = new StringReader("(2)*");

		try {
			RegularExpressionParser rep = new RegularExpressionParser(sr);

			Expression exp = null;
			while((exp = rep.parse(false)) != null) {
				FSM fsm = exp.parseToFSM();
//				System.out.println(fsm.toString());
				
				store.impose(new Regular(fsm, var));
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

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
