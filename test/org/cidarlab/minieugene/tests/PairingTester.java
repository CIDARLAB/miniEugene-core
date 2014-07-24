package org.cidarlab.minieugene.tests;


public class PairingTester {
	
	public static void test() {
		testPairingOnTypes();
		testPairingOnComponents();
		testEqualsIndices();
	}
	
	private static void testPairingOnTypes() {
		testThenTypes();
		testWithTypes();
		testAlwaysNextToTypes();
		testEqualsTypes();
	}
	
	private static void testThenTypes() {
		MiniEugeneTester.test("N=1. all_forward. p1 is_a Promoter. g1 is_a Gene. "+
				"Promoter THEN Gene.");
		MiniEugeneTester.test("N=2. all_forward. p1 is_a Promoter. g1 is_a Gene. "+
				"Promoter THEN Gene.");
		MiniEugeneTester.test("N=3. all_forward. p1 is_a Promoter. g1 is_a Gene. "+
				"Promoter THEN Gene.");
		MiniEugeneTester.test("N=4. all_forward. p1 is_a Promoter. g1 is_a Gene. "+
				"Promoter THEN Gene.");
	}
	
	private static void testWithTypes() {
		MiniEugeneTester.test("N=4. all_forward. p1 is_a Promoter. p2 is_a Promoter. g1 is_a Gene. g2 is_a Gene. "+
				"Promoter WITH Gene.");
//		MiniEugeneTester.test("N=10. all_forward. p1 is_a Promoter. p2 is_a Promoter. g1 is_a Gene. g2 is_a Gene. "+
//				"Promoter WITH Gene.");
	}
	
	private static void testAlwaysNextToTypes() {
		MiniEugeneTester.test("N=4. all_forward. p1 is_a Promoter. p2 is_a Promoter. g1 is_a Gene. g2 is_a Gene. "+
				"contains Promoter. contains Gene. Promoter ALWAYS_NEXTTO Gene.");
		MiniEugeneTester.test("N=4. all_forward. "+
				"p1 is_a Promoter. p2 is_a Promoter. g1 is_a Gene. g2 is_a Gene. "+
				"r1 is_a RBS. r2 is_a RBS. t1 is_a Terminator. t2 is_a Terminator. "+
				"contains Promoter. contains Gene. contains Terminator. contains RBS."+
				"Promoter ALWAYS_NEXTTO RBS. RBS ALWAYS_NEXTTO Gene. Gene ALWAYS_NEXTTO Terminator.");
	}
	
	private static void testEqualsTypes() {
		MiniEugeneTester.test("N=2. all_forward. p1 is_a Promoter. p2 is_a Promoter. g1 is_a Gene. g2 is_a Gene. "+
				"[0] EQUALS Promoter. [1] EQUALS Gene.");
		MiniEugeneTester.test("N=2. all_forward. p1 is_a Promoter. p2 is_a Promoter. g1 is_a Gene. g2 is_a Gene. "+
				"[0] EQUALS Promoter \\/ [0] EQUALS Gene. [1] EQUALS Gene \\/ [1] EQUALS Promoter.");
	}

	private static void testEqualsIndices() {
		MiniEugeneTester.test("N=4. all_forward. p1 is_a Promoter. p2 is_a Promoter. g1 is_a Gene. g2 is_a Gene. "+
				"contains Promoter. contains Gene. [0] EQUALS [1]. [1] EQUALS [2].");
	}

	private static void testPairingOnComponents() {
	}
}
