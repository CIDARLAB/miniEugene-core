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

package org.cidarlab.minieugene;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.cidarlab.minieugene.act.ACT;
import org.cidarlab.minieugene.constants.MiniEugeneConstants;
import org.cidarlab.minieugene.data.pigeon.WeyekinPoster;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.parser.MiniEugeneLexer;
import org.cidarlab.minieugene.parser.MiniEugeneParser;
import org.cidarlab.minieugene.predicates.LogicalAnd;
import org.cidarlab.minieugene.predicates.interaction.Interaction;
import org.cidarlab.minieugene.solver.jacop.JaCoPSolver;
import org.cidarlab.minieugene.symbol.SymbolTables;

/**
 * The MiniEugene main class for the utilization of miniEugene as an embedded jar.
 * 
 * @author Ernst Oberortner
 */
public class MiniEugene 
		implements IMiniEugene {
	
	/*
	 * reference to the symbol tables
	 */
	private SymbolTables symbols;
	
	private MiniEugeneStatistics stats;		
	private List<Component[]> solutions;
	
	/** 
	 * non-args constructor
	 */
	public MiniEugene() {
		// symbol tables
		this.symbols = new SymbolTables();
		
		// statistics
		this.stats = new MiniEugeneStatistics();
		
		// solutions
		this.solutions = null;
	}
	
	/**
	 * solve/3 method finds NR_OF_SOLUTIONS rule-compliant designs of size N. 
	 * 
	 * NR_OF_SOLUTIONS is a non-negative integer. 
	 *
	 * @param  rules                an array of Strings each representing one miniEugene rule
	 * @param  N                    the size of the design ( >0 )
	 * @param  NR_OF_SOLUTIONS      the number of desired solutions ( >0 )
	 */	
	@Override
	public void solve(String[] rules, int N, int NR_OF_SOLUTIONS) 
			throws MiniEugeneException {

		/*
		 * Error handling
		 */
		if(null == rules) {
			throw new MiniEugeneException("No rules provided!");
		}
		
		if(N < 1) {
			throw new MiniEugeneException("Invalid size of design!");
		}
		

		if(NR_OF_SOLUTIONS < -1) {
			throw new MiniEugeneException("Invalid number of required solutions!");
		}

		/*
		 * at the beginning of every run, we clear the symbol tables 
		 * that might contain symbols from earlier runs
		 */
		this.symbols.clear();

		/*
		 * also, clear the statistics
		 */
		this.stats.getMeasurements().clear();
		
		/*
		 * first, we need to build the miniEugene script
		 * based on the given information
		 */
		String script = this.buildScript(N, rules);
		
		/*
		 * then, we parse the miniEugene script
		 */
		LogicalAnd and = this.parse(script);
		
		/*
		 * and finally, we solve the problem
		 */
		this.solve(and, NR_OF_SOLUTIONS);
	}

	/**
	 * 
	 * @param N
	 * @param rules
	 * @return
	 */
	public String buildScript(int N, String[] rules) {
		StringBuilder sb = new StringBuilder();

		// N = number .
		sb.append("N=").append(N).append(".");
		
		// rules
		for(int i=0; i<rules.length; i++) {
			sb.append(rules[i]);
			if(!rules[i].endsWith(".")) {
				sb.append(".");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * solve/3 method finds ALL rule-compliant designs of size N. 
	 *
	 * @param  rules  an String array of miniEugene rules
	 * @param  N      the size of the design
	 */	
	@Override
	public void solve(String[] rules, int N) 
			throws MiniEugeneException {
		
		/*
		 * we look for ALL solutions...
		 * hence, we set N to -1 
		 */
		this.solve(rules, N, -1);
	}

	/**
	 * getStatistics/0 method returns statistics
	 * about the last execution of the solve method.
	 * 
	 * if the solve method hasn't been executed, then
	 * getStatistics/0 return NULL
	 * 
	 * @return an instance of the MiniEugeneStatistics class containing
	 *         key-value pairs representing the statistics of the last
	 *         execution of the solve method        
	 */	
	@Override
	public MiniEugeneStatistics getStatistics() {
		return this.stats;
	}

	/**
	 * getSolutions/0 returns the list of solutions found by
	 * the last execution of the solve method.
	 * 
	 * The list contains of arrays of Symbol objects that contain 
	 * information on each solution's symbols:
	 * - an integer ID (generated by miniEugene)
	 * - the name of the symbol (specified by the user as rule operands)
	 * - the orientation (forward/reverse)
	 *
	 * @return a List of Symbol arrays.        
	 */	
	@Override
	public List<Component[]> getSolutions() {
		return this.solutions;
	}
	
	@Override
	public Set<Interaction> getInteractions() {
		return this.symbols.getInteractions();
	}
	
	
	/**
	 * 
	 */
	public void solve(String script, int NR_OF_SOLUTIONS) 
		throws MiniEugeneException {
		
		/*
		 * first, we clear the symbol tables and statistics
		 * since they might contain information from the last run
		 */
		this.symbols.clear();
		this.stats.getMeasurements().clear();
		
		/*
		 * next, we parse the script
		 */
		try {
			LogicalAnd la = this.parse(script);
			this.solve(la, NR_OF_SOLUTIONS);
		} catch(MiniEugeneException e) {
			e.printStackTrace();
			throw new MiniEugeneException(e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public void solve(String script) 
			throws MiniEugeneException {
			
		/*
		 * first, we clear the symbol tables and statistics 
		 * since they might contain information from the last run
		 */
		this.symbols.clear();
		this.stats.getMeasurements().clear();
		
		/*
		 * next, we parse the script
		 */
		try {
			LogicalAnd la = this.parse(script);
			this.solve(la, -1);
		} catch(MiniEugeneException e) {
			throw new MiniEugeneException(e.getMessage());
		}
			
	}

	/**
	 * 
	 * @param script
	 * @return
	 */
	public LogicalAnd parse(String script) 
			throws MiniEugeneException {
		
		/*
		 * FIRST RUN
		 * we collect all facts
		 */
		try {
			this.collectFacts(script);
		} catch(MiniEugeneException me) {
			throw new MiniEugeneException(me.getMessage());
		}
		
		/*
		 * SECOND RUN
		 * we interpret all constraints
		 */
		return this.interpretConstraints(script);
	}
	
	private void collectFacts(String script) 
			throws MiniEugeneException {

		// Lexer
		MiniEugeneLexer lexer = new MiniEugeneLexer(
				new ANTLRStringStream(script));
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// Parser
		MiniEugeneParser parser = new MiniEugeneParser(tokens);

		// initialize the parser with the symboltables
		parser.init(this.symbols);
		
		// PARSING
		try {
			parser.setCollectFacts(true);
			parser.miniEugene();

//			parser.printFacts();
			
			if(parser.hasErrors()) {
				throw new MiniEugeneException("The script contains invalid characters!");
			}
		} catch(Exception e) {
			throw new MiniEugeneException(e.getMessage());
		}
	}

	/**
	 * The interpretConstraints/1 method compiles a given miniEugene script 
	 * into a logical conjunction (Conjunctive NormalForm) 
	 * of the specified constraints.
	 * 
	 * @param script  ... the miniEugene script
	 * @return ... a LogicalAnd object that represents the logical 
	 *             conjunction of the specified constraints
	 *             
	 * @throws MiniEugeneException
	 */
	private LogicalAnd interpretConstraints(String script) 
			throws MiniEugeneException {

		// Lexer
		MiniEugeneLexer lexer = new MiniEugeneLexer(
				new ANTLRStringStream(script));
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// Parser
		MiniEugeneParser parser = new MiniEugeneParser(tokens);

		// initialize the parser with the symboltables
		parser.init(this.symbols);
		
		// PARSING
		try {
			parser.setCollectFacts(false);
			parser.miniEugene();
			
			if(parser.hasErrors()) {
				throw new MiniEugeneException("The script contains invalid characters!");
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new MiniEugeneException(e.getMessage());
		}

		// finally, we return the create
		// Predicate objects
		return parser.getConstraint();
	}
	
	/**
	 * 
	 * @param predicates
	 * @param NR_OF_SOLUTIONS
	 */
	private void solve(LogicalAnd la, int NR_OF_SOLUTIONS) 
			throws MiniEugeneException {

		try {
			Component[] components = this.symbols.getComponents();

			if(null == components || components.length==0) {
				throw new MiniEugeneException("no solutions found!");
			}

			
//			int minN = la.getMinN();
			int maxN = la.getMaxN();
			
			
			/*
			 * RBC
			 */
//			if(EugeneRBC.upperBound(la, maxN) > 1000000) {
//				throw EugeneException("TOO BIG!");
//			}
			
			// future work:
//			if(maxN < minN) {
//				throw new EugeneException(
//						"The given max. length ("+maxN+") of the design is less than the calculated min. length ("+minN+")!");
//			}
			
			// calculate the number of possible solutions...
			int numberOfParts = components.length;
			
			// future work:
//			long possibleSolutions = 0;
//			for(int i=minN; i<=maxN; i++) {
//				possibleSolutions += 
//						Math.pow(numberOfParts, i) * Math.pow(2, i);
//			}
			
			this.stats.add(MiniEugeneConstants.NUMBER_OF_PARTS, numberOfParts);
			this.stats.add(MiniEugeneConstants.NUMBER_OF_TYPES, this.symbols.getTypesSize());
			this.stats.add(MiniEugeneConstants.MAXIMUM_LENGTH_OF_DESIGN, la.getMaxN());
			this.stats.add(MiniEugeneConstants.DESIGN_SPACE, (Math.pow(numberOfParts, maxN) * Math.pow(2, maxN)));
			this.stats.add(MiniEugeneConstants.NUMBER_OF_RULES, la.getNumberOfRules());

			// TODO
//			this.stats.add(EugeneConstants.MINIMUM_LENGTH_OF_DESIGN, la.getMinN());
			
			/*
			 * ACT
			 */
//			this.symbols.getACT().constructACT(la.getPredicates());
			
			/*
			 * SOLUTION FINDING
			 */
			long T1 = System.nanoTime();
			this.solutions = new JaCoPSolver(this.symbols).solve(components, la, NR_OF_SOLUTIONS);
			long T2 = System.nanoTime();

			if(null != solutions) {
				this.stats.add(MiniEugeneConstants.NUMBER_OF_SOLUTIONS, solutions.size());
			} else {
				this.stats.add(MiniEugeneConstants.NUMBER_OF_SOLUTIONS, 0);
			}					
				

			/*
			 * next, we iterate over the predicates and check if there are any
			 * SOME_REVERSE directionality predicates
			 */
			stats.add(MiniEugeneConstants.SOLUTION_FINDING_TIME, 
					(T2-T1)*Math.pow(10, -9));

			if(null == solutions || solutions.size()==0) {
				throw new MiniEugeneException("no solutions found!");
			}

		} catch(Exception e) {
			e.printStackTrace();
			throw new MiniEugeneException(e.getMessage());
		}
	}
	
	/*
	 * ONLY FOR TESTING PURPOSE
	 */
	protected void executeScript(String script, int N, int NR_OF_SOLUTIONS) 
			throws MiniEugeneException {
		
		if(null == script || script.isEmpty()) {
			throw new MiniEugeneException("please provide some input!");
		}

		/*
		 * at the beginning of every run, we clear the symbol tables 
		 * that might contain symbols from earlier runs
		 */
		this.symbols.clear();
				
		try {
			LogicalAnd la = this.parse(script);			
			this.solve(la, NR_OF_SOLUTIONS);
		} catch(Exception e) {
			throw new MiniEugeneException(e.getMessage());
		}

	}

	@Override
	public URI visualizeACT() 
			throws MiniEugeneException {
		ACT act = this.symbols.getACT();
		if(null != act) {
			String gv = act.toGraphViz();
			WeyekinPoster.setDotText(gv);
			return WeyekinPoster.postMyVision();
		}
		return null;
	}
	
	
	public LogicalAnd getCNF(String script) 
			throws MiniEugeneException {
		
		// first, we clear the symbol tables
		this.symbols.clear();
		
		return this.parse(script);
	}
	
	public void printFacts() {
		for(int i=0; i<this.symbols.getComponents().length; i++) {
			System.out.println(this.symbols.getComponents()[i]);
		}
	}

}
