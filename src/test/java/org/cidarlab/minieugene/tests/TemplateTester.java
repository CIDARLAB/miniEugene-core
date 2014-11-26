package org.cidarlab.minieugene.tests;


public class TemplateTester {
	
	public static void test() {
		testTemplatesOnTypes();
		testTemplatesOnComponents();
	}
	
	private static void testTemplatesOnTypes() {
		MiniEugeneTester.test("N=2.p8 IS_A Promoter.p9 IS_A Promoter.r9 IS_A Repressor.r8 IS_A Repressor.TEMPLATE [Promoter|Repressor], [Promoter|Repressor]. [0] equals [1].");
	}
	
	private static void testTemplatesOnComponents() {
		MiniEugeneTester.test("N=2.p8 IS_A Promoter.p9 IS_A Promoter.r9 IS_A Repressor.r8 IS_A Repressor.TEMPLATE [r9|r8|p8|p9], [p8|p9|r9|r8]. [0] equals [1].");
	}
}
