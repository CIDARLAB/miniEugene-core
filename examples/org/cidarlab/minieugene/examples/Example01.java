package org.cidarlab.minieugene.examples;

import java.util.Arrays;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.Symbol;
import org.cidarlab.minieugene.exception.EugeneException;

public class Example01 {

	/*
	 * the utilization of miniEugene is divided into the following steps:
	 * 1. instantiate MiniEugene
	 * 2. specify an String array, each row containing a miniEugene rule
	 * 3. let miniEugene solve the rules (using MiniEugene's solve() method)
	 * 4. process the solutions and/or have a look into the statistics of the solving process
	 */
	public static void main(String[] args) {
		/*
		 * STEP 1 
		 */
		MiniEugene me = new MiniEugene();
		
		/*
		 * STEP 2
		 * 
		 */
		String[] rules = {"CONTAINS a", "CONTAINS b", "CONTAINS c"};
		
		try {
		
			/*
			 * STEP 3: 
			 * here we set N to 3
			 */
			me.solve(rules, 3);     

		} catch(EugeneException e) {
			/*
			 * do some exception handling here
			 */
			e.printStackTrace();
		}		
			
		/*
		 * STEP 4.1:
		 * processing the solutions 
		 * just printing them to the console
		 */
		if(null != me.getSolutions()) {
			for(Symbol[] solution : me.getSolutions()) {
				System.out.println(Arrays.toString(solution));
			}
		}
		
		/*
		 * STEP 4.2:
		 * dump the statistics
		 */
		if(null != me.getStatistics()) {
			me.getStatistics().print();
		}
		
	}
}
