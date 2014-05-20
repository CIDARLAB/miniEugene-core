package org.cidarlab.minieugene.solver.jacop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.LogicalAnd;
import org.cidarlab.minieugene.predicates.Predicate;
import org.cidarlab.minieugene.predicates.interaction.Induces;
import org.cidarlab.minieugene.predicates.interaction.Interaction;
import org.cidarlab.minieugene.predicates.interaction.Represses;
import org.cidarlab.minieugene.predicates.templating.Sequence;
import org.cidarlab.minieugene.solver.Solver;
import org.cidarlab.minieugene.symbol.SymbolTables;

import JaCoP.constraints.Alldifferent;
import JaCoP.constraints.And;
import JaCoP.constraints.ExtensionalSupportVA;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.core.Domain;
import JaCoP.core.IntVar;
import JaCoP.core.Store;
import JaCoP.core.ValueEnumeration;
import JaCoP.search.DepthFirstSearch;
import JaCoP.search.IndomainMin;
import JaCoP.search.IndomainSimpleRandom;
import JaCoP.search.LDS;
import JaCoP.search.MostConstrainedDynamic;
import JaCoP.search.Search;
import JaCoP.search.SelectChoicePoint;
import JaCoP.search.SimpleMatrixSelect;
import JaCoP.search.SimpleSelect;
import JaCoP.search.SmallestDomain;
import JaCoP.search.SmallestMin;


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

		this.N = and.getMaxN();

		// first, build the abstract syntax tree
//		buildACT(and);
		
    	/*
    	 * create the variables of the constraint solving problem
    	 * i.e. the parts
    	 */
//		IntVar[] positioning_variables = this.model_positioning(components, N);
    	IntVar[][] variables = this.model(components);
    	
//    	this.solve_pos(components);

    	/*
    	 * map the Eugene rules onto JaCoP constraints
    	 */
    	if(null != and) {
    		this.imposeConstraints(variables, and);

        	/*
        	 * and let's try to do some optimizations
        	 */
    		this.optimize(variables, and);
    	}
    	
    	
    	/*
    	 * for testing: print the store's information
    	 */
//    	store.print();
    	
    	/*
    	 * now, let's solve the problem
    	 */
//    	Domain[][] solutions = this.search(positioning_variables);
    	
//    	Domain[][] solutions = this.optSearch(variables, positioning_variables, NR_OF_SOLUTIONS);
    	return this.search(variables, NR_OF_SOLUTIONS);
    	
    	
    	/*
    	 * finally, we process and return the solutions
    	 */
//    	if(null != solutions) {
//    		try {
//    			return this.processSolutions(solutions);
//    		} catch(java.lang.OutOfMemoryError e) {
//    			throw new EugeneException("I'm sorry! This problem is currently too big for me to solve!");
//    			
//    		}
//    	} 
	}
	

	/**
	 * 
	 * @param components   ... the components of the design
	 * @param N            ... the length of the design
	 * @return	
	 */
	private IntVar[] model_positioning(Component[] components, int N) {
		IntVar[] variables = new IntVar[components.length];
		for(int i=0; i<components.length; i++) {
			Component c = components[i];
			variables[i] = new IntVar(store, c.getName()+".position", 0, N-1);
		}
		store.impose(new Alldifferent(variables));
		return variables;
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
				pc[j] = 
						new IfThen(
								new XeqC(variables[Variables.PART][i], symbols[j].getId()),
								new XeqC(variables[Variables.TYPE][i], symbols[j].getTypeId()));
			}
			store.impose(new And(pc));
			
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
							(Interaction)predicate);
				} 
				
				PrimitiveConstraint constraint = predicate.toJaCoP(
						this.store, variables);

				if(constraint != null) {
					if(constraint instanceof And) {

						// option 1
						for(PrimitiveConstraint pc : ((And)constraint).listOfC) {
							store.impose(pc);
						}

					} else {
						store.impose(constraint);
					}
				}

			} catch(Exception e) {
				throw new EugeneException(e.getMessage());
			}

		}
	}
	
