package org.cidarlab.minieugene;

import java.io.File;
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
//		new TestSuite().test(new File("./tests/bryan/ex1"));
//		new TestSuite().test(new File("./tests/bryan/ex2"));
//		new TestSuite().test(new File("./tests/bryan/ex3"));
//		new TestSuite().test(new File("./tests/bryan/ex4"));

		
		/*
		 * COUNTING RULES
		 */
		// CONTAINS
//		new TestSuite().test("N=4.CONTAINS p.CONTAINS c.");
		
		// SAME_COUNT
//		new TestSuite().test(new File("./tests/counting/same_count01"));
//		new TestSuite().test(new File("./tests/counting/same_count02"));
//		new TestSuite().test(new File("./tests/counting/same_count03"));

		// EXACTLY
//		new TestSuite().test("N=1. r1 exactly 1.");
		
//		new TestSuite().test("N=6.ALL_FORWARD.rc_ EXACTLY 1.rc_ AFTER rc.p DRIVES rc.p DRIVES rc_.p DRIVES r.t SOME_AFTER rc_.");
//		new TestSuite().test("N=10.CONTAINS p.contains c.contains t.p drives c.p same_orientation t.[0] equals p.[1] equals c.");
//		new TestSuite().test("N=8.CONTAINS c.STARTSWITH p OR STARTSWITH t.ENDSWITH p OR ENDSWITH t.c NEXTTO r. r BEFORE c.p SOME_BEFORE r.r SOME_BEFORE p.t SOME_AFTER c. t SOME_BEFORE p.");
//		new TestSuite().test("N=16."+
//				"P1 EXACTLY 1.r1 EXACTLY 1.c1 EXACTLY 1.T1 EXACTLY 1.ALL_FORWARD.STARTSWITH P1."+
//				"r1 NEXTTO c1.c1 NEXTTO T1.P1 NEXTTO r1.P2 EXACTLY 1.r2 EXACTLY 1.c2 EXACTLY 1.T2 EXACTLY 1.ALL_FORWARD."+
//				"P1 BEFORE P2.r2 NEXTTO c2.r2 BEFORE c2.c2 NEXTTO T2.P2 NEXTTO r2.P2 EXACTLY 1.r3 EXACTLY 1.c3 EXACTLY 1."+
//				"T3 EXACTLY 1.ALL_FORWARD.P2 BEFORE P3.r3 NEXTTO c3.r3 BEFORE c3.c3 NEXTTO T3.P3 NEXTTO r3.P4 EXACTLY 1.r4 EXACTLY 1."+
//				"c4 EXACTLY 1.T4 EXACTLY 1.ALL_FORWARD.P3 BEFORE P4.r4 NEXTTO c4.r4 BEFORE c4.c4 NEXTTO T4.P4 NEXTTO r4."+
//				"cTetR REPRESSES PTetR.cLacI REPRESSES PLacI.");
		/*
		 * CALCULATE minN
		 */
//		new TestSuite().test(new File("./tests/counting/minN/minN01"));

		/*
		 * BEFORE
		 */
//		new TestSuite().test("N=2.a before b.");
//		
		/*
		 * THEN
		 */
//		new TestSuite().test(new File("./tests/then/then01"));
//		new TestSuite().test(new File("./tests/then/then02"));
//		new TestSuite().test(new File("./tests/then/then03"));
//
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
		
		// EQUALS
		
		// solely indices
//		new TestSuite().test("N=2.contains p.[0] equals [1].");
//		new TestSuite().test("N=2.contains p.[1] equals [0].");
//		new TestSuite().test("N=3.contains p.contains c.[1] equals [0].");
//		new TestSuite().test("N=3.contains p.contains c.[1] equals [2].");
//		new TestSuite().test("N=3.contains p.contains c.[0] equals [1] or [1] equals [2].");

		// invalid indices
//		new TestSuite().test("N=3.contains p.contains c.[3] equals [-1].");

		// indices + components
//		new TestSuite().test("N=2.[0] equals p or [0] equals c.[1] equals c or [1] equals p.");
		
		// NOTEQUALS

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
		
//		new TestSuite().test("N=3.contains p. contains c. contains t.p drives c.");
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
		 * miniEugene's TEMPLATING CONSTRAINTS
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
//		new TestSuite().test("N=4.all_forward.not template promoter, rbs, cds, terminator.");

		// templates with selections
//		new TestSuite().test("N=4.all_forward.template [p1], [r1], [c1], [t1].");
//		new TestSuite().test("N=4.all_forward.template [p1|p2], [r1|r2], [c1|c2], [t1|t2].");
//		new TestSuite().test("N=4.all_forward.template [p1|p2|p3], [r1|r2|r3], [c1|c2|c3], [t1|t2|t3].");
//		new TestSuite().test("N=8.all_forward.template [p1], [r1], [c1], [t1].");
//		new TestSuite().test("N=8.all_forward.template [p1|p2], [r1|r2], [c1|c2], [t1|t2].");
//		new TestSuite().test("N=8.all_forward.template [p1|p2|p3], [r1|r2|r3], [c1|c2|c3], [t1|t2|t3].");
//		new TestSuite().test("N=12.all_forward.template [p1], [r1], [c1], [t1].");
//		new TestSuite().test("N=12.all_forward.template [p1|p2], [r1|r2], [c1|c2], [t1|t2].");
//		new TestSuite().test("N=12.all_forward.template [p1|p2|p3], [r1|r2|r3], [c1|c2|c3], [t1|t2|t3].");
		
		
		// DISJUNCTION OF TEMPLATES
