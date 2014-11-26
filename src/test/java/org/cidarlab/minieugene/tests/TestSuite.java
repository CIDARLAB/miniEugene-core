/*
 * Copyright (c) 2014, Boston University
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 *    
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *    
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.minieugene.tests;

import java.io.File;

public class TestSuite {

	public static void main(String[] args) 
			throws Exception {

		/*
		 * BASIC ``Syntax'' TESTS
		 */
//		MiniEugeneTester.testFile(new File("./tests/comments"));

		/*
		 * naming of rule operands
		 */
//		MiniEugeneTester.testFile(new File("./tests/operand-naming.eug"));
//		MiniEugeneTester.testFile(new File("./tests/bryan/ex1"));
//		MiniEugeneTester.testFile(new File("./tests/bryan/ex2"));
//		MiniEugeneTester.testFile(new File("./tests/bryan/ex3"));
//		MiniEugeneTester.testFile(new File("./tests/bryan/ex4"));

		
		/*
		 * specification of facts
		 */
//		testFactSpecification();
		
//		CountingTester.test();
//		OrientationTester.test();
//		PairingTester.test();
//		InteractionTester.test();
//		TemplateTester.test();
		
//		MiniEugeneTester.test("N=4. contains p. p is_a Promoter. "+
//				"contains r. r is_a RBS. "+
//				"contains g. g is_a Gene. "+
//				"contains t. t is_a Terminator.");
		
		// first run -> p, r, g, t
		// second run -> p, r, g, t, p1, r1, g1, t1
//		MiniEugeneTester.test("N=4. contains p1. p is_a Promoter. "+
//				"contains r1. r is_a RBS. "+
//				"contains g1. g is_a Gene. "+
//				"contains t1. t is_a Terminator.");

//		MiniEugeneTester.test("N=4. Promoter before RBS. RBS before Gene. Gene before Terminator."+
//				"p1 is_a Promoter. p2 is_a Promoter. p3 is_a Promoter." + 
//				"r1 is_a RBS. r2 is_a RBS. r3 is_a RBS." +
//				"g1 is_a Gene. g2 is_a Gene. g3 is_a Gene." +
//				"t1 is_a Terminator. t2 is_a Terminator. t3 is_a Terminator.");
		

		// consistency
//		MiniEugeneTester.test("N=2. contains a. contains b. a before b.");
//		MiniEugeneTester.test("N=2. contains a. contains b. a before b. b before a.");
//		MiniEugeneTester.test("N=2. contains a. notcontains a.");
//		MiniEugeneTester.test("N=2. contains a. contains a.");
//		MiniEugeneTester.test("N=2. contains a. contains b. a nextto b.");
//		MiniEugeneTester.test("N=2. contains a. contains b. a nextto b. not a nextto b.");
//		MiniEugeneTester.test("N=2. contains a. contains b. a nextto b. b nextto a.");

		/*
		 * COUNTING RULES
		 */
		// CONTAINS
//		MiniEugeneTester.test("N=4.CONTAINS p.CONTAINS c.");
		
		// SAME_COUNT
//		MiniEugeneTester.testFile(new File("./tests/counting/same_count01"));
//		MiniEugeneTester.testFile(new File("./tests/counting/same_count02"));
//		MiniEugeneTester.testFile(new File("./tests/counting/same_count03"));

		// EXACTLY