//    private Domain[][] optSearch(IntVar[][] variables, IntVar[] position_variables, int NR_OF_SOLUTIONS) 
//    		throws EugeneException {
//    	
//		boolean result = store.consistency();
//		if(!result) {
//			throw new EugeneException("Inconsistent constraints!");
//		}
//		
//		long T1, T2, T;
//		T1 = System.currentTimeMillis();
//		
//		
//		// first, let's search for the orientations
//		Search<IntVar> labelOrientation = new DepthFirstSearch<IntVar>();
//		SelectChoicePoint<IntVar> selectOrientation = 
//			new SimpleSelect<IntVar>(variables[Variables.ORIENTATION], 
//					new SmallestMin<IntVar>(), 
//					new SmallestDomain<IntVar>(),
//					new IndomainMin<IntVar>());
//		
//		labelOrientation.setSelectChoicePoint(selectOrientation);
//		labelOrientation.setPrintInfo(false);
//
////		// then, let's search for the positions
////		Search<IntVar> labelPositioning = new DepthFirstSearch<IntVar>();
////		SelectChoicePoint<IntVar> selectPositioning = 
////			new SimpleSelect<IntVar>(
////					position_variables, 
////					new SmallestMin<IntVar>(), 
////					new SmallestDomain<IntVar>(),
////					new IndomainMin<IntVar>());
////		
////		labelPositioning.setSelectChoicePoint(selectPositioning);
////		labelPositioning.setPrintInfo(false);
//
//		// finally, let's search for the parts
//		Search<IntVar> labelParts = new DepthFirstSearch<IntVar>();
//		SelectChoicePoint<IntVar> selectParts = 
//			new SimpleSelect<IntVar>(
//					variables[Variables.PART], 
//					new SmallestMin<IntVar>(), 
//					new SmallestDomain<IntVar>(),
//					new IndomainMin<IntVar>());
//
//		labelParts.addChildSearch(labelOrientation);
////		labelParts.addChildSearch(labelPositioning);
//		
//        if(NR_OF_SOLUTIONS != (-1)) {
//        	labelParts.getSolutionListener().setSolutionLimit(NR_OF_SOLUTIONS);
//        } else {
//        	labelParts.getSolutionListener().searchAll(true);   
//        }
//  
//        labelParts.setPrintInfo(false);
//        labelParts.getSolutionListener().recordSolutions(true);
//                
//		try {
//			/*
//			 * search the solutions
//			 */
//			result = labelParts.labeling(store, selectParts);
//
//		} catch(OutOfMemoryError oome) {
//			throw new EugeneException("I'm sorry! This problem is currently too big for me to solve!");
//		} catch(Exception e) {
//			throw new EugeneException(e.toString());
//		}
//
//		T2 = System.currentTimeMillis();
//		T = T2 - T1;
//
//		String s = String.format("%.2f", (float)T/1000);
//		System.out.println("\n\t*** Solution Finding Time = "+ s + " s");
//		
//		/*
//		 * return the solutions
//		 */
////		System.out.println("*** ORIENTATIONS ***");
////		labelOrientation.getSolutionListener().printAllSolutions();
////		System.out.println("*** PARTS ***");
////		labelParts.getSolutionListener().printAllSolutions();
//		return labelParts.getSolutionListener().getSolutions();
//    }

