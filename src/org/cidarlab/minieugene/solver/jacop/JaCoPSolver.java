package org.cidarlab.minieugene.solver.jacop;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.minieugene.Symbol;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.Predicate;
import org.cidarlab.minieugene.predicates.interaction.Induces;
import org.cidarlab.minieugene.predicates.interaction.InteractionPredicate;
import org.cidarlab.minieugene.predicates.interaction.Represses;
import org.cidarlab.minieugene.solver.Solver;
import org.cidarlab.minieugene.symbol.SymbolTables;

import JaCoP.constraints.Alldifferent;
import JaCoP.constraints.Constraint;
import JaCoP.constraints.And;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.core.Domain;
import JaCoP.core.IntVar;
import JaCoP.core.Store;
import JaCoP.core.ValueEnumeration;
import JaCoP.search.DepthFirstSearch;
import JaCoP.search.IndomainSimpleRandom;
import JaCoP.search.MostConstrainedDynamic;
import JaCoP.search.Search;
import JaCoP.search.SelectChoicePoint;
import JaCoP.search.SimpleMatrixSelect;
import JaCoP.search.SimpleSelect;


public class JaCoPSolver 
		implements Solver {

	private Store store;
	private SymbolTables symbols;
	private int N;
	
	public JaCoPSolver(SymbolTables symbols) {
		this.store = new Store();
		this.symbols = symbols;
	}
	
	public List<Symbol[]> solve(int N, Symbol[] symbols, Predicate[] predicates, int NR_OF_SOLUTIONS)
			throws EugeneException {

		this.N = N;
		
    	/*
    	 * create the variables of the constraint solving problem
    	 * i.e. the parts
    	 */
    	IntVar[][] variables = this.model(symbols);

    	/*
    	 * map the Eugene rules onto JaCoP constraints
    	 */
    	if(null != predicates) {
    		this.imposeConstraints(variables, predicates);
    	}
    	
    	/*
    	 * for testing: print the store's information
    	 */
//    	store.print();
    	
    	/*
    	 * now, let's solve the problem
    	 */
    	Domain[][] solutions = this.search(variables, NR_OF_SOLUTIONS);
    	
    	/*
    	 * finally, we process and return the solutions
    	 */
    	if(null != solutions) {
    		try {
    			return this.processSolutions(solutions);
    		} catch(java.lang.OutOfMemoryError e) {
    			throw new EugeneException("I'm sorry! This problem is currently too big for me to solve!");
    			
    		}
    	} 
    	return null;
	}
    
	private IntVar[][] model(Symbol[] symbols) 
			throws EugeneException {
		
		IntVar[][] variables = new IntVar[3][N];
				/*
				 * 0 ... Parts
				 * 1 ... Types
				 * 2 ... Orientation
				 */
		
		/*
		 * PARTS
		 */
		variables[Variables.PART] = new IntVar[N];
		variables[Variables.TYPE] = new IntVar[N];
		variables[Variables.ORIENTATION] = new IntVar[N];
		
		/*
		 * for every position i (0 <= i < N), 
		 * we have three variables:
		 * P_i ... parts
		 * T_i ... types
		 * O_i ... orientation
		 */
		for(int i=0; i<N; i++) {

			variables[Variables.PART][i] = new IntVar(store, "P"+i);
			variables[Variables.TYPE][i] = new IntVar(store, "T"+i);
			variables[Variables.ORIENTATION][i] = new IntVar(store, "O"+i);
			
			PrimitiveConstraint[] pc = new PrimitiveConstraint[symbols.length];
			
			for(int j=0; j<symbols.length; j++) {						
				variables[Variables.PART][i].addDom(symbols[j].getId(), symbols[j].getId());
				variables[Variables.TYPE][i].addDom(symbols[j].getType(), symbols[j].getType());
			
				/*
				 * we also impose constraints that part and type match
				 * so we avoid various permutations were the part and type do not match
				 */
				pc[j] = new And(
								new XeqC(variables[Variables.PART][i], symbols[j].getId()),
								new XeqC(variables[Variables.TYPE][i], symbols[j].getType()));
			}
			store.impose(new Or(pc));

			variables[Variables.ORIENTATION][i].addDom(-1, -1);
			variables[Variables.ORIENTATION][i].addDom( 1,  1);
				/*
				 * -1 ... reverse
				 *  1 ... forward
				 */			
		}
		
		return variables;
	}

	public void imposeConstraints(IntVar[][] variables, Predicate[] predicates) 
			throws EugeneException {
		/*
		 * per default, all parts have a FORWARD orientation
		 */
		for(int i=0; i<predicates.length; i++) {
			try {
				if(predicates[i] instanceof Represses ||
						predicates[i] instanceof Induces) {
					this.symbols.putInteraction((InteractionPredicate)predicates[i]);
				} else {
					Constraint constraint = predicates[i].toJaCoP(this.store, variables);
					if(constraint != null) {
						if(constraint instanceof And) {
							for(PrimitiveConstraint pc : ((And)constraint).listOfC) {
								//store.imposeWithConsistency(pc);
								store.impose(pc);
							}
						} else {
							store.impose(constraint);
						}
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
				throw new EugeneException("I cannot impose "+predicates[i]);
			}
		}
	}
	
    private Domain[][] search(IntVar[][] variables, int NR_OF_SOLUTIONS) 
    		throws EugeneException {
    	Search<IntVar> search = new DepthFirstSearch<IntVar>(); 

        SelectChoicePoint<IntVar> select = new SimpleMatrixSelect<IntVar>(
				variables, 
				new MostConstrainedDynamic<IntVar>(), 
				new IndomainSimpleRandom<IntVar>());  

        if(NR_OF_SOLUTIONS != (-1)) {
        	search.getSolutionListener().setSolutionLimit(NR_OF_SOLUTIONS);
        } else {
            search.getSolutionListener().searchAll(true);   
        }
  

        search.setPrintInfo(false);
        search.getSolutionListener().recordSolutions(true);
                
		try {
			/*
			 * search the solutions
			 */
			search.labeling(store, select);
		} catch(OutOfMemoryError oome) {
			throw new EugeneException("I'm sorry! This problem is currently too big for me to solve!");
		} catch(Exception e) {
			e.printStackTrace();
		}

		/*
		 * return the solutions
		 */
		return search.getSolutionListener().getSolutions();
    }

		
	public List<Symbol[]> processSolutions(Domain[][] solutions) {
		
		List<Symbol[]> lst = new ArrayList<Symbol[]>();
		for(int i=0; i<solutions.length && solutions[i]!=null; i++) {
			
			Domain[] solution = solutions[i];
			
			Symbol[] sol = new Symbol[this.N];

			for(int j=0; j<this.N; j++) {
				Symbol symbol = null;
				
				/*
				 * PART
				 */
				ValueEnumeration ve = solution[j].valueEnumeration();
				while(ve.hasMoreElements()) {
					Symbol old = this.symbols.get(ve.nextElement());
					symbol = new Symbol(old.getName());
				}
				
				/*
				 * ORIENTATION
				 */
				ve = solution[j+(Variables.ORIENTATION * N)].valueEnumeration();
				while(ve.hasMoreElements()) {
					if(ve.nextElement() == (-1)) {
						symbol.setForward(false);
					}
				}				

				sol[j] = symbol;
			}
			
			lst.add(sol);
		}
		return lst;
	}
    
}
