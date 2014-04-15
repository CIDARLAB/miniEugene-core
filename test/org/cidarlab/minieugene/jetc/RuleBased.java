package org.cidarlab.minieugene.jetc;

import java.util.HashMap;
import java.util.Map;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.util.SolutionExporter;

public class RuleBased {

	private Map<String, Integer> colors;

	public RuleBased() {
		// c1    ... repressor of the dark blue NOR gate
		// c2    ...                  light blue
		// c3    ...                  orange
		// c4    ...                  light green
		// c5    ...                  dark green
		// cGFP ... reporter for the out0 signal 
		// cRFP ... reporter for the out1 signal

		this.colors = new HashMap<String, Integer>();

		// dark blue NOR gate
		this.colors.put("p1_2", 2);
		this.colors.put("p1_3", 2);
		this.colors.put("r1", 2);
		this.colors.put("c1", 2);
		this.colors.put("t1", 2);

		// light blue NOR gate
		this.colors.put("p2", 1);		
		this.colors.put("r2", 1);
		this.colors.put("c2", 1);
		this.colors.put("t2", 1);

		// orange NOR gate
		this.colors.put("p3", 8);		
		this.colors.put("r3", 8);
		this.colors.put("c3", 8);
		this.colors.put("t3", 8);

		// light green NOR gate
		this.colors.put("p4", 3);		
		this.colors.put("r4", 3);
		this.colors.put("c4", 3);
		this.colors.put("t4", 3);

		// dark green NOR gate
		this.colors.put("p5", 4);		
		this.colors.put("r5", 4);
		this.colors.put("c5", 4);
		this.colors.put("t5", 4);

		// INDUCIBLE PROMOTERS
		this.colors.put("pIn0", 14);
		this.colors.put("pIn1", 14);
		this.colors.put("pIn2", 14);
		this.colors.put("pIn2_1", 14);
		this.colors.put("pIn2_3", 14);
		this.colors.put("pIn2_4", 14);
		this.colors.put("p0", 14);

		
		// OUT cassettes
		this.colors.put("rOut0", 10);
		this.colors.put("cGFP",  10);
		this.colors.put("tOut0", 10);
		this.colors.put("rOut1", 6);
		this.colors.put("cRFP", 6);
		this.colors.put("tOut1", 6);

	}

