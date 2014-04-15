package org.cidarlab.minieugene.jetc;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.data.pigeon.WeyekinPoster;
import org.cidarlab.minieugene.util.FileUtil;
import org.cidarlab.minieugene.util.SolutionExporter;

public class TemplateDriven {

	public static void main(String[] args) 
			throws Exception {

		// ITERATION 1:
		new TemplateDriven().test("N=3.sequence r1, c1, t1. all_forward.");
		new TemplateDriven().test("N=3.sequence r2, c2, t2. all_forward.");
		new TemplateDriven().test("N=3.sequence r3, c3, t3. all_forward.");
		new TemplateDriven().test("N=3.sequence r4, c4, t4. all_forward.");
		new TemplateDriven().test("N=3.sequence r5, c5, t5. all_forward.");
		new TemplateDriven().test("N=3.sequence rOut1, cOut1, tOut1. all_forward.");
		new TemplateDriven().test("N=3.sequence rOut0, cOut1, tOut1. all_forward.");
		
		// ITERATION 2:
		// out1 Signal
		new TemplateDriven().test("N=14."+
				"all_forward." +
				
				// dark blue NOR gate
				"sequence pIn2, pIn1, r1, c1, t1."+
				
				// light blue NOR gate
				"sequence p1, p0, r2, c2, t2. "+

				// out1 reporting device
				"sequence pOut1, rOut1, cOut1, tOut1."+

				// input signals of dark blue NOR gate
				"in2 induces pIn2. in1 induces pIn1."+

				// input signals of light blue NOR gate
				"c1 represses p1. 0 induces p0. c2 represses pOut1.");

		// out0 Signal
		new TemplateDriven().test("N=19."+
				"all_forward." +
				
				// orange NOR gate
				"sequence p1, pIn2, r3, c3, t3."+
				
				// light green NOR gate
				"sequence pIn2, pIn0, r4, c4, t4. "+

				"sequence p3, p4, r5, c5, t5."+
				
				// out1 reporting device
				"sequence p5, rOut0, cOut1, tOut1."+
				
				// induces 
				"in2 induces pIn2. in0 induces pIn0."+
				
				// represses
				"c3 represses p3. c4 represses p4. c5 represses p5.");
		
		// ITERATION 2:
		new TemplateDriven().test("N=33."+
		
				/*
				 * reusing out1 signal 
				 */
				"all_forward." +
				
				// dark blue NOR gate
				"sequence pIn2, pIn1, r1, c1, t1."+
				
				// light blue NOR gate
				"sequence p1, p0, r2, c2, t2. "+

				// out1 reporting device
				"sequence pOut1, rOut1, cOut1, tOut1."+

				// input signals of dark blue NOR gate
				"in2 induces pIn2. in1 induces pIn1."+

				// input signals of light blue NOR gate
				"c1 represses p1. 0 induces p0. c2 represses pOut1."+
				
				/*
				 * reusing out0 signal
				 */
				// orange NOR gate
				"sequence p1, pIn2, r3, c3, t3."+
					
				// light green NOR gate
				"sequence pIn2, pIn0, r4, c4, t4. "+

				"sequence p3, p4, r5, c5, t5."+
					
				// out1 reporting device
				"sequence p5, rOut0, cOut1, tOut1."+
					
				// induces 
				"in2 induces pIn2. in0 induces pIn0."+
					
				// represses
				"c3 represses p3. c4 represses p4. c5 represses p5.");
	
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
			me.solve(script, 1);

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
			// ACT -> GraphViz
//			URI act = me.visualizeACT();
				
			// PIGEON
			URI pig = se.toPigeon();
			WeyekinPoster.launchPage(pig);
			
			// EUGENE
//			se.toEugene("./designs/templating/xxx_rev1.eug");
		} catch(Exception e) {
			e.printStackTrace();
		}

//			String filename = java.util.UUID.randomUUID().toString();
		
		// SBOL
//			se.toSBOL("./test-results/"+filename+".sbol.xml");
		
		
		// CONSOLE OUTPUT
//		se.toConsole();

		System.out.println("[TestSuite.test] full processing time: "+tProcessing*Math.pow(10, -9)+"sec");
		
		//new Eugene(sFile);
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
