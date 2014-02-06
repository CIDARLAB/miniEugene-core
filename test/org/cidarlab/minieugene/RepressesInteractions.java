package org.cidarlab.minieugene;

public class RepressesInteractions {

	private static final String NEWLINE = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		String script = "N=2"+NEWLINE+"CONTAINS p"+NEWLINE+"CONTAINS c"+NEWLINE+"c REPRESSES p";
		try {
			MiniEugene me = new MiniEugene();
			me.executeScript(script, -1, -1);
			System.out.println(me.getInteractions());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
