package org.cidarlab.minieugene.tests;


public class OrientationTester {
	
	public static void test() {
		testOrientationOnTypes();
//		testOrientationOnComponents();
	}
	
	private static void testOrientationOnTypes() {
		testAllForwardTypes();
		testAllReverseTypes();
		testAllSameOrientationTypes();
		testAlternateOrientationTypes();
		testSomeForwardTypes();
		testSomeReverseTypes();
		testSomeSameOrientationTypes();
	}
	
	private static void testAllForwardTypes() {
		MiniEugeneTester.test("N=10. p1 is_a Promoter. p2 is_a Promoter. all_forward Promoter.");
	}
	
	private static void testAllReverseTypes() {
	}
	
	private static void testAllSameOrientationTypes() {
	}

	private static void testAlternateOrientationTypes() {
	}
	
	private static void testSomeForwardTypes() {
	}
	
	private static void testSomeReverseTypes() {
	}
	
	private static void testSomeSameOrientationTypes() {
	}
}
