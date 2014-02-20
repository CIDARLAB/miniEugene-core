package org.cidarlab.minieugene;

import java.util.Arrays;

import org.cidarlab.minieugene.dom.Component;

public class NotMoreThanTester {

	private static final String NEWLINE = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		String script = "N=3"+NEWLINE+"CONTAINS p"+NEWLINE+"CONTAINS c"+NEWLINE+"p NOTMORETHAN 1";
		try {
			MiniEugene me = new MiniEugene();
			me.executeScript(script, -1, -1);
			
			if(null != me.getSolutions()) {
				for(Component[] solution : me.getSolutions()) {
					System.out.println(Arrays.toString(solution));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
