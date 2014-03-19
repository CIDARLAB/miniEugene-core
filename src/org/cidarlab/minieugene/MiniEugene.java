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
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.interaction.Interaction;
import org.cidarlab.minieugene.parser.MiniEugeneLexer;
import org.cidarlab.minieugene.parser.MiniEugeneParser;
import org.cidarlab.minieugene.predicates.LogicalAnd;
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
	//private PredicateBuilder pb;
	
	private MiniEugeneStatistics stats;		
	private List<Component[]> solutions;
	
	/** 
	 * non-args constructor
	 */
	public MiniEugene() {
		this.symbols = new SymbolTables();
		//this.pb = new PredicateBuilder(this.symbols);
		
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
			throws EugeneException {

		/*
		 * Error handling
		 */
		if(null == rules) {
			throw new EugeneException("No rules provided!");
		}
		
		if(N < 1) {
			throw new EugeneException("Invalid size of design!");
		}
		

		if(NR_OF_SOLUTIONS < -1) {
			throw new EugeneException("Invalid number of required solutions!");
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
			throws EugeneException {
		
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
		throws EugeneException {
		
		try {
			this.solve(script);
		} catch(EugeneException ee) {
			throw new EugeneException(ee.getMessage());
		}
		
		/*
		 * TODO: pick NR_OF_SOLUTIONS solutions randomly
		 */
	}
	
	/**
	 * 
	 */
	public void solve(String script) 
			throws EugeneException {
			
		/*
		 * at the beginning of every run, we clear the symbol tables 
		 * that might contain symbols from earlier runs
		 */
		this.symbols.clear();

		/*
		 * first, we parse the script
		 */
		try {
			LogicalAnd la = this.parse(script);
			this.solve(la, -1);
		} catch(EugeneException e) {
			throw new EugeneException(e.getMessage());
		}
			
	}

	/**
	 * 
	 * @param script
	 * @return
	 */
	public LogicalAnd parse(String script) 
			throws EugeneException {
		
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
		} catch(Exception e) {
			throw new EugeneException(e.getMessage());
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
			throws EugeneException {

		try {
			Component[] symbols = this.symbols.getSymbols();

			if(null == symbols || symbols.length==0) {
				throw new EugeneException("no solutions found!");
			}

			this.stats.add(EugeneConstants.NUMBER_OF_PARTS, symbols.length);
			this.stats.add(EugeneConstants.POSSIBLE_SOLUTIONS, 
					Math.pow(symbols.length, la.getN()) * Math.pow(2, la.getN()));
			this.stats.add(EugeneConstants.NUMBER_OF_RULES, la.getSize());

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
				throw new EugeneException("no solutions found!");
			}

		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
	}
	
	/*
	 * ONLY FOR TESTING PURPOSE
	 */
	protected void executeScript(String script, int N, int NR_OF_SOLUTIONS) 
			throws EugeneException {
		
		if(null == script || script.isEmpty()) {
			throw new EugeneException("please provide some input!");
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
			throw new EugeneException(e.getMessage());
		}

	}

	@Override
	public URI visualizeACT() 
			throws EugeneException {
		String gv = (this.symbols.getACT()).toGraphViz();
		WeyekinPoster.setDotText(gv);
		return WeyekinPoster.postMyVision();
	}
	

}