//		new TestSuite().test("N=4. p1 same_orientation r1. p1 same_orientation c1. p1 same_orientation t1. not forward p1 \\/ template [p1], [r1], [c1], [t1]. not reverse p1 \\/ template [t1], [c1], [r1], [p1].");
//		new TestSuite().test("N=8. p1 same_orientation r1. p1 same_orientation c1. p1 same_orientation t1. not forward p1 \\/ template [p1], [r1], [c1], [t1]. not reverse p1 \\/ template [t1], [c1], [r1], [p1].");		
//		new TestSuite().test("N=12. p1 same_orientation r1. p1 same_orientation c1. p1 same_orientation t1. not forward p1 \\/ template [p1], [r1], [c1], [t1]. not reverse p1 \\/ template [t1], [c1], [r1], [p1].");
		
		// SEQUENCES
//		new TestSuite().test("N=3.contains X. all_forward. sequence p, c.");
//		new TestSuite().test("N=3.contains X. all_forward. sequence [p1|p2], [c1|c2].");
//		new TestSuite().test("N=50.X exactly 48. all_forward. sequence [p1|p2], [c1|c2].");
//		new TestSuite().test("N=4. sequence [p1|p2|p3|c1|c2|c3]. p1 with c1 \\/ p2 with c2 \\/ p3 with c3. p1 same_orientation c1. p2 same_orientation c2. p3 same_orientation c3. not forward p1 \\/ p1 before c1. not forward p2 \\/ p2 before c2. not forward p3 \\/ p3 before c3. not reverse p1 \\/ p1 after c1. not reverse p2 \\/ p2 after c2. not reverse p3 \\/ p3 after c3.");
		
//		new TestSuite().test("N=5.all_forward. sequence [p1|p2], [c1|c2]. p1 then c1. p2 then c2.");
//		new TestSuite().test("N=30.p exactly 1.p same_count c.p same_orientation c.not forward p \\/ sequence p, c.not reverse p \\/ sequence c, p.contains t. p same_orientation t.");
		
//		new TestSuite().test("N=40. all_forward. template [p1|p2],[r1|r2],[c1|c2],[t1|t2].");
		
		// INVALID SEQUENCS/TEMPLATES
		
		// Extensional Support
//		new TestSuite().test("N=1. all_forward. template p.");
//		new TestSuite().test("N=1. all_forward. template [p1|p2].");
//		new TestSuite().test("N=1. all_forward. template [p1|p2|p3].");
//		new TestSuite().test("N=1. all_forward. template [p1|p2|p3|p4].");
//		new TestSuite().test("N=2. all_forward. template p.");
//		new TestSuite().test("N=2. all_forward. template [p1|p2].");
//		new TestSuite().test("N=2. all_forward. template [p1|p2|p3].");
//		new TestSuite().test("N=2. all_forward. template [p1|p2|p3|p4].");
//		new TestSuite().test("N=3. all_forward. template p.");
//		new TestSuite().test("N=3. all_forward. template [p1|p2].");
//		new TestSuite().test("N=3. all_forward. template [p1|p2|p3].");
//		new TestSuite().test("N=3. all_forward. template [p1|p2|p3|p4].");
//
//		new TestSuite().test("N=2. all_forward. template [p1|p2], [c1|c2].");
//		new TestSuite().test("N=4. all_forward. template [p1|p2], [r1|r2].");
//		new TestSuite().test("N=6. all_forward. template [p1|p2], [c1|c2].");
//		new TestSuite().test("N=8. all_forward. template [p1|p2], [r1|r2].");
//
//		new TestSuite().test("N=3. template [p1|p2], [p2|p3], [p4|p5].");
//		new TestSuite().test("N=3. all_forward. template [p1|p2|p3], [r1|r2|r3], [p1|p2|p3].");
		
		
//		new TestSuite().test("N=12. template p, r, c, t.");
//		new TestSuite().test("N=12. all_forward. template [p1|p2|p3], [r1|r2|r3], [c1|c2|c3], [t1|t2|t3].");

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
//		new TestSuite().test(new File("./designs/templating/inverter"));

		/*
		 * LATTICE 
		 */
//		new TestSuite().test(new File("./tests/lattice/daniel01"));
//		new TestSuite().test(new File("./tests/lattice/daniel02"));
//		new TestSuite().test(new File("./tests/lattice/daniel03"));

//		new TestSuite().testAll("./tests");
		
//		new TestSuite().test("N=4.sequence p,[[r,c],t]+.");
//		new TestSuite().test("N=4.sequence p,[[r,c],t]*.");
//		new TestSuite().test("N=4. sequence p, c+, t.");
//		new TestSuite().test("N=1. sequence p, c, t.");

		
		/*
		 * REAL DESIGNS
		 */
		
		// NOR GATES
		new TestSuite().test(new File("./designs/nor-orientations"));
		new TestSuite().test(new File("./designs/nor-events"));
		
		
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
//		new TestSuite().test(new File("./designs/nor-orientations"));

		/*
		 * Swati's Inverters
		 */
//		new TestSuite().test(new File("./designs/templating/inverter"));
//		new TestSuite().test(new File("./designs/templating/inverter_rev1"));

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
