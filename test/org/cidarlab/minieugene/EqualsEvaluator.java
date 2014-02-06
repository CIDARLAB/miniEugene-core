package org.cidarlab.minieugene;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.constants.EugeneConstants;

public class EqualsEvaluator {

	private static final int MAX_DESIGN_SIZE = 3; 
	
	public void evaluate() {
		for(int i=2; i<=MAX_DESIGN_SIZE; i++) {
			System.out.println("*** i: "+i);
			String s=buildScript(i);
			
//			System.out.println(s);

			try {
				MiniEugene me = new MiniEugene();				
				me.solve(s.split(System.getProperty("line.separator")),  i, -1);
				System.out.println(
						me.getStatistics().getValueByKey(EugeneConstants.NUMBER_OF_SOLUTIONS));
//				mer.printSolutions();
//				System.out.println(mer.getSBOL().getPath());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String buildScript(int N) {
		String NEWLINE = System.getProperty("line.separator");
		
		StringBuilder sb = new StringBuilder();
		sb.append("CONTAINS a").append(NEWLINE);
		sb.append("CONTAINS b").append(NEWLINE);
//		for(int i=0; i<N-1; i++) {
			sb.append("[").append("0")
				.append("] NOTEQUALS [")
				.append("1").append("]")
				.append(NEWLINE);
//		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		EqualsEvaluator te = new EqualsEvaluator();
		te.evaluate();
	}
}
