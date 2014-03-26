package org.cidarlab.minieugene.solver.jacop;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.cidarlab.minieugene.act.ACT;
import org.cidarlab.minieugene.data.pigeon.WeyekinPoster;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.LogicalAnd;
import org.cidarlab.minieugene.predicates.LogicalOr;
import org.cidarlab.minieugene.predicates.LogicalPredicate;
import org.cidarlab.minieugene.predicates.Predicate;
import org.cidarlab.minieugene.predicates.counting.BinaryContains;
import org.cidarlab.minieugene.predicates.counting.Contains;
import org.cidarlab.minieugene.predicates.counting.CountingPredicate;
import org.cidarlab.minieugene.predicates.interaction.Induces;
import org.cidarlab.minieugene.predicates.interaction.InteractionPredicate;
import org.cidarlab.minieugene.predicates.interaction.Represses;
import org.cidarlab.minieugene.solver.Solver;
import org.cidarlab.minieugene.symbol.SymbolTables;
import org.cidarlab.minieugene.util.SolutionExporter;

import JaCoP.constraints.Alldifferent;
import JaCoP.constraints.And;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XeqY;
import JaCoP.constraints.XgtY;
import JaCoP.constraints.XltY;
import JaCoP.core.Domain;
import JaCoP.core.IntVar;
import JaCoP.core.Store;
import JaCoP.core.ValueEnumeration;
import JaCoP.search.DepthFirstSearch;
import JaCoP.search.IndomainMin;
import JaCoP.search.IndomainSimpleRandom;
import JaCoP.search.MostConstrainedDynamic;
import JaCoP.search.MostConstrainedStatic;
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
	
	public List<Component[]> solve(Component[] components, LogicalAnd and, int NR_OF_SOLUTIONS)
			throws EugeneException {

		this.N = and.getN();

		// first, build the abstract syntax tree
//		buildACT(and);
		
    	/*
    	 * create the variables of the constraint solving problem
    	 * i.e. the parts
    	 */
    	IntVar[][] variables = this.model(components);
    	
//    	this.solve_pos(components);

    	/*
    	 * map the Eugene rules onto JaCoP constraints
    	 */
    	if(null != and) {
    		this.imposeConstraints(variables, and);
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
	

//	/*
//	 * 
//	 */
//	private int calculateMinN(Component[] components, Predicate[] predicates) {
//
//		Store store = new Store();
//		
//		HashMap<Integer, Component> count = new HashMap<Integer, Component>();
//
//		// init the hashmap
//		for(int i=0; i<components.length; i++) {
//			count.put(components[i].getId(), components[i]);
//		}
//		
//		int n = 0;
//		for(int i=0; i<predicates.length; i++) {
//			
//			Predicate p = predicates[i];
//			
//			if(p instanceof CountingPredicate) {
//				String name = count.get(((CountingPredicate) p).getA()).getName();
//				
//				IntVar var = (IntVar)store.findVariable(name);
//				if(var == null) {
//					var = new IntVar(store, name);
//				}
//				
//				if(p instanceof Contains) {
//					var.addDom(1, 1);
//				} else if(p instanceof Exactly) {
//					var.addDom(((Exactly)p).getN(), ((Exactly)p).getN());
//				} else if(p instanceof MoreThan) {
//					var.addDom(((MoreThan)p).getN() + 1, Integer.MAX_VALUE);
//				} 
//			} else if(p instanceof LogicalNot) {
//				Predicate subP = ((LogicalNot)p).getPredicate();
//				String name = count.get(((CountingPredicate) subP).getA()).getName();
//				
//				IntVar var = (IntVar)store.findVariable(name);
//				if(var == null) {
//					var = new IntVar(store, name);
//				}
//
//				if(p instanceof Contains) {
//					var.addDom(0, 0);
//				} else if(subP instanceof Exactly) {
//					var.addDom(0, Integer.MAX_VALUE);
//					store.impose(new XneqC(var, ((Exactly)p).getN()));
//				} else if(subP instanceof MoreThan) {
//					var.addDom(0, ((MoreThan)p).getN());
//				} 
//				
//			} else if(p instanceof AllAfter || p instanceof SomeAfter ||
//					p instanceof AllBefore || p instanceof SomeBefore ||
//					p instanceof AllNextTo || p instanceof SomeNextTo) {
//				String nameA = count.get(((BinaryPredicate) p).getA()).getName();
//				String nameB = count.get(((BinaryPredicate) p).getB()).getName();
//				
//				IntVar varA = (IntVar)store.findVariable(nameA);
//				if(varA == null) {
//					varA = new IntVar(store, nameA);
//				}
//
//				IntVar varB = (IntVar)store.findVariable(nameB);
//				if(varB == null) {
//					varB = new IntVar(store, nameB);
//				}
//				
//				varA.addDom(0, Integer.MAX_VALUE);
//				varB.addDom(0, Integer.MAX_VALUE);
//				
//			} else if(p instanceof StartsWith || p instanceof EndsWith) {
//				String nameA = count.get(((UnaryPredicate) p).getA()).getName();
//				
//				IntVar varA = (IntVar)store.findVariable(nameA);
//				if(varA == null) {
//					varA = new IntVar(store, nameA);
//				}
//
//				varA.addDom(1, Integer.MAX_VALUE);
//			} else if(p instanceof Then || p instanceof With) {
//				String nameA = count.get(((BinaryPredicate) p).getA()).getName();
//				String nameB = count.get(((BinaryPredicate) p).getB()).getName();
//				
//				IntVar varA = (IntVar)store.findVariable(nameA);
//				if(varA == null) {
//					varA = new IntVar(store, nameA);
//				}
//
//				IntVar varB = (IntVar)store.findVariable(nameB);
//				if(varB == null) {
//					varB = new IntVar(store, nameB);
//				}
//				
//				if(p instanceof Then) {
//					varA.addDom(0, Integer.MAX_VALUE);
//					varB.addDom(0, Integer.MAX_VALUE);
//					store.impose(new IfThen(new XgtC(varA, 1), new XgtC(varA, 1)));
//				} else if(p instanceof With) {
//					varA.addDom(1, Integer.MAX_VALUE);
//					varB.addDom(1, Integer.MAX_VALUE);
//				}
//				
//			}
//		}
//		
//		store.print();
//
//		return n;
//	}
	
//    private IntVar[] temporal_model(Component[] components) {
//    	
//    	/*
//    	 * every component gets the following variables
//    	 * - start, end 
//    	 * - 
//    	 */
//    	for(int i=0; i<components.length; i++) {
//    		Component c = components[i];
//    		
//    		new IntVar(store, c.getName()+".count");
//    		new IntVar(store, c.getName()+".start");
//    		new IntVar(store, c.getName()+".end");
//    		new IntVar(store, c.getName()+".orientation", -1, 1);
//    		new IntVar(store, c.getName()+".position", 0, Integer.MAX_VALUE);
//    		
//    	}
//    	
//    	store.print();
//    	return null;
//    }
    
	private void solve_pos(Component[] symbols) 
			throws EugeneException {
		
		Store st = new Store();
		
		int i = 0, j = 0;
		IntVar[] variables = new IntVar[symbols.length * 3];
		IntVar[] pos_variables = new IntVar[symbols.length];
		for(Component c : symbols) {

			// ID
			variables[i] = new IntVar(st, c.getName()+".id");
			variables[i++].addDom(
					this.symbols.getId(c.getName()), 
					this.symbols.getId(c.getName()));
			
			// POSITION
			variables[i++] = new IntVar(st, c.getName()+".pos", 0, N-1); 
			pos_variables[j++] = (IntVar)st.findVariable(c.getName()+".pos");
			
			// ORIENTATION
			variables[i] = new IntVar(st, c.getName()+".orientation");
			variables[i].addDom(-1, -1);
			variables[i++].addDom(1, 1);
		}
		
		// different positions
		st.impose(new Alldifferent(pos_variables));
		

		// pIn1 BEFORE cRep
		
		imposeBefore(st, "pIn1", "cRep");
		imposeBefore(st, "pIn2", "cRep");
		imposeBefore(st, "cRep", "pOut");
		imposeBefore(st, "pOut", "cOut");
		

	
		// now, we label the variables 
		// and search for appropriate assignments
		
		SelectChoicePoint<IntVar> select = 
				new SimpleSelect<IntVar>(
						variables,
						new MostConstrainedStatic<IntVar>(), 
						new IndomainMin<IntVar>());

		Search<IntVar> label = new DepthFirstSearch<IntVar>();
		
		label.getSolutionListener().searchAll(true);
		label.getSolutionListener().recordSolutions(true);
		label.setAssignSolution(true);
		
		boolean result = label.labeling(st, select);
		if(result) {
			List<Component[]> solutions = this.processPositioningSolutions(
					label.getSolutionListener().getSolutions());
			
			SolutionExporter se = new SolutionExporter(solutions, 
					null);

			try {
				// ACT -> GraphViz
//				URI act = me.visualizeACT();
					
				// PIGEON
				URI pig = se.toPigeon();
				WeyekinPoster.launchPage(pig);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void imposeBefore(Store st, String lhs, String rhs) {
		// pIn1 and cRep must have the same orientation
		st.impose(
				new XeqY(
					(IntVar)st.findVariable(lhs+".orientation"), 
					(IntVar)st.findVariable(rhs+".orientation")));
				
		// if pIn1 is forward oriented, 
		// then pIn1 should be BEFORE cRep...
		st.impose(
				new IfThen(
					new XeqC((IntVar)st.findVariable(lhs+".orientation"), 1),
					new XltY((IntVar)st.findVariable(lhs+".pos"), 
							 (IntVar)st.findVariable(rhs+".pos"))));

		// if pIn1 is reverse oriented, 
		// then cRep should be BEFORE pIn1...
		st.impose(
				new IfThen(
						new XeqC((IntVar)st.findVariable(lhs+".orientation"), -1),
						new XgtY((IntVar)st.findVariable(lhs+".pos"), 
								 (IntVar)st.findVariable(rhs+".pos"))));
	}
	
	public List<Component[]> processPositioningSolutions(Domain[][] solutions) {
		List<Component[]> lst = new ArrayList<Component[]>();
		for(int i=0; i<solutions.length && solutions[i]!=null; i++) {
			
			Domain[] solution = solutions[i];
			
			Component[] sol = new Component[solution.length/3];

			for(int j=0; j<solution.length; j++) {
				
				Component symbol = null;

				/*
				 * PART ID
				 */
				ValueEnumeration id = solution[j++].valueEnumeration();
				while(id.hasMoreElements()) {
					Component old = this.symbols.get(id.nextElement());
					symbol = new Component(old.getName());
				}
				
				/*
				 * PART POSITION
				 */
				int position = -1;
				ValueEnumeration pos = solution[j++].valueEnumeration();
				while(pos.hasMoreElements()) {
					position = pos.nextElement(); 
				}
				if(-1 != position) {
					sol[position] = symbol;
				}
				
				/*
				 * ORIENTATION
				 */
				ValueEnumeration orientation = solution[j].valueEnumeration();
				while(orientation.hasMoreElements()) {
					if(orientation.nextElement() == (-1)) {
						symbol.setForward(false);
					}
				}				

			}
			
			lst.add(sol);
		}
		return lst;		
	}
	
	private IntVar[][] model(Component[] symbols) 
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
				variables[Variables.TYPE][i].addDom(symbols[j].getTypeId(), symbols[j].getTypeId());
			
				/*
				 * we also impose constraints that part and type match
				 * so we avoid various permutations were the part and type do not match
				 */
				pc[j] = new And(
								new XeqC(variables[Variables.PART][i], symbols[j].getId()),
								new XeqC(variables[Variables.TYPE][i], symbols[j].getTypeId()));
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

	public void imposeConstraints(IntVar[][] variables, LogicalAnd and) 
			throws EugeneException {
				
		for(Predicate predicate : and.getPredicates()) {
			try {
				
				/*
				 * if we have an interaction predicate (esp represses or induces), 
				 * then we store the relation in the symbol tables 
				 * (for later visualization)
				 */
				if(predicate instanceof Represses || 
						predicate instanceof Induces) {
					this.symbols.putInteraction(
							(InteractionPredicate)predicate);
				} 
				
				
				PrimitiveConstraint constraint = predicate.toJaCoP(
						this.store, variables);
				
				if(constraint != null) {
					if(constraint instanceof And) {
						for(PrimitiveConstraint pc : ((And)constraint).listOfC) {
							store.impose(pc);
						}
					} else {
						store.impose(constraint);
					}
				}

			} catch(Exception e) {
				e.printStackTrace();
				throw new EugeneException("I cannot impose "+predicate);
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

		
	public List<Component[]> processSolutions(Domain[][] solutions) {
		
		List<Component[]> lst = new ArrayList<Component[]>();
		for(int i=0; i<solutions.length && solutions[i]!=null; i++) {
			
			Domain[] solution = solutions[i];
			
			Component[] sol = new Component[this.N];

			for(int j=0; j<this.N; j++) {
				Component symbol = null;
				
				/*
				 * PART
				 */
				ValueEnumeration ve = solution[j].valueEnumeration();
				while(ve.hasMoreElements()) {
					Component old = this.symbols.get(ve.nextElement());
					symbol = new Component(old.getName());
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
