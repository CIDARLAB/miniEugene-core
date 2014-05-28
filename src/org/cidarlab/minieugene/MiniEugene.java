/*
Copyright (c) 2014 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
 */

package org.cidarlab.minieugene;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.cidarlab.minieugene.act.ACT;
import org.cidarlab.minieugene.constants.EugeneConstants;
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
		
		this.stats = new MiniEugeneStatistics();
		
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
		 * first, we clear the symbol tables 
		 * since they might contain symbols from the last run
		 */
		this.symbols.clear();

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
		 * first, we clear the symbol tables 
		 * since they might contain symbols from the last run
		 */
		this.symbols.clear();

		/*
		 * next, we parse the script
		 */
		try {
			LogicalAnd la = this.parse(script);
			this.solve(la, -1);
		} catch(MiniEugeneException e) {
			e.printStackTrace();
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
			parser.miniEugene();
			
			if(parser.hasErrors()) {
				throw new MiniEugeneException("The script contains invalid characters!");
			}
		} catch(Exception e) {
			//e.printStackTrace();
			throw new MiniEugeneException(e.getMessage());
		}

		// finally, we return the create
		// Predicate objects
		return parser.getPredicate();
	}
	
	/**
	 * 
	 * @param predicates
	 * @param NR_OF_SOLUTIONS
	 */
	private void solve(LogicalAnd la, int NR_OF_SOLUTIONS) 
			throws MiniEugeneException {

		try {
			Component[] symbols = this.symbols.getSymbols();

			if(null == symbols || symbols.length==0) {
				throw new MiniEugeneException("no solutions found!");
			}

			int minN = la.getMinN();
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
			int numberOfParts = symbols.length;
			
			// future work:
//			long possibleSolutions = 0;
//			for(int i=minN; i<=maxN; i++) {
//				possibleSolutions += 
//						Math.pow(numberOfParts, i) * Math.pow(2, i);
//			}
			
			this.stats.add(EugeneConstants.NUMBER_OF_PARTS, symbols.length);
			this.stats.add(EugeneConstants.DESIGN_SPACE, (Math.pow(numberOfParts, maxN) * Math.pow(2, maxN)));
			this.stats.add(EugeneConstants.NUMBER_OF_RULES, la.getNumberOfRules());
			this.stats.add(EugeneConstants.MAXIMUM_LENGTH_OF_DESIGN, la.getMaxN());

			// TODO
//			this.stats.add(EugeneConstants.MINIMUM_LENGTH_OF_DESIGN, la.getMinN());
			
			/*
			 * ACT
			 */
			this.symbols.getACT().constructACT(la.getPredicates());
			
			/*
			 * SOLUTION FINDING
			 */
			long T1 = System.nanoTime();
			this.solutions = new JaCoPSolver(this.symbols).solve(symbols, la, NR_OF_SOLUTIONS);
			long T2 = System.nanoTime();

			if(null != solutions) {
				this.stats.add(EugeneConstants.NUMBER_OF_SOLUTIONS, solutions.size());
			} else {
				this.stats.add(EugeneConstants.NUMBER_OF_SOLUTIONS, 0);
			}					
				

			/*
			 * next, we iterate over the predicates and check if there are any
			 * SOME_REVERSE directionality predicates
			 */
			stats.add(EugeneConstants.SOLUTION_FINDING_TIME, 
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

}