//		MiniEugeneTester.test("N=1. r1 exactly 1.");		
//		MiniEugeneTester.test("N=10.CONTAINS p.contains c.contains t.p drives c.p same_orientation t.[0] equals p.[1] equals c.");
//		MiniEugeneTester.test("N=8.CONTAINS c.STARTSWITH p OR STARTSWITH t.ENDSWITH p OR ENDSWITH t.c NEXTTO r. r BEFORE c.p SOME_BEFORE r.r SOME_BEFORE p.t SOME_AFTER c. t SOME_BEFORE p.");
//		MiniEugeneTester.test("N=16."+
//				"P1 EXACTLY 1.r1 EXACTLY 1.c1 EXACTLY 1.T1 EXACTLY 1.ALL_FORWARD.STARTSWITH P1."+
//				"r1 NEXTTO c1.c1 NEXTTO T1.P1 NEXTTO r1.P2 EXACTLY 1.r2 EXACTLY 1.c2 EXACTLY 1.T2 EXACTLY 1.ALL_FORWARD."+
//				"P1 BEFORE P2.r2 NEXTTO c2.r2 BEFORE c2.c2 NEXTTO T2.P2 NEXTTO r2.P2 EXACTLY 1.r3 EXACTLY 1.c3 EXACTLY 1."+
//				"T3 EXACTLY 1.ALL_FORWARD.P2 BEFORE P3.r3 NEXTTO c3.r3 BEFORE c3.c3 NEXTTO T3.P3 NEXTTO r3.P4 EXACTLY 1.r4 EXACTLY 1."+
//				"c4 EXACTLY 1.T4 EXACTLY 1.ALL_FORWARD.P3 BEFORE P4.r4 NEXTTO c4.r4 BEFORE c4.c4 NEXTTO T4.P4 NEXTTO r4."+
//				"cTetR REPRESSES PTetR.cLacI REPRESSES PLacI.");
		
		/*
		 * CALCULATE minN
		 */
//		MiniEugeneTester.testFile(new File("./tests/counting/minN/minN01"));

		/*
		 * BEFORE
		 */
//		MiniEugeneTester.test("N=2.a before b.");
//		
		/*
		 * THEN
		 */
//		MiniEugeneTester.testFile(new File("./tests/then/then01"));
//		MiniEugeneTester.testFile(new File("./tests/then/then02"));
//		MiniEugeneTester.testFile(new File("./tests/then/then03"));
//		MiniEugeneTester.testFile(new File("./tests/swati/test01"));

		/*
		 * WITH
		 */
		//MiniEugeneTester.test("N=4.TEMPLATE [Promoter|RBS|Gene|Terminator], [Promoter|RBS|Gene|Terminator], [Promoter|RBS|Gene|Terminator], [Promoter|RBS|Gene|Terminator].p0 IS_A Promoter.rbs0 IS_A RBS.g0 IS_A Gene.t0 IS_A Terminator.Promoter with RBS.RBS with Gene.Gene with Terminator.all_reverse.Promoter after RBS.RBS after Gene.Gene after Terminator.");

		/*
		 * POSITIONING
		 */
		// ALL_BEFORE
//		MiniEugeneTester.testFile(new File("./tests/before/all_before01"));
		
		// SOME_BEFORE
//		MiniEugeneTester.testFile(new File("./tests/before/some_before01"));
//		MiniEugeneTester.testFile(new File("./tests/before/some_before02"));
//		MiniEugeneTester.testFile(new File("./tests/before/some_before03"));

		// ALWAYS_NEXTTO
//		MiniEugeneTester.testFile(new File("./tests/pairing/always_nextto01"));
		
		/*
		 * PAIRING RULES
		 */
		// EQUALS
		
		// solely indices
//		MiniEugeneTester.test("N=2.contains p.[0] equals [1].");
//		MiniEugeneTester.test("N=2.contains p.[1] equals [0].");
//		MiniEugeneTester.test("N=3.contains p.contains c.[1] equals [0].");
//		MiniEugeneTester.test("N=3.contains p.contains c.[1] equals [2].");
//		MiniEugeneTester.test("N=3.contains p.contains c.[0] equals [1] or [1] equals [2].");

		// invalid indices
//		MiniEugeneTester.test("N=3.contains p.contains c.[3] equals [-1].");

		// indices + components
//		MiniEugeneTester.test("N=2.[0] equals p or [0] equals c.[1] equals c or [1] equals p.");
		
		// NOTEQUALS
//		MiniEugeneTester.test("N=2.contains p.[0] notequals [1].");   // no solution
//		MiniEugeneTester.test("N=2.contains p.[1] notequals [0].");   // no solution
//		MiniEugeneTester.test("N=3.all_forward.contains p.contains c.[0] notequals [1].[1] notequals [2]."); 
//		MiniEugeneTester.test("N=3.all_forward.contains p.contains c.contains t.[0] notequals [1].[1] notequals [2]."); 
//		MiniEugeneTester.test("N=3.contains p.contains c.[0] equals [1].[1] notequals [2].all_forward p."); 
		
		/*
		 * ORIENTATION RULES
		 */		
