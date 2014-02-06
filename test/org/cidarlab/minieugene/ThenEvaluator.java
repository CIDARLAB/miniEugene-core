package org.cidarlab.minieugene;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.constants.EugeneConstants;

public class ThenEvaluator {

	private static final int MAX_DESIGN_SIZE = 10; 
	private static final String NEWLINE = System.getProperty("line.separator"); 
	
	public void evaluate() {
		for(int i=2; i<=MAX_DESIGN_SIZE; i++) {
			System.out.println("*** i: "+i);
			String s=buildScript(i);
			
//			System.out.println(s);
			
			try {
				MiniEugene me = new MiniEugene();				
	            me.solve(s.split(NEWLINE), i, -1);

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
			sb.append(i).append(" THEN ").append(i+1).append(NEWLINE);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		ThenEvaluator te = new ThenEvaluator();
		te.evaluate();
	}
}