	public static void main(String[] args) 
			throws Exception {


		RuleBased rb = new RuleBased();
		
		// ITERATION 1:
		// RBS-CDS-TERMINATOR tuples
//		rb.test("iteration1-nor1", "N=3.r1 exactly 1.c1 exactly 1.t1 exactly 1.r1 before c1.r1 nextto c1.c1 before t1.c1 nextto t1.all_forward.");
//		rb.test("iteration1-nor2", "N=3.r2 exactly 1.c2 exactly 1.t2 exactly 1.r2 before c2.r2 nextto c2.c2 before t2.c2 nextto t2.all_forward.");
//		rb.test("iteration1-nor3", "N=3.r3 exactly 1.c3 exactly 1.t3 exactly 1.r3 before c3.r3 nextto c3.c3 before t3.c3 nextto t3.all_forward.");
//		rb.test("iteration1-nor4", "N=3.r4 exactly 1.c4 exactly 1.t4 exactly 1.r4 before c4.r4 nextto c4.c4 before t4.c4 nextto t4.all_forward.");
//		rb.test("iteration1-nor5", "N=3.r5 exactly 1.c5 exactly 1.t5 exactly 1.r5 before c5.r5 nextto c5.c5 before t5.c5 nextto t5.all_forward.");
//		rb.test("iteration1-out1", "N=3.rOut1 exactly 1.cRFP exactly 1.tOut1 exactly 1.rOut1 before cRFP.rOut1 nextto cRFP.cRFP before tOut1.cRFP nextto tOut1.all_forward.");
//		rb.test("iteration1-out0", "N=3.rOut0 exactly 1.cGFP exactly 1.tOut0 exactly 1.rOut0 before cGFP.rOut0 nextto cGFP.cGFP before tOut0.cGFP nextto tOut0.all_forward.");
		
//		// ITERATION 2:
//		// OUT1 DEVICE := in2 + in1 drive c1, p1 + p0 drive cRFP, c1 represses p1 
//		rb.test("iteration2-out1",
//				"N=14."+
//		
//				// dark blue NOR gate's components
//				"r1 exactly 1. c1 exactly 1. t1 exactly 1. "+
//				// ordering of its components
//				"r1 before c1. r1 nextto c1. c1 before t1. c1 nextto t1. all_forward."+
//				// input signals of dark blue NOR gate
//				// in2 -> pIn2 -> c1
//				"pIn2 exactly 1. in2 induces pIn2. pIn2 drives c1. "+
//				// in1 -> pIn1 -> c1
//				"pIn1 exactly 1. in1 induces pIn1. pIn1 drives c1. "+
//				// order of the promoters
//				"pIn2 before pIn1. "+
//				
//				// light blue NOR gate's components
//				"r2 exactly 1.c2 exactly 1.t2 exactly 1."+
//				// - its components' positions
//				"r2 before c2. r2 nextto c2. c2 before t2. c2 nextto t2."+
//				// - its components' orientation
//				"all_forward."+
//				// input signals of light blue NOR gate
//				// c1 -> p1 -> c2 
//				"p1 exactly 1. c1 represses p1. p1 drives c2. "+
//				// 0 -> p0 -> c2
//				"p0 exactly 1. 0 induces p0. p0 drives c2."+
//				// order of the promoters of the light blue NOR gate
//				"p1 before p0."+
//
//				// the out1 reporting cassette's components
//				"rOut1 exactly 1. cRFP exactly 1. tOut1 exactly 1. "+
//				// positioning of its components
//				"rOut1 before cRFP. rOut1 nextto cRFP. cRFP before tOut1. cRFP nextto tOut1. all_forward."+				
//				// input signals of the out1 cassette
//				// c2 -> p2 -> cRFP 
//				"p2 exactly 1. c2 represses p2. p2 drives cRFP.");
//
//		System.gc();
//		
//		// OUT0 DEVICE := in2 drives c3, in2 + in0 drive c4, c3 represses p4. 
//		rb.test("iteration2-out0",
//				
//				// length of the out0 device
//				"N=19."+
//						
//				// the orange NOR gate
//				// - its components
//				"r3 exactly 1. c3 exactly 1. t3 exactly 1."+
//				// - the components' positioning
//				"r3 before c3. r3 nextto c3. c3 before t3. c3 nextto t3."+
//				// - the components' orientation
//				"all_forward."+
//				// - its input signals
//				//   - in2 -> pIn2 -> c3   
//				//   HACK: we added a new pIn2 promoter (named pIn2_3 since it drives c3)
//				"in2 induces pIn2_3. pIn2_3 exactly 1. pIn2_3 drives c3. " + 
//				//   - p1 -> c3
//				"p1 exactly 1. p1 drives c3. "+
//				// - positioning of the input promoters
//				"p1 before pIn2_3." +
//				
//				// the light green NOR gate
//				// - its components
//				"r4 exactly 1. c4 exactly 1. t4 exactly 1."+
//				// - its components' positioning
//				"r4 before c4. r4 nextto c4. c4 before t4. c4 nextto t4."+
//				// - its components' orientation
//				"all_forward."+
//				// - its input signals
//				//   - in2 -> pIn2 -> c4
//				//   HACK: we added a new pIn2 promoter (named pIn2_4 since it drives c4)
//				"in2 induces pIn2_4. pIn2_4 exactly 1. pIn2_4 drives c4."+
//				//   - in0 -> pIn0 -> c4
//				"in0 induces pIn0. pIn0 exactly 1. pIn0 drives c4."+
//				// - positioning of the input promoters
//				"pIn2_4 before pIn0."+
//				
//				// the dark green NOR gate
//				// - its components
//				"r5 exactly 1. c5 exactly 1. t5 exactly 1."+
//				// - its components' positioning
//				"r5 before c5. r5 nextto c5. c5 before t5. c5 nextto t5."+
//				// - its components' orientations
//				"all_forward."+
//				// - its input signals
//				//   - c3 -> p3 -> c5
//				"c3 represses p3. p3 exactly 1. p3 drives c5. "+
//				//   - c4 -> p4 -> c5
//				"c4 represses p4. p4 exactly 1. p4 drives c5. "+
//				// - positioning of its input promoters
//				"p3 before p4."+
//				
//				// out0 reporting cassette
//				// - its components
//				"rOut0 exactly 1. cGFP exactly 1. tOut0 exactly 1. "+
//				// - its components' positioning
//				"rOut0 before cGFP. rOut0 nextto cGFP. cGFP before tOut0. cGFP nextto tOut0."+
//				// - its components' orientations
//				"all_forward."+
//				// - its input signals
//				// c5 -> p5 -> cGFP
//				"c5 represses p5. p5 exactly 1. p5 drives cGFP.");

		
		// we had to:
		// - duplicate promoters 
		//   pIn2 -> pIn2_1, pIn2_3, pIn3_4
		//   p1 -> p1_2 and p1_3
		
		// PRIORITY ENCODER := OUT1 + OUT0
		rb.test("iteration3-penc",
				"N=33."+
						
				// dark blue NOR gate's components
				"r1 exactly 1. c1 exactly 1. t1 exactly 1. "+
				// ordering of its components
				"r1 before c1. r1 nextto c1. c1 before t1. c1 nextto t1. all_forward."+
				// input signals of dark blue NOR gate
				// in2 -> pIn2 -> c1
				"pIn2_1 exactly 1. in2 induces pIn2_1. pIn2 drives c1. "+
				// in1 -> pIn1 -> c1
				"pIn1 exactly 1. in1 induces pIn1. pIn1 drives c1. "+
				// order of the promoters
				"pIn2 before pIn1. "+
				
				// light blue NOR gate's components
				"r2 exactly 1. c2 exactly 1. t2 exactly 1."+
				// - its components' positions
				"r2 before c2. r2 nextto c2. c2 before t2. c2 nextto t2."+
				// - its components' orientation
				"all_forward."+
				// input signals of light blue NOR gate
				// c1 -> p1 -> c2 
				//   for a better separation between the two p1 promoters, 
				//   we renamed the p1 promoter that drives c2 to p1_2
				//   (it's repressed by c1 and drives c2)
				"p1_2 exactly 1. c1 represses p1_2. p1_2 drives c2. "+
				// 0 -> p0 -> c2
				"p0 exactly 1. 0 induces p0. p0 drives c2."+
				// order of the promoters of the light blue NOR gate
				"p1 before p0."+

				// the out1 reporting cassette's components
				"rOut1 exactly 1. cRFP exactly 1. tOut1 exactly 1. "+
				// positioning of its components
				"rOut1 before cRFP. rOut1 nextto cRFP. cRFP before tOut1. cRFP nextto tOut1. all_forward."+				
				// input signals of the out1 cassette
				// c2 -> p2 -> cRFP 
				"p2 exactly 1. c2 represses p2. p2 drives cRFP." +
				
				// the orange NOR gate
				// - its components
				"r3 exactly 1. c3 exactly 1. t3 exactly 1."+
				// - the components' positioning
				"r3 before c3. r3 nextto c3. c3 before t3. c3 nextto t3."+
				// - the components' orientation
				"all_forward."+
				// - its input signals
				//   - in2 -> pIn2 -> c3   
				"in2 induces pIn2_3. pIn2_3 exactly 1. pIn2_3 drives c3. " + 
				//   - p1 -> c3
				//   HACK: we added a new p1 promoter (named p1_3 since it's repressed by c1 and drives c3)
				//   this line from Iteration 2 
				//   "p1 exactly 1. p1 drives c3. "
				//   gets into
				//  - c1 -> p1_3 -> c3
				"c1 represses p1_3. p1_3 exactly 1. p1_3 drives c3. "+
				// - positioning of the input promoters
				"p1_3 before pIn2_3." +
				
				// the light green NOR gate
				// - its components
				"r4 exactly 1. c4 exactly 1. t4 exactly 1."+
				// - its components' positioning
				"r4 before c4. r4 nextto c4. c4 before t4. c4 nextto t4."+
				// - its components' orientation
				"all_forward."+
				// - its input signals
				//   - in2 -> pIn2 -> c4
				//   HACK: we added a new pIn2 promoter (named pIn2_4 since it drives c4)
				"in2 induces pIn2_4. pIn2_4 exactly 1. pIn2_4 drives c4."+
				//   - in0 -> pIn0 -> c4
				"in0 induces pIn0. pIn0 exactly 1. pIn0 drives c4."+
				// - positioning of the input promoters
				"pIn2_4 before pIn0."+
				
				// the dark green NOR gate
				// - its components
				"r5 exactly 1. c5 exactly 1. t5 exactly 1."+
				// - its components' positioning
				"r5 before c5. r5 nextto c5. c5 before t5. c5 nextto t5."+
				// - its components' orientations
				"all_forward."+
				// - its input signals
				//   - c3 -> p3 -> c5
				"c3 represses p3. p3 exactly 1. p3 drives c5. "+
				//   - c4 -> p4 -> c5
				"c4 represses p4. p4 exactly 1. p4 drives c5. "+
				// - positioning of its input promoters
				"p3 before p4."+
				
				// out0 reporting cassette
				// - its components
				"rOut0 exactly 1. cGFP exactly 1. tOut0 exactly 1. "+
				// - its components' positioning
				"rOut0 before cGFP. rOut0 nextto cGFP. cGFP before tOut0. cGFP nextto tOut0."+
				// - its components' orientations
				"all_forward."+
				// - its input signals
				// c5 -> p5 -> cGFP
				"c5 represses p5. p5 exactly 1. p5 drives cGFP.");
	}

	public void test(String name, String script) {
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
			me.solve(script, 1);

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
			se.pigeonize("./images/acm-jetc/rules/"+name+".png", colors, false);
		} catch(Exception e) {
			e.printStackTrace();
		}

		// CONSOLE OUTPUT
		se.toConsole();

		tProcessing = System.nanoTime() - t1;
		System.out.println("[TestSuite.test] full processing time: "+tProcessing*Math.pow(10, -9)+"sec");
		
		//new Eugene(sFile);
	}
}
