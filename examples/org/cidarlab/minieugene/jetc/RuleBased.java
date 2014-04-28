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
		this.colors.put("p1", 2);
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
		
//		rb.iteration1();
		
		/*
		 * ITERATION 2: 
		 */
//		rb.iteration2();
		

		
		/*
		 * ITERATION 3
		 */
		rb.test("iteration3-penc",
				rb.iteration3());
		
	}
	
	
	public void iteration1() {
		// ITERATION 1:
		// RBS-CDS-TERMINATOR tuples
		this.test("iteration1_darkblue_nor_cassette", "N=3."+this.iteration1_darkblue_nor_cassette());
		this.test("iteration1_lightblue_nor_cassette", "N=3."+this.iteration1_lightblue_nor_cassette());
		this.test("iteration1_out1_cassette", "N=3."+this.iteration1_out1_cassette());
		
		this.test("iteration1_orange_nor_cassette", "N=3."+this.iteration1_orange_nor_cassette());
		this.test("iteration1_lightgreen_nor_cassette", "N=3."+this.iteration1_lightgreen_nor_cassette());
		this.test("iteration1_darkgreen_nor_cassette", "N=3."+this.iteration1_darkgreen_nor_cassette());
		this.test("iteration1_out0_cassette", "N=3."+this.iteration1_out0_cassette());
		
	}
	
	public void iteration2() {
		System.out.println(this.iteration2_out1());
		this.test("iteration2-out1",		// name
				this.iteration2_out1());	// constraints

		this.test("iteration2-out0",		// name				
				this.iteration2_out0());	// constraints
	}
	
	private String iteration1_darkblue_nor_cassette() {
		return 
			// components
			"r1 exactly 1. c1 exactly 1. t1 exactly 1."+
			// positioning
			"r1 before c1. r1 nextto c1. c1 before t1. c1 nextto t1."+
			// orientation
			"all_forward.";
	}

	private String iteration1_lightblue_nor_cassette() {
		return 
			// components
			"r2 exactly 1. c2 exactly 1. t2 exactly 1."+
			// positioning
			"r2 before c2. r2 nextto c2. c2 before t2. c2 nextto t2."+
			// orientation
			"all_forward.";
	}

	private String iteration1_out1_cassette() {
		return 
			// components
			"rOut1 exactly 1. cRFP exactly 1. tOut1 exactly 1."+
			// positioning
			"rOut1 before cRFP. rOut1 nextto cRFP. cRFP before tOut1. cRFP nextto tOut1."+
			// orientation
			"all_forward.";
	}

	private String iteration1_orange_nor_cassette() {
		return 
			// components
			"r3 exactly 1. c3 exactly 1. t3 exactly 1."+
			// positioning
			"r3 before c3. r3 nextto c3. c3 before t3. c3 nextto t3."+
			// orientation
			"all_forward.";
	}

	private String iteration1_lightgreen_nor_cassette() {
		return 
			// components
			"r4 exactly 1. c4 exactly 1. t4 exactly 1."+
			// positioning
			"r4 before c4. r4 nextto c4. c4 before t4. c4 nextto t4."+
			// orientation
			"all_forward.";
	}


	private String iteration1_darkgreen_nor_cassette() {
		return 
			// components
			"r5 exactly 1. c5 exactly 1. t5 exactly 1."+
			// positioning
			"r5 before c5. r5 nextto c5. c5 before t5. c5 nextto t5."+
			// orientation
			"all_forward.";
	}

	private String iteration1_out0_cassette() {
		return 
			// components
			"rOut0 exactly 1. cGFP exactly 1. tOut0 exactly 1."+
			// positioning
			"rOut0 before cGFP. rOut0 nextto cGFP. cGFP before tOut0. cGFP nextto tOut0."+
			// orientation
			"all_forward.";
	}
	
	public String iteration2_out1() {
		return 
			"N=14." + 
			// dark blue NOR gate's components
			"r1 exactly 1. c1 exactly 1. t1 exactly 1. "+
			// ordering of its components
			"r1 before c1. r1 nextto c1. c1 before t1. c1 nextto t1. all_forward."+
			// input signals of dark blue NOR gate
			// in2 -> pIn2 -> c1
			"pIn2 exactly 1. in2 induces pIn2. pIn2 nextto pIn1. "+
			// in1 -> pIn1 -> c1
			"pIn1 exactly 1. in1 induces pIn1. pIn1 nextto r1. "+
			// order of the promoters
			"pIn2 before pIn1. "+
			
			// light blue NOR gate's components
			"r2 exactly 1.c2 exactly 1.t2 exactly 1."+
			// - its components' positions
			"r2 before c2. r2 nextto c2. c2 before t2. c2 nextto t2."+
			// - its components' orientation
			"all_forward."+
			// input signals of light blue NOR gate
			// c1 -> p1 -> c2 
			"p1 exactly 1. c1 represses p1. p1 nextto p0. "+
			// 0 -> p0 -> c2
			"p0 exactly 1. 0 induces p0. p0 nextto r2."+
			// order of the promoters of the light blue NOR gate
			"p1 before p0."+
	
			// the out1 reporting cassette's components
			"rOut1 exactly 1. cRFP exactly 1. tOut1 exactly 1. "+
			// positioning of its components
			"rOut1 before cRFP. rOut1 nextto cRFP. cRFP before tOut1. cRFP nextto tOut1. all_forward."+				
			// input signals of the out1 cassette
			// c2 -> p2 -> cRFP 
			"p2 exactly 1. c2 represses p2. p2 nextto rOut1.";
	}

	
	public String iteration2_out0() {
		
		return
			"N=19."+			
			// the orange NOR gate
			// - its components
			"r3 exactly 1. c3 exactly 1. t3 exactly 1."+
			// - the components' positioning
			"r3 before c3. r3 nextto c3. c3 before t3. c3 nextto t3."+
			// - the components' orientation
			"all_forward."+
			// - its input signals
			//   - in2 -> pIn2 -> c3   
			//   HACK: we added a new pIn2 promoter (named pIn2_3 since it drives c3)
			"in2 induces pIn2_3. pIn2_3 exactly 1. pIn2_3 nextto r3. " + 
			//   - p1 -> c3
			"p1 exactly 1. p1 nextto pIn2_3. "+
			// - positioning of the input promoters
			"p1 before pIn2_3." +
			
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
			"in2 induces pIn2_4. pIn2_4 exactly 1. pIn2_4 nextto pIn0."+
			//   - in0 -> pIn0 -> c4
			"in0 induces pIn0. pIn0 exactly 1. pIn0 nextto r4."+
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
			"c3 represses p3. p3 exactly 1. p3 nextto p4. "+
			//   - c4 -> p4 -> c5
			"c4 represses p4. p4 exactly 1. p4 nextto r5. "+
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
			"c5 represses p5. p5 exactly 1. p5 nextto rOut0.";
	}

	public String iteration3() {
		// - duplicate promoters 
		//   pIn2 -> pIn2_1, pIn2_3, pIn2_4
		//   p1 -> p1_2 and p1_3

		return "N=33."+
				// dark blue NOR gate's components
				"r1 exactly 1. c1 exactly 1. t1 exactly 1. "+
				// ordering of its components
				"r1 before c1. r1 nextto c1. c1 before t1. c1 nextto t1. all_forward."+
				// input signals of dark blue NOR gate
				// in2 -> pIn2 -> c1
				"pIn2_1 exactly 1. in2 induces pIn2_1. pIn2_1 nextto pIn1. "+
				// in1 -> pIn1 -> c1
				"pIn1 exactly 1. in1 induces pIn1. pIn1 nextto r1. "+
				// order of the promoters
				"pIn2_1 before pIn1. "+
				
				// light blue NOR gate's components
				"r2 exactly 1.c2 exactly 1.t2 exactly 1."+
				// - its components' positions
				"r2 before c2. r2 nextto c2. c2 before t2. c2 nextto t2."+
				// - its components' orientation
				"all_forward."+
				// input signals of light blue NOR gate
				// c1 -> p1 -> c2 
				"p1_2 exactly 1. c1 represses p1_2. p1_2 nextto p0. "+
				// 0 -> p0 -> c2
				"p0 exactly 1. 0 induces p0. p0 nextto r2."+
				// order of the promoters of the light blue NOR gate
				"p1_2 before p0."+
		
				// the out1 reporting cassette's components
				"rOut1 exactly 1. cRFP exactly 1. tOut1 exactly 1. "+
				// positioning of its components
				"rOut1 before cRFP. rOut1 nextto cRFP. cRFP before tOut1. cRFP nextto tOut1. all_forward."+				
				// input signals of the out1 cassette
				// c2 -> p2 -> cRFP 
				"p2 exactly 1. c2 represses p2. p2 nextto rOut1." +

				// the orange NOR gate
				// - its components
				"r3 exactly 1. c3 exactly 1. t3 exactly 1."+
				// - the components' positioning
				"r3 before c3. r3 nextto c3. c3 before t3. c3 nextto t3."+
				// - the components' orientation
				"all_forward."+
				// - its input signals
				//   - in2 -> pIn2 -> c3   
				//   HACK: we added a new pIn2 promoter (named pIn2_3 since it drives c3)
				"in2 induces pIn2_3. pIn2_3 exactly 1. pIn2_3 nextto r3. " + 
				//   - c1 -> p1 -> c3
				"c1 represses p1_3. p1_3 exactly 1. p1_3 nextto pIn2_3. "+
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
				"in2 induces pIn2_4. pIn2_4 exactly 1. pIn2_4 nextto pIn0."+
				//   - in0 -> pIn0 -> c4
				"in0 induces pIn0. pIn0 exactly 1. pIn0 nextto r4."+
				// - positioning of the input promoters
				"pIn2_4 before pIn0." +
				
				// the dark green NOR gate
				// - its components
				"r5 exactly 1. c5 exactly 1. t5 exactly 1."+
				// - its components' positioning
				"r5 before c5. r5 nextto c5. c5 before t5. c5 nextto t5."+
				// - its components' orientations
				"all_forward."+
				// - its input signals
				//   - c3 -> p3 -> c5
				"c3 represses p3. p3 exactly 1. p3 nextto p4. "+
				//   - c4 -> p4 -> c5
				"c4 represses p4. p4 exactly 1. p4 nextto r5. "+
				// - positioning of its input promoters
				"p3 before p4." +
				
				// out0 reporting cassette
				// - its components
				"rOut0 exactly 1. cGFP exactly 1. tOut0 exactly 1. "+
				// - its components' positioning
				"rOut0 before cGFP. rOut0 nextto cGFP. cGFP before tOut0. cGFP nextto tOut0."+
				// - its components' orientations
				"all_forward."+
				// - its input signals
				// c5 -> p5 -> cGFP
				"c5 represses p5. p5 exactly 1. p5 nextto rOut0.";
	}

	public void test(String name, String script) {
		MiniEugene me = new MiniEugene();				
		try {
			/*
			 * execute the script
			 */
			me.solve(script);

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
			se.pigeonize("./images/acm-jetc/rules/subset/5_"+name+".png", colors, false, 5);
		} catch(Exception e) {
			e.printStackTrace();
		}

		// CONSOLE OUTPUT
		se.toConsole();
	}
}
