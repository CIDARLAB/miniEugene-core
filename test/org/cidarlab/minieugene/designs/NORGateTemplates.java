package org.cidarlab.minieugene.designs;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.util.FileUtil;
import org.cidarlab.minieugene.util.SolutionExporter;

public class NORGateTemplates {

	private Map<String, Integer> colors;

	public NORGateTemplates() {
		this.colors = new HashMap<String, Integer>();

		this.colors.put("rbs", 14);
		this.colors.put("term",14);

		// dark blue NOR gate
		this.colors.put("p1", 2);
		this.colors.put("c1", 2);

		this.colors.put("p2", 1);		
		this.colors.put("c2", 1);

		this.colors.put("p3", 8);		
		this.colors.put("c3", 8);

		this.colors.put("p4", 3);		
		this.colors.put("c4", 3);

		// INDUCIBLE PROMOTERS
		this.colors.put("pIn1", 14);
		this.colors.put("pIn2", 14);
		this.colors.put("pIn3", 14);
		this.colors.put("pIn4", 14);
		
		// REPORTERS
		this.colors.put("cGFP",  4);
		this.colors.put("cRFP",  6);
		this.colors.put("cRFP", 12);
	}
	
	
	public static void main(String[] args) 
			throws Exception {
		
		new NORGateTemplates().test(new File("./designs/nor-gate/templates/nor-templates"));
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
					"./designs/nor-gate/templates/nor-gates.png", 
					this.colors, 
					true, 
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
