package org.cidarlab.minieugene.unittests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.dom.Component;
import org.junit.Test;

/**
 * 
 * @author Ernst Oberortner
 */
public class NextToTest {

	@Test
	public void test_AnexttoB_N2() {
		String script = "N=2.A nextto B.all_forward.";
		
		try {
			
			MiniEugene me = new MiniEugene();
			
			me.solve(script);
			
			List<Component[]> solutions = me.getSolutions();
			assertTrue(null != solutions);
			assertTrue(solutions.size() == 4);
			// A, A
			// A, B
			// B, A
			// B, B

		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);	// no exception allowed
		}
	}
	
	@Test
	public void test_AnexttoB_N3() {
		String script = "N=3.A nextto B.all_forward.";
		
		try {
			
			MiniEugene me = new MiniEugene();
			
			me.solve(script);
			
			List<Component[]> solutions = me.getSolutions();
			assertTrue(null != solutions);
			
			/*
			 * SOLUTIONS:
			 * A A A
			 * A A B  // WRONG
			 * A B A
			 * A B B  // WRONG
			 * 
			 * B A A  // WRONG
			 * B A B
			 * B B A  // WRONG
			 * B B B
			 */
			assertTrue(solutions.size() == 4);

		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);	// no exception allowed
		}
	}

	@Test
	public void test_NOT_AnexttoB_N2() {
		String script = "N=2.NOT A nextto B.all_forward.";
		
		try {
			
			MiniEugene me = new MiniEugene();
			
			me.solve(script);
			
			List<Component[]> solutions = me.getSolutions();
			assertTrue(null != solutions);
			assertTrue(solutions.size() == 2);
			// A, A
			// B, B

		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);	// no exception allowed
		}
	}

	@Test
	public void testDemoDesign() {
		String script = "N=5." +
				"TEMPLATE [p1], [A | B | C], [p2], [A | B | C], [p4]."+
				"FORWARD [0]." +
				"FORWARD [1]." +
				"FORWARD [2]." +
				"FORWARD [3]." +
				"FORWARD [4]." +
				"NOT A MORETHAN 1." +
				"NOT B MORETHAN 1." +
				"NOT C MORETHAN 1." +
				"NOT A NEXTTO p1.";
		try {
			
			MiniEugene me = new MiniEugene();
			
			me.solve(script);
			
			List<Component[]> solutions = me.getSolutions();
			assertTrue(null != solutions);
			
			/*
			 * expected solutions
			 * p1,B,p2,A,p4
			 * p1,C,p2,A,p4
			 * p1,B,p2,C,p4
			 * p1,C,p2,B,p4
			 */
			assertTrue(solutions.size() == 4);
			
			// TODO: 
			// generate the set of expected solutions manually
			// and compare it against the enumerated solutions
			
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);	// no exception allowed
		}
	}

}