//    private Domain[][] search(IntVar[] variables) 
//    		throws EugeneException {
//		
//		long T1, T2, T;
//		T1 = System.currentTimeMillis();
//
//		Search<IntVar> labelPositions = new DepthFirstSearch<IntVar>();
//		SelectChoicePoint<IntVar> selectParts = 
//			new SimpleSelect<IntVar>(
//					variables, 
//					new SmallestMin<IntVar>(), 
//					new SmallestDomain<IntVar>(),
//					new IndomainMin<IntVar>());
//
//		labelPositions.getSolutionListener().searchAll(true);     
//		labelPositions.setPrintInfo(true);
//		labelPositions.getSolutionListener().recordSolutions(true);
//                
//		try {
//			/*
//			 * search the solutions
//			 */
//			labelPositions.labeling(store, selectParts);
//
//		} catch(OutOfMemoryError oome) {
//			throw new EugeneException("I'm sorry! This problem is currently too big for me to solve!");
//		} catch(Exception e) {
//			throw new EugeneException(e.toString());
//		}
//
//		T2 = System.currentTimeMillis();
//		T = T2 - T1;
//
//		String s = String.format("%.2f", (float)T/1000);
//		System.out.println("\n\t*** Solution Finding Time = "+ s + " s");
//		
//		/*
//		 * return the solutions
//		 */
//		System.out.println("*** POSITIONS ***");
////		labelPositions.getSolutionListener().printAllSolutions();
//		return labelPositions.getSolutionListener().getSolutions();
//    }
	

    private List<Component[]> search(IntVar[][] variables, int NR_OF_SOLUTIONS) 
    		throws EugeneException {

    	if(!store.consistency()) {
    		throw new EugeneException("Inconsistent rules!");
    	}
    	
    	Search<IntVar> search = new DepthFirstSearch<IntVar>(); 

        SelectChoicePoint<IntVar> select = new SimpleMatrixSelect<IntVar>(
				variables, 
				new MostConstrainedDynamic<IntVar>(), 
				new IndomainSimpleRandom<IntVar>());  
        
//        MiniEugeneSolutionListener<IntVar> listener = new MiniEugeneSolutionListener<IntVar>(this.symbols, this.N);
//        search.setSolutionListener(listener);
        
        if(NR_OF_SOLUTIONS != (-1)) {
        	search.getSolutionListener().setSolutionLimit(NR_OF_SOLUTIONS);
        } else {
//        	search.getSolutionListener().setSolutionLimit(MiniEugeneSolutionListener.MAX_NR_OF_SOLUTIONS);
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

//		search.getSolutionListener().printAllSolutions();
		/*
		 * return the solutions
		 */

		if(null == search.getSolutionListener().getSolutions()) {
			return null;
		}
		return this.processSolutions(search.getSolutionListener().getSolutions());
//		return null;
//		return listener.getMiniEugeneSolutions();
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
					// interim solution ... no orientation
//					symbol = this.symbols.get(ve.nextElement());
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
    
	
	/*
	 * OPTIMIZATIONS
	 */
	public void optimize(IntVar[][] variables, LogicalAnd and) 
			throws EugeneException {
			
		List<Sequence> sequences = new ArrayList<Sequence>();
		for(Predicate predicate : and.getPredicates()) {
			if(predicate instanceof Sequence) {
				
				Sequence seq = (Sequence)predicate;
				
				// analyse the sequence
				for(List<Component> lst : seq.getComponents()) {
					if(lst.size() > 1) {
						// we cannot optimize this right now
						return;
					}
				}
				
				sequences.add(seq);
			}
		}
		
		if(!sequences.isEmpty()) {
			this.sequenceOptimize(variables, sequences);
		}
	}	
	
	private void sequenceOptimize(IntVar[][] variables, List<Sequence> sequences) {
				
		// first, we permute the list of sequences
		List<List<Sequence>> lstPerm = this.permute(sequences);

		// let's try extensional support
		int max_cols = 0;
		for(Sequence seq : lstPerm.get(0)) {
			max_cols += seq.getComponents().size();
		}
		
		if(max_cols == variables[Variables.PART].length) {
			int[][] ext = new int[lstPerm.size()][max_cols];
			int row = 0;
			for(List<Sequence> lst : lstPerm) {
				int col = 0;
				for(Sequence sequence : lst) {
					for(int i=0; i<sequence.getComponents().size(); i++) {
						ext[row][col++] = sequence.getComponents().get(i).get(0).getId();
					}
				}
				row ++;
			}
			store.impose(new ExtensionalSupportVA(variables[Variables.PART], ext));
		}
	}
	
	// sequence permute
	private List<List<Sequence>> permute(List<Sequence> sequences) {

        if(sequences.size()==1){
            List<Sequence> arrayList = new ArrayList<Sequence>();
            arrayList.add(sequences.get(0));
            List<List<Sequence> > listOfList = new ArrayList<List<Sequence>>();
            listOfList.add(arrayList);
            return listOfList;
        }

        Set<Sequence> setOf = new HashSet<Sequence>(sequences);   

        List<List<Sequence>> listOfLists = new ArrayList<List<Sequence>>();

        for(Sequence i: sequences){
            ArrayList<Sequence> arrayList = new ArrayList<Sequence>();
            arrayList.add(i);

            Set<Sequence> setOfCopied = new HashSet<Sequence>();
            setOfCopied.addAll(setOf);
            setOfCopied.remove(i);

            List<Sequence> isttt = new ArrayList<Sequence>(setOfCopied);
//            setOfCopied.toArray(isttt);

            List<List<Sequence>> permute = permute(isttt);
            Iterator<List<Sequence>> iterator = permute.iterator();
            while (iterator.hasNext()) {
                List<Sequence> list = iterator.next();
                list.add(i);
                listOfLists.add(list);
            }
        }   

        return listOfLists;
    }
	
	private PrimitiveConstraint sequenceStartsAt(IntVar[][] variables, Sequence seq, int idx) {
		PrimitiveConstraint[] sel = new PrimitiveConstraint[seq.getComponents().get(0).size()];
		int k=0;
		for(Component c : seq.getComponents().get(0)) {
			sel[k++] = new XeqC(variables[Variables.PART][idx], c.getId());
		}
		return new Or(sel);
		
	}
	
}
