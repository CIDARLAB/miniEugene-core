/*
Copyright (c) 2014 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
 */

package org.cidarlab.minieugene.examples;

import java.net.URI;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.util.SolutionExporter;

/**
 * In this example we demonstrate how to utilize miniEugene's 
 * SolutionExporter in order to visualize the solutions using Pigeon (pigeoncad.org),
 * export the solutions to SBOL (sbolstandard.org), and to generate a 
 * Eugene header file (eugenecad.org). 
 * <p>
 * In general, the utilization of miniEugene is divided into the following steps:<br/>
 * 1. instantiate MiniEugene<br/>
 * 2. specify an String array, each row containing a miniEugene rule<br/>
 * 3. let miniEugene solve the rules (using MiniEugene's solve() method)<br/>
 * 4. process the solutions (maybe by using the SolutionExporter)<br/>
 *    and/or have a look into the statistics of the solving process<br/>
 *
 * @author Ernst Oberortner
 *
 */
public class SolutionExportExample {

	public static void main(String[] args) {
		/*
		 * STEP 1:
		 * Instantiating miniEugene
		 */
		MiniEugene me = new MiniEugene();
		
		/*
		 * STEP 2:
		 * specify some rules and put them 
		 * into a String array (String[])
		 */
		String[] rules = {
				"CONTAINS a", "CONTAINS b", "CONTAINS c", 
				"CONTAINS d", "CONTAINS e", "CONTAINS f", 
				"CONTAINS g", "CONTAINS h", "CONTAINS i",
				"CONTAINS j", "CONTAINS k", "CONTAINS l", 
				"a BEFORE b", "b BEFORE c", "c BEFORE d",
				"d BEFORE e", "e BEFORE f", "f BEFORE g",
				"g BEFORE h", "h BEFORE i", "i BEFORE j",
				"j BEFORE k", "k BEFORE l"};
		try {
			
			/* STEP 3:
			 * Ask miniEugene to solve the constraints
			 * for a design length of 12 and
			 * we'd like to have 20 solutions
			 */
			me.solve(rules, 12, 20);

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		/* STEP 4:
		 * we instantiate miniEugene's SolutionExporter
		 */
		SolutionExporter se = new SolutionExporter(
				me.getSolutions(), me.getInteractions());
		
		/*
		 * we visualize the solutions using Pigeon
		 */
		try {
			URI pigeonURI = se.toPigeon();
			System.out.println(pigeonURI.toString());
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}

		/*
		 * we export the solutions to SBOL
		 */
		try {
			se.toSBOL("./examples/solutions.sbol");
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}

		/*
		 * we export the solutions to Eugene
		 */
		try {
			se.toEugene("./examples/solutions.eug");
		} catch (EugeneException e) {
			e.printStackTrace();
		}
	}

}
