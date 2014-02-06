package org.cidarlab.minieugene.examples;

import org.cidarlab.minieugene.MiniEugene;

public class Example02 {

	public static void main(String[] args) {
		/*
		 * first, instantiate miniEugene
		 */
		MiniEugene me = new MiniEugene();
		
		/*
		 * then, define some rules 
		 * (in a String array)
		 */
		String[] rules = {
				"CONTAINS a", "CONTAINS b", "CONTAINS c", 
				"CONTAINS d", "CONTAINS e", "CONTAINS f", 
				"CONTAINS g", "CONTAINS h", "CONTAINS i",
				"CONTAINS j", "CONTAINS k", "CONTAINS l", 
				"a BEFORE b", "b BEFORE c", "c BEFORE d",
				"d BEFORE e", "e BEFORE f", "f BEFORE g",
				"g BEFORE h", "h BEFORE i", "i BEFORE j",
				"j BEFORE k", "k BEFORE l"};
		try {
			
			/*
			 * let miniEugene solve the problem
			 * N=12 and we'd like to have 20 solutions
			 */
			me.solve(rules, 12, 20);

		} catch(Exception e) {
			e.printStackTrace();
		}		
			
		/*
		 * print the number of solutions
		 * (should be equal to 20)
		 */
		if(null != me.getSolutions()) {
			System.out.println("Number of Solutions: "+me.getSolutions().size());
		}
		
		/*
		 * dump the statistics
		 */
		if(null != me.getStatistics()) {
			me.getStatistics().print();
		}
			
	}

}