//		MiniEugeneTester.test("N=2.contains a.all_forward \\/ all_reverse.");
		
		// SOME_FORWARD
//		MiniEugeneTester.testFile(new File("./tests/orientation/forward/some01"));
//		MiniEugeneTester.testFile(new File("./tests/orientation/forward/some02"));
//		MiniEugeneTester.testFile(new File("./tests/orientation/forward/some03"));
		
		// SOME_REVERSE
//		MiniEugeneTester.testFile(new File("./tests/orientation/reverse/some01"));
//		MiniEugeneTester.testFile(new File("./tests/orientation/reverse/some02"));
//		MiniEugeneTester.testFile(new File("./tests/orientation/reverse/some03"));
//		MiniEugeneTester.test("N=2. some_forward [0]. contains p.");
//		MiniEugeneTester.testFile(new File("./tests/orientation/alternate/alternate02"));

		// ALTERNATE 
//		MiniEugeneTester.testFile(new File("./tests/orientation/alternate/alternate01"));
//		MiniEugeneTester.testFile(new File("./tests/orientation/alternate/alternate02"));
		
		
		
		// SAME_ORIENTATION
//		MiniEugeneTester.testFile(new File("./tests/orientation/same_orientation/same01"));
//		MiniEugeneTester.testFile(new File("./tests/orientation/same_orientation/same02"));
//		MiniEugeneTester.testFile(new File("./tests/orientation/same_orientation/same03"));
//		MiniEugeneTester.testFile(new File("./tests/orientation/same_orientation/same04"));
//		MiniEugeneTester.testFile(new File("./tests/orientation/same_orientation/same05"));

//		MiniEugeneTester.testFile(new File("./tests/orientation/same_orientation/some_same01"));
//		MiniEugeneTester.testFile(new File("./tests/orientation/same_orientation/some_same02"));

		
		// ORIENTATION RULES WITH INDICES
//		MiniEugeneTester.test("N=2. contains p. forward [0]. forward [1].");
//		MiniEugeneTester.test("N=2. contains p. forward [0].");
//		MiniEugeneTester.test("N=2. contains p. forward [1].");
//		MiniEugeneTester.test("N=2. contains p. reverse [0]. reverse [1].");
//		MiniEugeneTester.test("N=2. contains p. reverse [0].");
//		MiniEugeneTester.test("N=2. contains p. reverse [1].");
//		MiniEugeneTester.test("N=4. contains p. contains r. contains c. contains t. forward [0]. reverse [1]. forward [2]. reverse [3].");

		// faulty ones
//		MiniEugeneTester.test("N=2. contains p. reverse [2].");   // index too high
//		MiniEugeneTester.test("N=2. contains p. reverse [-1].");  // index too low
//		MiniEugeneTester.test("N=1. contains p. some_forward [0].");
//		MiniEugeneTester.test("N=1. contains p. some_reverse [0].");

		
		/*
		 * INTERACTION RULES
		 */
//		MiniEugeneTester.testFile(new File("./tests/interaction/drives01"));
//		MiniEugeneTester.testFile(new File("./tests/interaction/drives02"));
//		MiniEugeneTester.testFile(new File("./tests/interactions.eug"));

		// DRIVES
//		MiniEugeneTester.test("N=3.contains p. contains c. contains t.p drives c.");
//		MiniEugeneTester.test("N=4.contains p. contains c. contains t.p drives c.");
//		MiniEugeneTester.test("N=5.contains p. contains c. contains t.p drives c.");
//		MiniEugeneTester.test("N=6.contains p. contains c. contains t.p drives c.");
		
		// INDUCES
//		MiniEugeneTester.test("N=2. contains c. contains p. c induces p.");
//		MiniEugeneTester.test("N=1. contains p. in induces p.");

		// REPRESSES
//		MiniEugeneTester.test("N=2. contains c. contains p. c represses p.");

		/*
		 * LOGICAL OR
		 */
//		MiniEugeneTester.testFile(new File("./tests/or/or01"));
		
		/*
		 * LOGICAL NOT
		 * negation of rules
		 */
