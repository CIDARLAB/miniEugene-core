package org.cidarlab.minieugene.tests;


public class InteractionTester {
	
	public static void test() {
//		testInteractionsOnTypes();
		testInteractionsOnComponents();
	}
	
	private static void testInteractionsOnTypes() {
		testInducesOnTypes();
		testRepressesOnTypes();
		testDrivesOnTypes();
	}
	
	private static void testInducesOnTypes() {
	}
	
	private static void testRepressesOnTypes() {
	}
	
	private static void testDrivesOnTypes() {
	}

	private static void testInteractionsOnComponents() {
//		testInducesOnComponents();
//		testRepressesOnComponents();
		testDrivesOnComponents();
	}
	
	private static void testInducesOnComponents() {
		MiniEugeneTester.test("N=2. in1 induces pIn1. in2 induces pIn2.");
		MiniEugeneTester.test("N=3. in1 induces pIn1. in2 induces pIn2.");
		MiniEugeneTester.test("N=4. in1 induces pIn1. in2 induces pIn2.");
		MiniEugeneTester.test("N=5. in1 induces pIn1. in2 induces pIn2.");
	}
	
	private static void testRepressesOnComponents() {
		MiniEugeneTester.test("N=2. g1 represses p1. g2 represses p2.");
		MiniEugeneTester.test("N=3. g1 represses p1. g2 represses p2.");
		MiniEugeneTester.test("N=4. g1 represses p1. g2 represses p2.");
		MiniEugeneTester.test("N=5. g1 represses p1. g2 represses p2.");
	}
	
	private static void testDrivesOnComponents() {
//		MiniEugeneTester.test("N=2. p1 drives g2.");
		MiniEugeneTester.test("N=3. p1 drives g2. contains t. contains p1. contains g2.");
//		MiniEugeneTester.test("N=2. g1 represses p1. p1 drives g2. g2 represses p2.");
//		MiniEugeneTester.test("N=3. g1 represses p1. p1 drives g2. g2 represses p2.");
//		MiniEugeneTester.test("N=4. g1 represses p1. p1 drives g2. g2 represses p2.");
	}
}
