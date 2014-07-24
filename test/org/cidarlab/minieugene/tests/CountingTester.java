package org.cidarlab.minieugene.tests;


public class CountingTester {
	
	public static void test() {
		testCountingOnTypes();
		testCountingOnComponents();
	}
	
	private static void testCountingOnTypes() {
		testMoreThanTypes();
		testContainsTypes();
		testExactlyTypes();
		testSameCountTypes();
		testThenTypes();
		testWithTypes();
	}
	
	private static void testMoreThanTypes() {
		// growing N
		MiniEugeneTester.test("N=1. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter MORETHAN 0."); // ODD N -> NO SOLUTION
		MiniEugeneTester.test("N=2. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter MORETHAN 1.");
		MiniEugeneTester.test("N=3. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter MORETHAN 2."); // ODD N -> NO SOLUTION
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter MORETHAN 3.");

		// growing Parts
		MiniEugeneTester.test("N=4. p1 is_a Promoter. g is_a Gene. Promoter MORETHAN 3.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter MORETHAN 3.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. p3 is_a Promoter. g is_a Gene. Promoter MORETHAN 3.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. p3 is_a Promoter. p4 is_a Promoter. g is_a Gene. Promoter MORETHAN 3.");
	}
	
	private static void testContainsTypes() {
		// growing N
		MiniEugeneTester.test("N=1. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. CONTAINS Promoter. NOT CONTAINS Gene.");
		MiniEugeneTester.test("N=2. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. CONTAINS Promoter. NOT CONTAINS Gene.");
		MiniEugeneTester.test("N=3. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. CONTAINS Promoter. NOT CONTAINS Gene.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. CONTAINS Promoter. NOT CONTAINS Gene.");
		

		MiniEugeneTester.test("N=2. p is_a Promoter. g is_a Gene. contains Promoter. contains Gene.");
		MiniEugeneTester.test("N=2. p1 is_a Promoter. p2 is_a Promoter. g1 is_a Gene. g2 is_a Gene."+
				"contains Promoter. contains Gene.");
		
		StringBuilder sb = new StringBuilder();
		sb.append("N=4. ");
		
		StringBuilder sbProm = new StringBuilder();
		StringBuilder sbRBS = new StringBuilder();
		StringBuilder sbGene = new StringBuilder();
		StringBuilder sbTerm = new StringBuilder();
		for(int i=1; i<=4; i++) {
			sbProm.append("p").append(i).append(" is_a Promoter.");
			sbRBS.append("r").append(i).append(" is_a RBS.");
			sbGene.append("g").append(i).append(" is_a Gene.");
			sbTerm.append("t").append(i).append(" is_a Terminator.");
		}
		sb.append(sbProm).append(sbRBS).append(sbGene).append(sbTerm);
		sb.append("contains Promoter. contains RBS. contains Gene. contains Terminator.");
		MiniEugeneTester.test(sb.toString());
	}
	
	private static void testExactlyTypes() {
		// growing N
		MiniEugeneTester.test("N=1. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter EXACTLY 1.");
		MiniEugeneTester.test("N=2. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter EXACTLY 2.");
		MiniEugeneTester.test("N=3. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter EXACTLY 3.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter EXACTLY 4.");

		// growing Parts
		MiniEugeneTester.test("N=4. p1 is_a Promoter. g is_a Gene. Promoter EXACTLY 4.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter EXACTLY 4.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. p3 is_a Promoter. g is_a Gene. Promoter EXACTLY 4.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. p3 is_a Promoter. p4 is_a Promoter. g is_a Gene. Promoter EXACTLY 4.");
	}

	private static void testSameCountTypes() {
		// growing N
		MiniEugeneTester.test("N=1. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter SAME_COUNT Gene.");
		MiniEugeneTester.test("N=2. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter SAME_COUNT Gene.");
		MiniEugeneTester.test("N=3. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter SAME_COUNT Gene.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter SAME_COUNT Gene.");

		// growing Parts
		MiniEugeneTester.test("N=4. p1 is_a Promoter. g is_a Gene. Promoter SAME_COUNT Gene.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter SAME_COUNT Gene.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. p3 is_a Promoter. g is_a Gene. Promoter SAME_COUNT Gene.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. p3 is_a Promoter. p4 is_a Promoter. g is_a Gene. Promoter SAME_COUNT Gene.");
	}

	private static void testThenTypes() {
		// growing N
		MiniEugeneTester.test("N=1. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter THEN Gene.");
		MiniEugeneTester.test("N=2. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter THEN Gene.");
		MiniEugeneTester.test("N=3. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter THEN Gene.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter THEN Gene.");

		// growing Parts
		MiniEugeneTester.test("N=4. p1 is_a Promoter. g is_a Gene. Promoter THEN Gene.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter THEN Gene.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. p3 is_a Promoter. g is_a Gene. Promoter THEN Gene.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. p3 is_a Promoter. p4 is_a Promoter. g is_a Gene. Promoter THEN Gene.");
	}

	private static void testWithTypes() {
		// growing N
		MiniEugeneTester.test("N=1. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter WITH Gene.");
		MiniEugeneTester.test("N=2. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter WITH Gene.");
		MiniEugeneTester.test("N=3. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter WITH Gene.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter WITH Gene.");

		// growing Parts
		MiniEugeneTester.test("N=4. p1 is_a Promoter. g is_a Gene. Promoter WITH Gene.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. g is_a Gene. Promoter WITH Gene.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. p3 is_a Promoter. g is_a Gene. Promoter WITH Gene.");
		MiniEugeneTester.test("N=4. p1 is_a Promoter. p2 is_a Promoter. p3 is_a Promoter. p4 is_a Promoter. g is_a Gene. Promoter WITH Gene.");
	}
	
	private static void testCountingOnComponents() {
		testMoreThanComponents();
//		testContainsComponents();
//		testExactlyComponents();
//		testSameCountComponents();
//		testThenComponents();
//		testWithComponents();
	}
	
	private static void testMoreThanComponents() {
		// growing N
		MiniEugeneTester.test("N=1. p1 MORETHAN 0 \\/ p2 MORETHAN 0 \\/ g MORETHAN 0.");
		MiniEugeneTester.test("N=2. p1 MORETHAN 0 \\/ p2 MORETHAN 0 \\/ g MORETHAN 0.");
		MiniEugeneTester.test("N=3. p1 MORETHAN 0 \\/ p2 MORETHAN 0 \\/ g MORETHAN 0.");
		MiniEugeneTester.test("N=4. p1 MORETHAN 0 \\/ p2 MORETHAN 0 \\/ g MORETHAN 0.");

		// growing Parts
		MiniEugeneTester.test("N=4. p1 MORETHAN 0.");
		MiniEugeneTester.test("N=4. p1 MORETHAN 0 \\/ p2 MORETHAN 0.");
		MiniEugeneTester.test("N=4. p1 MORETHAN 0 \\/ p2 MORETHAN 0 \\/ g1 MORETHAN 0.");
		MiniEugeneTester.test("N=4. p1 MORETHAN 0 \\/ p2 MORETHAN 0 \\/ g1 MORETHAN 0 \\/ g2 MORETHAN 0.");
	}
}