//		MiniEugeneTester.test("N=2.contains a. not contains b.all_forward.");
//		MiniEugeneTester.test("N=2.not contains a. not contains b.all_forward.");

		// IF contains a THEN contains b
		// <==>
//		MiniEugeneTester.test("N=2.not contains a or contains b.all_forward.");
		// forward a => forward b
//		MiniEugeneTester.test("N=2.not forward a or forward b.contains a.contains b.");
		
		// we want this pattern:
		// a1, b, a2, c
		// a2, c, a1, b
//		MiniEugeneTester.test("N=4.template [a1|a2|b|c], [a1|a2|b|c], [a1|a2|b|c], [a1|a2|b|c]. "+
//				"a1 exactly 1. a2 exactly 1. b exactly 1. c exactly 1. all_forward."+
//				"a1 before b. a1 nextto b."+
//				"a2 before c. a2 nextto c.");
		
		
		
		/*
		 * minN calculations
		 */
//		MiniEugeneTester.testFile(new File("./tests/N/minN01"));
//		MiniEugeneTester.testFile(new File("./tests/N/minN02"));
//		MiniEugeneTester.testFile(new File("./tests/N/minN03"));   // DOES NOT WORK YET!
		
		/*
		 * miniEugene's TEMPLATING CONSTRAINTS
		 */
		
		// TEMPLATES
//		MiniEugeneTester.test("N=1.all_forward.template a.");
//		MiniEugeneTester.test("N=100.all_forward.template a.");
//		MiniEugeneTester.test("N=4.all_forward.template Promoter, RBS, CDS, Terminator.");
//		MiniEugeneTester.test("N=400.all_forward.template Promoter, RBS, CDS, Terminator.");
//		MiniEugeneTester.test("N=4.all_forward.template a, b.");
//		MiniEugeneTester.test("N=20.alternate_orientation.template a, b.");
//		MiniEugeneTester.test("N=20.template a, b.");
//		MiniEugeneTester.test("N=200.all_forward \\/ all_reverse.template a, b.");
		
		// negated templates
//		MiniEugeneTester.test("N=4.all_forward.not template promoter, rbs, cds, terminator.");

		// templates with selections
//		MiniEugeneTester.test("N=4.all_forward.template [p1], [r1], [c1], [t1].");
//		MiniEugeneTester.test("N=4.all_forward.template [p1|p2], [r1|r2], [c1|c2], [t1|t2].");
//		MiniEugeneTester.test("N=4.all_forward.template [p1|p2|p3], [r1|r2|r3], [c1|c2|c3], [t1|t2|t3].");
//		MiniEugeneTester.test("N=8.all_forward.template [p1], [r1], [c1], [t1].");
//		MiniEugeneTester.test("N=8.all_forward.template [p1|p2], [r1|r2], [c1|c2], [t1|t2].");
//		MiniEugeneTester.test("N=8.all_forward.template [p1|p2|p3], [r1|r2|r3], [c1|c2|c3], [t1|t2|t3].");
//		MiniEugeneTester.test("N=12.all_forward.template [p1], [r1], [c1], [t1].");
//		MiniEugeneTester.test("N=12.all_forward.template [p1|p2], [r1|r2], [c1|c2], [t1|t2].");
//		MiniEugeneTester.test("N=12.all_forward.template [p1|p2|p3], [r1|r2|r3], [c1|c2|c3], [t1|t2|t3].");
		
		
		// DISJUNCTION OF TEMPLATES
//		MiniEugeneTester.test("N=4. p1 same_orientation r1. p1 same_orientation c1. p1 same_orientation t1. not forward p1 \\/ template [p1], [r1], [c1], [t1]. not reverse p1 \\/ template [t1], [c1], [r1], [p1].");
//		MiniEugeneTester.test("N=8. p1 same_orientation r1. p1 same_orientation c1. p1 same_orientation t1. not forward p1 \\/ template [p1], [r1], [c1], [t1]. not reverse p1 \\/ template [t1], [c1], [r1], [p1].");		
//		MiniEugeneTester.test("N=12. p1 same_orientation r1. p1 same_orientation c1. p1 same_orientation t1. not forward p1 \\/ template [p1], [r1], [c1], [t1]. not reverse p1 \\/ template [t1], [c1], [r1], [p1].");
		
		// SEQUENCES
