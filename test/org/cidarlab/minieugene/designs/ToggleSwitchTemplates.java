package org.cidarlab.minieugene.designs;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.util.FileUtil;
import org.cidarlab.minieugene.util.SolutionExporter;

public class ToggleSwitchTemplates {

	private Map<String, Integer> colors;

	public ToggleSwitchTemplates() {
		this.colors = new HashMap<String, Integer>();

		this.colors.put("T1T2_1", 14);
		this.colors.put("T1T2_2", 14);

		this.colors.put("cLacI", 2);
		this.colors.put("rRBS1", 2);
		this.colors.put("Ptrc2", 2);

		this.colors.put("P1", 6);	
		this.colors.put("rbsE", 6);
		this.colors.put("cR1", 6);

		this.colors.put("rbsB", 4);		
		this.colors.put("cGFPmut3", 4);
	}
	
	
	public static void main(String[] args) 
			throws Exception {		
		new ToggleSwitchTemplates().test("N=10."+
				"template T1T2_1, cLacI, rRBS1, P1, Ptrc2, rbsE, cR1, rbsB, cGFPmut3, T1T2_2."+
				"reverse T1T2_1. reverse cLacI. reverse rRBS1. reverse P1."+
				"forward Ptrc2. forward rbsE. forward cR1. forward rbsB. forward cGFPmut3. forward T1T2_2."+
				"cR1 represses P1. cLacI represses Ptrc2.");
	}

	public void test(String script) {
		MiniEugene me = new MiniEugene();				
		long t1 = -1;
		long tProcessing = -1;
		
		System.out.println("**** "+script+" ****");
		
		try {
			/*
			 * read the file
			 */
			t1 = System.nanoTime();
			
			/*
			 * execute the script
			 */
			me.solve(script);

			tProcessing = System.nanoTime() - t1;
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}

		me.getStatistics().print();

		if(me.getSolutions() == null || me.getSolutions().isEmpty() ) {
			return;
		}
		
		SolutionExporter se = new SolutionExporter(me.getSolutions(), me.getInteractions());
		try {
			se.pigeonize(
					"./designs/toggle-switch.png", 
					this.colors, 
					false, 
					10);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("[TestSuite.test] full processing time: "+tProcessing*Math.pow(10, -9)+"sec");
	}
	
	
	public void test(File f) 
			throws IOException {
		String script = FileUtil.readFile(f);
		this.test(script);
	}
	
	public void testAll( String path ) {
		File root = new File( path );
		FilenameFilter filter = new FilenameFilter() {
                @Override 
	        public boolean accept(File directory, String fileName) {
	            return fileName.endsWith(".eug");
	        }
	    };
	    File[] list = root.listFiles(filter);

		for ( File f : list ) {
			if ( f.isDirectory() ) {
				testAll( f.getAbsolutePath() );
			} else {
				System.out.println("**** "+f.getAbsoluteFile()+" ****");
				try {
					this.test(f);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	
}
