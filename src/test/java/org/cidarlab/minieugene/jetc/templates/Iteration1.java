package org.cidarlab.minieugene.jetc.templates;

import java.net.URI;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.util.SolutionExporter;

/*
 * Iteration 1:
 * - find all forward oriented <ri, ci, ti> triplets
 * - find all reverse oriented <ti, ci, ri> triplets
 */
public class Iteration1 {

	
	public static void main(String[] args) {
		
		MiniEugene me = new MiniEugene();
		
		try {
			/*
			 * ( all_forward and template r, c, t ) OR ( all_reverse and template t, c, r)
			 * 
			 * we need to transform the disjunction into a CNF
			 * therefore, we substitute:
			 * a := all_forward
			 * b := template r, c, t
			 * c := all_reverse
			 * d := template t, c, r
			 * 
			 * Transformation:
			 * (a /\ b) \/ (c /\ d)                <=>
			 * ((a /\ b) \/ c) /\ ((a /\ b) \/ d)  <=>
			 * ((a \/ c) /\ (b \/ c)) /\ ((a \/ d) /\ ( b\/ d))
			 * 
			 * 
			 */			
			String script = "N=3."+
					"all_forward or all_reverse. "+
					"template [r1|r2|r3|r4|r5], [c1|c2|c3|c4|c5|GFP|RFP|YFP], [t1|t2|t3|t4|t5] or all_reverse." +
					"all_forward or sequence [t1|t2|t3|t4|t5], [c1|c2|c3|c4|c5|GFP|RFP|YFP], [r1|r2|r3|r4|r5]." +
					"template [r1|r2|r3|r4|r5], [c1|c2|c3|c4|c5|GFP|RFP|YFP], [t1|t2|t3|t4|t5] or template [t1|t2|t3|t4|t5], [c1|c2|c3|c4|c5|GFP|RFP|YFP], [r1|r2|r3|r4|r5].";
					

			me.solve(script);
			me.getStatistics().print();
			SolutionExporter se = new SolutionExporter(
					me.getSolutions(), 
					me.getInteractions());
			
//			URI pig = se.toPigeon();
			//WeyekinPoster.launchPage(pig);

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