//		MiniEugeneTester.test("N=2.sequence p, c.");
//		MiniEugeneTester.test("N=3.contains X. all_forward. sequence p, c.");
//		MiniEugeneTester.test("N=3.contains X. all_forward. sequence [p1|p2], [c1|c2].");
//		MiniEugeneTester.test("N=50.X exactly 48. all_forward. sequence [p1|p2], [c1|c2].");
//		MiniEugeneTester.test("N=4. sequence [p1|p2|p3|c1|c2|c3]. p1 with c1 \\/ p2 with c2 \\/ p3 with c3. p1 same_orientation c1. p2 same_orientation c2. p3 same_orientation c3. not forward p1 \\/ p1 before c1. not forward p2 \\/ p2 before c2. not forward p3 \\/ p3 before c3. not reverse p1 \\/ p1 after c1. not reverse p2 \\/ p2 after c2. not reverse p3 \\/ p3 after c3.");
//		MiniEugeneTester.test("N=5.all_forward. sequence [p1|p2], [c1|c2]. p1 then c1. p2 then c2.");
//		MiniEugeneTester.test("N=30.p exactly 1.p same_count c.p same_orientation c.not forward p \\/ sequence p, c.not reverse p \\/ sequence c, p.contains t. p same_orientation t.");
//		MiniEugeneTester.test("N=40. all_forward. template [p1|p2],[r1|r2],[c1|c2],[t1|t2].");
		
		// INVALID SEQUENCS/TEMPLATES
		
		// Extensional Support
//		MiniEugeneTester.test("N=1. all_forward. template p.");
//		MiniEugeneTester.test("N=1. all_forward. template [p1|p2].");
//		MiniEugeneTester.test("N=1. all_forward. template [p1|p2|p3].");
//		MiniEugeneTester.test("N=1. all_forward. template [p1|p2|p3|p4].");
//		MiniEugeneTester.test("N=2. all_forward. template p.");
//		MiniEugeneTester.test("N=2. all_forward. template [p1|p2].");
//		MiniEugeneTester.test("N=2. all_forward. template [p1|p2|p3].");
//		MiniEugeneTester.test("N=2. all_forward. template [p1|p2|p3|p4].");
//		MiniEugeneTester.test("N=3. all_forward. template p.");
//		MiniEugeneTester.test("N=3. all_forward. template [p1|p2].");
//		MiniEugeneTester.test("N=3. all_forward. template [p1|p2|p3].");
//		MiniEugeneTester.test("N=3. all_forward. template [p1|p2|p3|p4].");
//		MiniEugeneTester.test("N=2. all_forward. template [p1|p2], [c1|c2].");
//		MiniEugeneTester.test("N=4. all_forward. template [p1|p2], [r1|r2].");
//		MiniEugeneTester.test("N=6. all_forward. template [p1|p2], [c1|c2].");
//		MiniEugeneTester.test("N=8. all_forward. template [p1|p2], [r1|r2].");
//		MiniEugeneTester.test("N=3. template [p1|p2], [p2|p3], [p4|p5].");
//		MiniEugeneTester.test("N=3. all_forward. template [p1|p2|p3], [r1|r2|r3], [p1|p2|p3].");		
//		MiniEugeneTester.test("N=12. template p, r, c, t.");
//		MiniEugeneTester.test("N=12. all_forward. template [p1|p2|p3], [r1|r2|r3], [c1|c2|c3], [t1|t2|t3].");

		/*
		 * ACT
		 */
//		MiniEugeneTester.testFile(new File("./tests/act/cyclic"));
//		MiniEugeneTester.testFile(new File("./tests/act/cyclic02"));
//		MiniEugeneTester.testFile(new File("./tests/act/cyclic03"));
//		MiniEugeneTester.testFile(new File("./tests/act/cyclic04"));
//		MiniEugeneTester.testFile(new File("./tests/act/cyclic05"));
//		MiniEugeneTester.testFile(new File("./tests/act/cyclic06"));
//
//		MiniEugeneTester.testFile(new File("./tests/act/acyclic"));
//		MiniEugeneTester.testFile(new File("./tests/act/acyclic02"));
//		MiniEugeneTester.testFile(new File("./tests/act/acyclic03"));
//		MiniEugeneTester.testFile(new File("./tests/act/acyclic04"));

