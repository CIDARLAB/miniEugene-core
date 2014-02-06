package org.cidarlab.minieugene.test.rules;

import org.cidarlab.minieugene.MiniEugene;

public class ContainsEvaluator {

	private static final int MAX_DESIGN_SIZE = 10; 
	
	public void evaluate() {
		
		for(int i=1; i<=MAX_DESIGN_SIZE; i++) {

			System.out.println("*** i: "+i);

			String[] rules = new String[i];
			for(int k=1; k<=i; k++) {
				rules[k-1] = new String("CONTAINS i"+k);
			}
						
			try {
				MiniEugene me = new MiniEugene();				
				me.solve(rules, i, -1);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	public String buildScript(int N) {
		String NEWLINE = System.getProperty("line.separator");
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			sb.append("CONTAINS i").append(i).append(NEWLINE);
		}
		return sb.toString();
	}
	**/
	public static void main(String[] args) {
		ContainsEvaluator ce = new ContainsEvaluator();
		ce.evaluate();
	}
}
