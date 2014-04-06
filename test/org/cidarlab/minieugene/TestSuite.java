package org.cidarlab.minieugene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;

import org.cidarlab.minieugene.data.pigeon.WeyekinPoster;
import org.cidarlab.minieugene.util.FileUtil;
import org.cidarlab.minieugene.util.SolutionExporter;


public class TestSuite {

	public static void main(String[] args) 
			throws Exception {

		/*
		 * BASIC ``Syntax'' TESTS
		 */
//		new TestSuite().test(new File("./tests/comments"));

		/*
		 * naming of rule operands
		 */
//		new TestSuite().test(new File("./tests/operand-naming.eug"));
		//new TestSuite().test(new File("./tests/bryan/ex1"));
		//new TestSuite().test(new File("./tests/bryan/ex2"));
		//new TestSuite().test(new File("./tests/bryan/ex3"));
//		new TestSuite().test(new File("./tests/bryan/ex4"));

		
		/*
		 * COUNTING RULES
		 */
		
		// SAME_COUNT
//		new TestSuite().test(new File("./tests/counting/same_count01"));
//		new TestSuite().test(new File("./tests/counting/same_count02"));
//		new TestSuite().test(new File("./tests/counting/same_count03"));

		/*
		 * CALCULATE minN
		 */
//		new TestSuite().test(new File("./tests/counting/minN/minN01"));

		/*
		 * BEFORE
		 */
//		new TestSuite().test(new File("./tests/before/before01"));
//		
		/*
		 * THEN
		 */
//		new TestSuite().test(new File("./tests/then/then01"));
//		new TestSuite().test(new File("./tests/then/then02"));
//		new TestSuite().test(new File("./tests/then/then03"));

//		new TestSuite().test(new File("./tests/swati/test01"));


		/*
		 * POSITIONING
		 */
		// ALL_BEFORE
//		new TestSuite().test(new File("./tests/before/all_before01"));
		
		// SOME_BEFORE
//		new TestSuite().test(new File("./tests/before/some_before01"));
//		new TestSuite().test(new File("./tests/before/some_before02"));
//		new TestSuite().test(new File("./tests/before/some_before03"));

		/*
		 * PAIRING RULES
		 */
//		new TestSuite().test(new File("./tests/pairing/always_nextto01"));

		/*
		 * ORIENTATION RULES
		 */		
//		new TestSuite().test("N=2.contains a.all_forward \\/ all_reverse.");
		
		// SOME_FORWARD
//		new TestSuite().test(new File("./tests/orientation/forward/some01"));
//		new TestSuite().test(new File("./tests/orientation/forward/some02"));
//		new TestSuite().test(new File("./tests/orientation/forward/some03"));
		
		// SOME_REVERSE
//		new TestSuite().test(new File("./tests/orientation/reverse/some01"));
//		new TestSuite().test(new File("./tests/orientation/reverse/some02"));
//		new TestSuite().test(new File("./tests/orientation/reverse/some03"));

		// ALTERNATE 
//		new TestSuite().test(new File("./tests/orientation/alternate/alternate01"));
//		new TestSuite().test(new File("./tests/orientation/alternate/alternate02"));
		
		// SAME_ORIENTATION
//		new TestSuite().test(new File("./tests/orientation/same_orientation/same01"));
//		new TestSuite().test(new File("./tests/orientation/same_orientation/same02"));
//		new TestSuite().test(new File("./tests/orientation/same_orientation/same03"));
//		new TestSuite().test(new File("./tests/orientation/same_orientation/same04"));
//		new TestSuite().test(new File("./tests/orientation/same_orientation/same05"));
		//TODO
//		new TestSuite().test(new File("./tests/orientation/same_orientation/some_same01"));
//		new TestSuite().test(new File("./tests/orientation/same_orientation/some_same02"));

		/*
		 * INTERACTION RULES
		 */
//		new TestSuite().test(new File("./tests/interaction/drives01"));
//		new TestSuite().test(new File("./tests/interaction/drives02"));
//		new TestSuite().test(new File("./tests/interactions.eug"));
		
		new TestSuite().test("N=3.contains p. contains c. contains t.p drives c.");
//		new TestSuite().test("N=4.contains p. contains c. contains t.p drives c.");
//		new TestSuite().test("N=5.contains p. contains c. contains t.p drives c.");
//		new TestSuite().test("N=6.contains p. contains c. contains t.p drives c.");

		/*
		 * LOGICAL OR
		 */
//		new TestSuite().test(new File("./tests/or/or01"));
		
		/*
		 * minN calculations
		 */
//		new TestSuite().test(new File("./tests/N/minN01"));
//		new TestSuite().test(new File("./tests/N/minN02"));
//		new TestSuite().test(new File("./tests/N/minN03"));   // DOES NOT WORK YET!
		
		/*
		 * miniEugene's TEMPLATING features
		 */
		
		// TEMPLATES
//		new TestSuite().test("N=1.all_forward.template a.");
//		new TestSuite().test("N=100.all_forward.template a.");
//		new TestSuite().test("N=4.all_forward.template Promoter, RBS, CDS, Terminator.");
//		new TestSuite().test("N=400.all_forward.template Promoter, RBS, CDS, Terminator.");
//		new TestSuite().test("N=4.all_forward.template a, b.");
//		new TestSuite().test("N=20.alternate_orientation.template a, b.");
//		new TestSuite().test("N=20.template a, b.");
//		new TestSuite().test("N=200.all_forward \\/ all_reverse.template a, b.");
		
		// negated templates
//		new TestSuite().test("N=4.not template promoter, rbs, cds, terminator.");

		
		// SEQUENCES
//		new TestSuite().test("N=3.all_forward.sequence a, b.");
//		new TestSuite().test("N=30.p exactly 1.p same_count c.p same_orientation c.not forward p \\/ sequence p, c.not reverse p \\/ sequence c, p.contains t. p same_orientation t.");
		
		/*
		 * ACT
		 */
//		new TestSuite().test(new File("./tests/act/cyclic"));
//		new TestSuite().test(new File("./tests/act/cyclic02"));
//		new TestSuite().test(new File("./tests/act/cyclic03"));
//		new TestSuite().test(new File("./tests/act/cyclic04"));
//		new TestSuite().test(new File("./tests/act/cyclic05"));
//		new TestSuite().test(new File("./tests/act/cyclic06"));
//
//		new TestSuite().test(new File("./tests/act/acyclic"));
//		new TestSuite().test(new File("./tests/act/acyclic02"));
//		new TestSuite().test(new File("./tests/act/acyclic03"));
//		new TestSuite().test(new File("./tests/act/acyclic04"));

//		new TestSuite().test(new File("./tests/act/fsm"));
//		new TestSuite().test(new File("./tests/act/fsm02"));

//		new TestSuite().test(new File("./tests/swati/inverter"));

		/*
		 * LATTICE 
		 */
//		new TestSuite().test(new File("./tests/lattice/daniel01"));
//		new TestSuite().test(new File("./tests/lattice/daniel02"));
//		new TestSuite().test(new File("./tests/lattice/daniel03"));

//		new TestSuite().testAll("./tests");
		
		
		
		/*
		 * REAL DESIGNS
		 */
		
		// NOR GATES
//		new TestSuite().test(new File("./designs/nor-orientations"));
//		new TestSuite().test(new File("./designs/nor-events"));
		
		
		// INVERTERS
//		new TestSuite().test(new File("./tests/swati/test01"));
//		new TestSuite().test(new File("./tests/swati/inverter"));

		
		// Inverter example of the web site
//		new TestSuite().test(new File("./designs/web-site/inverter"));
//		new TestSuite().test(new File("./designs/web-site/toggle-switch"));
//		new TestSuite().test(new File("./designs/web-site/nor"));
		
		
//		new TestSuite().test(new File("./designs/nor"));

		// BROAD CISTRONS
//		new TestSuite().test(new File("./designs/broad/pathway"));

		/*
		 * ACM JETC
		 */
//		new TestSuite().test(new File("./designs/priority-encoder/rev3/cassette01.eug"));
//		new TestSuite().test(new File("./designs/priority-encoder/rev3/cassette02.eug"));
//		new TestSuite().test(new File("./designs/priority-encoder/rev3/cassette03.eug"));
//		new TestSuite().test(new File("./designs/priority-encoder/rev3/basic_composition.eug"));

		/*
		 * NOR GATE EXAMPLE
		 */
//		new TestSuite().test(new File("./designs/nor-gate/repressing-cassette.eug"));
//		new TestSuite().test(new File("./designs/nor-gate/reporting-cassette.eug"));
//		new TestSuite().test(new File("./designs/nor-gate/nor-gate.eug"));

//		new TestSuite().test(new File("./designs/nor-gate/rev1/repressing-cassette.eug"));
//		new TestSuite().test(new File("./designs/nor-gate/rev1/reporting-cassette.eug"));
//		new TestSuite().test(new File("./designs/nor-gate/rev1/nor-gate.eug"));

//		new TestSuite().test(new File("./designs/nor-gate/nor-gate.eug"));
//		new TestSuite().test(new File("./examples/transcriptional-unit.eug"));
	}

	public void test(String script) {
		MiniEugene me = new MiniEugene();				
		long t1 = -1;
		long tProcessing = -1;
		
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
		}

//		MiniEugeneReturn mer = new MiniEugene(-1, -1, false).execute(script);
			
		me.getStatistics().print();

		SolutionExporter se = new SolutionExporter(me.getSolutions(), me.getInteractions());
		try {
			// ACT -> GraphViz
//			URI act = me.visualizeACT();
				
			// PIGEON
			URI pig = se.toPigeon();
			WeyekinPoster.launchPage(pig);
			
		} catch(Exception e) {
			e.printStackTrace();
		}

//			String filename = java.util.UUID.randomUUID().toString();
		
		// SBOL
//			se.toSBOL("./test-results/"+filename+".sbol.xml");
		
		// EUGENE
//			se.toEugene("./test-results/"+filename+".eug");
		
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