//		MiniEugeneTester.testFile(new File("./tests/act/fsm"));
//		MiniEugeneTester.testFile(new File("./tests/act/fsm02"));

//		MiniEugeneTester.testFile(new File("./tests/swati/inverter"));
//		MiniEugeneTester.testFile(new File("./designs/templating/inverter"));

		/*
		 * LARGE DESIGNS + SOLUTION LISTENER
		 */
//		MiniEugeneTester.test("N=30. contains a. contains b.");
//		MiniEugeneTester.test("N=4. contains a. contains b. contains c. contains d.");
		
		/*
		 * 
		 */
//		MiniEugeneTester.test("N=20. CONTAINS p or contains c or contains t or contains r. p before r. p nextto r. "+
//				"r before c. c before t. all_forward.");
		
		// the published toggle-switch design
//		MiniEugeneTester.test("N=10. template T1T2_1, cLacI, rRBS1, P1, Ptrc2, rbsE, cR1, rbsB, cGFPmut3, T1T2_1."+
//				"reverse T1T2_1. reverse cLacI. reverse rRBS1. reverse P1."+
//				"forward Ptrc2. forward rbsE. forward cR1. forward rbsB. forward cGFPmut3. forward T1T2_2."+
//				"cR1 represses P1. cLacI represses Ptrc2.");
		/*
		 * LATTICE 
		 */
//		MiniEugeneTester.testFile(new File("./tests/lattice/daniel01"));
//		MiniEugeneTester.testFile(new File("./tests/lattice/daniel02"));
//		MiniEugeneTester.testFile(new File("./tests/lattice/daniel03"));

//		MiniEugeneTester.testAll("./tests");
		
//		MiniEugeneTester.test("N=4.sequence p,[[r,c],t]+.");
//		MiniEugeneTester.test("N=4.sequence p,[[r,c],t]*.");
//		MiniEugeneTester.test("N=4. sequence p, c+, t.");
//		MiniEugeneTester.test("N=1. sequence p, c, t.");

		/*
		 * NR_OF_SOLUTIONS tests
		 * => miniEugene should always return 50000 solutions
		 * we need this test, to test the miniEugene web app
		 */
//		MiniEugeneTester.test("N=16.all_forward.template [pAraBAD1|pAraBAD2|pConst|pTetR], [rB0030m|rB0031m|rB0032m|rB0033m|rB0034m], [cAraC|cTetR|cRFP|cGFP], term, [pAraBAD1|pAraBAD2|pConst|pTetR],[rB0030m|rB0031m|rB0032m|rB0033m|rB0034m], [cAraC|cTetR|cRFP|cGFP], term, [pAraBAD1|pAraBAD2|pConst|pTetR], [rB0030m|rB0031m|rB0032m|rB0033m|rB0034m], [cAraC|cTetR|cRFP|cGFP], term, [pAraBAD1|pAraBAD2|pConst|pTetR], [rB0030m|rB0031m|rB0032m|rB0033m|rB0034m], [cAraC|cTetR|cRFP|cGFP], term.");
		
		/*
		 * REAL DESIGNS
		 */
		
		// NOR GATES
//		MiniEugeneTester.testFile(new File("./designs/nor-orientations"));
//		MiniEugeneTester.testFile(new File("./designs/nor-events"));
		
		
		// INVERTERS
//		MiniEugeneTester.testFile(new File("./tests/swati/test01"));
//		MiniEugeneTester.testFile(new File("./tests/swati/inverter"));
		
		// HERE!
//		MiniEugeneTester.testFile(new File("./designs/cidar/inverters/inverters"));

		
		// Examples of the miniEugene web site
