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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.dom.ComponentType;
import org.cidarlab.minieugene.dom.Identified;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.LogicalAnd;
import org.cidarlab.minieugene.predicates.Constraint;
import org.cidarlab.minieugene.predicates.interaction.Induces;
import org.cidarlab.minieugene.predicates.interaction.Interaction;
import org.cidarlab.minieugene.predicates.interaction.Represses;
import org.cidarlab.minieugene.predicates.templating.Sequence;
import org.cidarlab.minieugene.solver.Solver;
import org.cidarlab.minieugene.symbol.SymbolTables;

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
			throws MiniEugeneException {

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
	

//	/**
//	 * 
//	 * @param components   ... the components of the design
//	 * @param N            ... the length of the design
//	 * @return	
//	 */
//	private IntVar[] model_positioning(Component[] components, int N) {
//		IntVar[] variables = new IntVar[components.length];
//		for(int i=0; i<components.length; i++) {
//			Component c = components[i];
//			variables[i] = new IntVar(store, c.getName()+".position", 0, N-1);
//		}
//		store.impose(new Alldifferent(variables));
//		return variables;
//	}
	
	private IntVar[][] model(Component[] components) 
			throws MiniEugeneException {

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
			
			/*
			 *  PART and TYPE IDs 
			 */
			Set<Integer> typeIds = new HashSet<Integer>();
			for(int j=0; j<components.length; j++) {						
				variables[Variables.PART][i].addDom(components[j].getId(), components[j].getId());
				variables[Variables.TYPE][i].addDom(components[j].getTypeId(), components[j].getTypeId());

				// here we collect the type ids
				// to impose constraints on type-component relations later
				typeIds.add(components[j].getTypeId());

				/*
				 * we also impose constraints that part and type must match
				 * so we avoid various permutations were the part and type do not match
				 * 
				 * Example:
				 * IF part == "p1" THEN type = "type_of_p1"
				 */
				store.impose(new IfThen(
								new XeqC(variables[Variables.PART][i], components[j].getId()),
								new XeqC(variables[Variables.TYPE][i], components[j].getTypeId())));
			}
			
			/*
			 * We also need to impose that types imply specific parts
			 * 
			 * Example: 
			 * IF type == "T" THEN part = "instance1_of_T" \/ part = "instance2_of_T" \/ ... \/ part = "instanceK_of_T"
			 */			
			for(int j=0; j<components.length; j++) {
				
				for(Integer typeId : typeIds) {

					Set<Component> typeComponents = this.symbols.getComponents(
							(ComponentType)this.symbols.get(typeId));
					
					PrimitiveConstraint[] pcTypes = new PrimitiveConstraint[typeComponents.size()];
					int k = 0;
					for(Component c : typeComponents) {
						pcTypes[k++] = new IfThen(
								new XeqC(variables[Variables.TYPE][i], typeId),
								new XeqC(variables[Variables.PART][i], c.getId()));
					}
					store.impose(new Or(pcTypes));
				}
			}


			
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
			throws MiniEugeneException {
		
		for(Constraint constraint : and.getConstraints()) {

			try {
				
				/*
				 * if we have an interaction predicate (esp represses or induces), 
				 * then we store the relation in the symbol tables 
				 * (for later visualization)
				 */
				if(constraint instanceof Induces || constraint instanceof Represses) {
					this.symbols.putInteraction(
							(Interaction)constraint);
				} 
				
				PrimitiveConstraint pc = 
						constraint.toJaCoP(this.store, variables);

				if(pc != null) {
					if(pc instanceof And) {

						// option 1
						for(PrimitiveConstraint c : ((And)pc).listOfC) {
							store.impose(c);
						}

					} else {
						store.impose(pc);
					}
				}

			} catch(Exception e) {
				throw new MiniEugeneException(e.getMessage());
			}

		}
	}
	

    private List<Component[]> search(IntVar[][] variables, int NR_OF_SOLUTIONS) 
    		throws MiniEugeneException {

    	if(!store.consistency()) {
    		throw new MiniEugeneException("Inconsistent rules!");
    	}
    	
		// first, let's search for the orientations
		Search<IntVar> labelOrientation = new DepthFirstSearch<IntVar>();
		SelectChoicePoint<IntVar> selectOrientation = 
			new SimpleSelect<IntVar>(variables[Variables.ORIENTATION], 
					new SmallestMin<IntVar>(), 
					new SmallestDomain<IntVar>(),
					new IndomainMin<IntVar>());
		
		labelOrientation.setSelectChoicePoint(selectOrientation);
		labelOrientation.setPrintInfo(false);

		// then, let's search for the types
		Search<IntVar> labelTypes = new DepthFirstSearch<IntVar>();
		SelectChoicePoint<IntVar> selectTypes = 
			new SimpleSelect<IntVar>(
					variables[Variables.TYPE], 
					new SmallestMin<IntVar>(), 
					new SmallestDomain<IntVar>(),
					new IndomainMin<IntVar>());
		
		labelTypes.setSelectChoicePoint(selectTypes);
		labelTypes.setPrintInfo(false);

		// finally, let's search for the parts
		Search<IntVar> labelParts = new DepthFirstSearch<IntVar>();
        SelectChoicePoint<IntVar> select = new SimpleMatrixSelect<IntVar>(
				variables, 
				new MostConstrainedDynamic<IntVar>(), 
				new IndomainSimpleRandom<IntVar>());  
        labelParts.addChildSearch(labelOrientation);
        labelParts.addChildSearch(labelTypes);
        
//        MiniEugeneSolutionListener<IntVar> listener = new MiniEugeneSolutionListener<IntVar>(this.symbols, this.N);
//        search.setSolutionListener(listener);
        
        if(NR_OF_SOLUTIONS != (-1)) {
        	labelParts.getSolutionListener().setSolutionLimit(NR_OF_SOLUTIONS);
        } else {
//        	search.getSolutionListener().setSolutionLimit(MiniEugeneSolutionListener.MAX_NR_OF_SOLUTIONS);
        	labelParts.getSolutionListener().searchAll(true);   
        }

        labelParts.setPrintInfo(false);
        labelParts.getSolutionListener().recordSolutions(true);

		try {
			/*
			 * search the solutions
			 */
			labelParts.labeling(store, select);
		} catch(OutOfMemoryError oome) {
			throw new MiniEugeneException("I'm sorry! This problem is currently too big for me to solve!");
		}

//		labelParts.getSolutionListener().printAllSolutions();

		/*
		 * return the solutions
		 */
		if(null == labelParts.getSolutionListener().getSolutions()) {
			return null;
		}
		return this.processSolutions(labelParts.getSolutionListener().getSolutions());
//		return null;
//		return listener.getMiniEugeneSolutions();
    }

		
	public List<Component[]> processSolutions(Domain[][] solutions) 
			throws MiniEugeneException {

		/*
		 * here, we also need a cloner
		 */

		List<Component[]> lst = new ArrayList<Component[]>();
		for(int i=0; i<solutions.length && solutions[i]!=null; i++) {
			
			Domain[] solution = solutions[i];
			
			Component[] sol = new Component[this.N];

			for(int j=0; j<this.N; j++) {
				Component component = null;
				
				/*
				 * PART
				 */
				ValueEnumeration ve = solution[j].valueEnumeration();
				while(ve.hasMoreElements()) {
					// interim solution ... no orientation
					int id = ve.nextElement(); 
					Identified idObj = this.symbols.get(id);
					if(null == idObj) {
						throw new MiniEugeneException("I cannot find any component with id "+id);
					}
					if(idObj instanceof Component) {
						// we need to create a new Component instance in memory
						component = new Component(
								((Component)idObj).getName(), 
								((Component)idObj).getType());
					}
				}
				
				/*
				 * ORIENTATION
				 */
				ve = solution[j+(Variables.ORIENTATION * N)].valueEnumeration();
				while(ve.hasMoreElements()) {
					if(ve.nextElement() == (-1)) {
						component.setForward(false);
					}
				}				

				sol[j] = component;
			}
			
			lst.add(sol);
		}
		return lst;
	}
    
	
	/*
	 * OPTIMIZATIONS
	 */
	public void optimize(IntVar[][] variables, LogicalAnd and) 
			throws MiniEugeneException {
			
		List<Sequence> sequences = new ArrayList<Sequence>();
		for(Constraint predicate : and.getConstraints()) {
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
	
//	private PrimitiveConstraint sequenceStartsAt(IntVar[][] variables, Sequence seq, int idx) {
//		PrimitiveConstraint[] sel = new PrimitiveConstraint[seq.getComponents().get(0).size()];
//		int k=0;
//		for(Component c : seq.getComponents().get(0)) {
//			sel[k++] = new XeqC(variables[Variables.PART][idx], c.getId());
//		}
//		return new Or(sel);
//		
//	}

//  private Domain[][] optSearch(IntVar[][] variables, IntVar[] position_variables, int NR_OF_SOLUTIONS) 
//	throws EugeneException {
//
//boolean result = store.consistency();
//if(!result) {
//	throw new EugeneException("Inconsistent constraints!");
//}
//
//long T1, T2, T;
//T1 = System.currentTimeMillis();
//
//
//// first, let's search for the orientations
//Search<IntVar> labelOrientation = new DepthFirstSearch<IntVar>();
//SelectChoicePoint<IntVar> selectOrientation = 
//	new SimpleSelect<IntVar>(variables[Variables.ORIENTATION], 
//			new SmallestMin<IntVar>(), 
//			new SmallestDomain<IntVar>(),
//			new IndomainMin<IntVar>());
//
//labelOrientation.setSelectChoicePoint(selectOrientation);
//labelOrientation.setPrintInfo(false);
//
////// then, let's search for the positions
////Search<IntVar> labelPositioning = new DepthFirstSearch<IntVar>();
////SelectChoicePoint<IntVar> selectPositioning = 
////	new SimpleSelect<IntVar>(
////			position_variables, 
////			new SmallestMin<IntVar>(), 
////			new SmallestDomain<IntVar>(),
////			new IndomainMin<IntVar>());
////
////labelPositioning.setSelectChoicePoint(selectPositioning);
////labelPositioning.setPrintInfo(false);
//
//// finally, let's search for the parts
//Search<IntVar> labelParts = new DepthFirstSearch<IntVar>();
//SelectChoicePoint<IntVar> selectParts = 
//	new SimpleSelect<IntVar>(
//			variables[Variables.PART], 
//			new SmallestMin<IntVar>(), 
//			new SmallestDomain<IntVar>(),
//			new IndomainMin<IntVar>());
//
//labelParts.addChildSearch(labelOrientation);
////labelParts.addChildSearch(labelPositioning);
//
//if(NR_OF_SOLUTIONS != (-1)) {
//	labelParts.getSolutionListener().setSolutionLimit(NR_OF_SOLUTIONS);
//} else {
//	labelParts.getSolutionListener().searchAll(true);   
//}
//
//labelParts.setPrintInfo(false);
//labelParts.getSolutionListener().recordSolutions(true);
//        
//try {
//	/*
//	 * search the solutions
//	 */
//	result = labelParts.labeling(store, selectParts);
//
//} catch(OutOfMemoryError oome) {
//	throw new EugeneException("I'm sorry! This problem is currently too big for me to solve!");
//} catch(Exception e) {
//	throw new EugeneException(e.toString());
//}
//
//T2 = System.currentTimeMillis();
//T = T2 - T1;
//
//String s = String.format("%.2f", (float)T/1000);
//System.out.println("\n\t*** Solution Finding Time = "+ s + " s");
//
///*
// * return the solutions
// */
////System.out.println("*** ORIENTATIONS ***");
////labelOrientation.getSolutionListener().printAllSolutions();
////System.out.println("*** PARTS ***");
////labelParts.getSolutionListener().printAllSolutions();
//return labelParts.getSolutionListener().getSolutions();
//}

//private Domain[][] search(IntVar[] variables) 
//	throws EugeneException {
//
//long T1, T2, T;
//T1 = System.currentTimeMillis();
//
//Search<IntVar> labelPositions = new DepthFirstSearch<IntVar>();
//SelectChoicePoint<IntVar> selectParts = 
//	new SimpleSelect<IntVar>(
//			variables, 
//			new SmallestMin<IntVar>(), 
//			new SmallestDomain<IntVar>(),
//			new IndomainMin<IntVar>());
//
//labelPositions.getSolutionListener().searchAll(true);     
//labelPositions.setPrintInfo(true);
//labelPositions.getSolutionListener().recordSolutions(true);
//        
//try {
//	/*
//	 * search the solutions
//	 */
//	labelPositions.labeling(store, selectParts);
//
//} catch(OutOfMemoryError oome) {
//	throw new EugeneException("I'm sorry! This problem is currently too big for me to solve!");
//} catch(Exception e) {
//	throw new EugeneException(e.toString());
//}
//
//T2 = System.currentTimeMillis();
//T = T2 - T1;
//
//String s = String.format("%.2f", (float)T/1000);
//System.out.println("\n\t*** Solution Finding Time = "+ s + " s");
//
///*
// * return the solutions
// */
//System.out.println("*** POSITIONS ***");
////labelPositions.getSolutionListener().printAllSolutions();
//return labelPositions.getSolutionListener().getSolutions();
//}


}
