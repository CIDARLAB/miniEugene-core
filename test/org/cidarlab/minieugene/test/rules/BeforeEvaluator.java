package org.cidarlab.minieugene.test.rules;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.constants.EugeneConstants;

public class BeforeEvaluator {

	private static final int MAX_DESIGN_SIZE = 10; 
	
	public void evaluate() {
		for(int i=2; i<=MAX_DESIGN_SIZE; i++) {
			System.out.println("*** i: "+i);
			String s=buildScript(i);
			
			System.out.println(s);
			
			try {
				MiniEugene me = new MiniEugene();				
				me.solve(s.split(System.getProperty("line.separator")), i, -1);
				System.out.println(
						me.getStatistics().getValueByKey(EugeneConstants.NUMBER_OF_SOLUTIONS));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String buildScript(int N) {
		String NEWLINE = System.getProperty("line.separator");
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<N; i++) {
			sb.append(i).append(" BEFORE ").append(i+1).append(NEWLINE);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		BeforeEvaluator be = new BeforeEvaluator();
		be.evaluate();
	}
}