//		MiniEugeneTester.testFile(new File("./designs/web-site/inverter"));
//		MiniEugeneTester.testFile(new File("./designs/web-site/toggle-switch"));
//		MiniEugeneTester.testFile(new File("./designs/web-site/nor"));
		
		/*
		 * Poojah and Devina's Senior Project
		 */
//		MiniEugeneTester.test("N=9.template pJ23104_AB, rBCD2_BC, [cE1010m_CD|cE0040m_CD], tB0015_DE, pJ23104_EB, rBCD2_BC, [cE1010m_CD|cE0040m_CD], tB0015_DF, DVL2.[2] NOTEQUALS [6]. all_forward.");
//		MiniEugeneTester.testFile(new File("./designs/nor"));

//		MiniEugeneTester.testFile(new File("./designs/nor"));

		// BROAD CISTRONS
//		MiniEugeneTester.testFile(new File("./designs/broad/pathway"));

		/*
		 * ACM JETC
		 */
		// TODO NEXT
//		MiniEugeneTester.testFile(new File("./designs/priority-encoder/acm-jetc/rule-based/iteration1/dark-blue"));
//		MiniEugeneTester.testFile(new File("./designs/priority-encoder/acm-jetc/rule-based/iteration2/out0"));
//		MiniEugeneTester.testFile(new File("./designs/priority-encoder/acm-jetc/rule-based/iteration2/out1"));

		/*
		 * NOR GATE EXAMPLE
		 */
//		MiniEugeneTester.testFile(new File("./designs/nor-gate/repressing-cassette.eug"));
//		MiniEugeneTester.testFile(new File("./designs/nor-gate/reporting-cassette.eug"));
//		MiniEugeneTester.testFile(new File("./designs/nor-gate/nor-gate.eug"));
//		MiniEugeneTester.testFile(new File("./designs/nor-orientations"));
//		MiniEugeneTester.testFile(new File("./designs/nor-gate/templates/nor-templates"));

		/*
		 * Swati's Inverters
		 */
//		MiniEugeneTester.testFile(new File("./designs/templating/inverter"));
//		MiniEugeneTester.testFile(new File("./designs/templating/inverter_rev1"));

//		MiniEugeneTester.testFile(new File("./designs/nor-gate/rev1/repressing-cassette.eug"));
//		MiniEugeneTester.testFile(new File("./designs/nor-gate/rev1/reporting-cassette.eug"));
//		MiniEugeneTester.testFile(new File("./designs/nor-gate/rev1/nor-gate.eug"));

//		MiniEugeneTester.testFile(new File("./designs/nor-gate/nor-gate.eug"));
//		MiniEugeneTester.testFile(new File("./examples/transcriptional-unit.eug"));
		
		MiniEugeneTester.testFile(new File("./designs/cidar/bryan/operons"));
		
		/*
		 * SB2 examples
		 */
//		MiniEugeneTester.test("N=1. template [p1|r1|c1|t1].");
		
		
	}

	
	public static void testFactSpecification() {
		MiniEugeneTester.test("N=2. p is_a Promoter. g is_a Gene.");
		MiniEugeneTester.test("N=2. p1 is_a Promoter. g is_a Gene.");
		MiniEugeneTester.test("N=4. p is_a Promoter. r is_a RBS. g is_a Gene. t is_a Terminator.");
		
		StringBuilder sb = new StringBuilder();
		sb.append("N=100. ");
		
		StringBuilder sbProm = new StringBuilder();
		StringBuilder sbRBS = new StringBuilder();
		StringBuilder sbGene = new StringBuilder();
		StringBuilder sbTerm = new StringBuilder();
		for(int i=1; i<=100; i++) {
			sbProm.append("p").append(i).append(" is_a Promoter.");
			sbRBS.append("r").append(i).append(" is_a RBS.");
			sbGene.append("g").append(i).append(" is_a Gene.");
			sbTerm.append("t").append(i).append(" is_a Terminator.");
		}
		sb.append(sbProm).append(sbRBS).append(sbGene).append(sbTerm);
		MiniEugeneTester.test(sb.toString());
		
		
		// some weird ones
		MiniEugeneTester.test("N=4. Promoter is_a Promoter. RBS is_a RBS. Gene is_a Gene. Terminator is_a Terminator.");
	}
	
}
