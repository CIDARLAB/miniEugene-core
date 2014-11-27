package org.cidarlab.minieugene;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.cidarlab.minieugene.dom.Component;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SequenceTest {

	@BeforeClass
	public static void setUpBeforeClass() 
			throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() 
			throws Exception {
	}

	@Before
	public void setUp() 
			throws Exception {
	}

	@After
	public void tearDown() 
			throws Exception {
	}

	@Test
	public void testNamedSequence1() {
		String script = "N=1. s: SEQUENCE p.";
		
		MiniEugene me = new MiniEugene();
		try {
			me.solve(script);
			List<Component[]> los = me.getSolutions();
			
			assertTrue(los.size() == 2);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNamedSequence2() {
		int N = 2;
		
		String script = "N="+N+". s: SEQUENCE p, r.";
		
		MiniEugene me = new MiniEugene();
		try {
			me.solve(script);
			List<Component[]> los = me.getSolutions();

			assertTrue(los.size() == 2 * 2);
			                             // ORIENTATION
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNamedSequenceN() {
		int N = 10;
		
		int NR_SOLUTIONS = 1;
		
		String script = "N="+N+". s: SEQUENCE ";
		for(int i=1; i<=N; i++) {
			script += "p"+i;
			if(i < N) {
				script += ", ";
			}
		
			NR_SOLUTIONS *= 2;
			  // at every position, a part can appear
			  // in two positions (forward or reverse) 
			
		}
		script += ".";
		
		MiniEugene me = new MiniEugene();
		try {
			me.solve(script);
			List<Component[]> los = me.getSolutions();

			assertTrue(los.size() == NR_SOLUTIONS);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNamedSequenceN_selection() {
		int N = 10;
		int M = 2;
		
		int NR_SOLUTIONS = 1;
		
		String script = "N="+N+". s: SEQUENCE ";
		for(int i=1; i<=N; i++) {
			script += "[";
			for(int j=1; j<=M; j++) {
				script += "p"+i+""+j;
				
				if(j < M) {
					script += "|";
				}
			}
			script += "]";
			
			if(i < N) {
				script += ", ";
			}
		
			NR_SOLUTIONS *= M * 2;
			  // at every position, a part can appear
			  // in two positions (forward or reverse) 
			
		}
		script += ".";
		
		MiniEugene me = new MiniEugene();
		try {
			me.solve(script);
			List<Component[]> los = me.getSolutions();
			
			assertTrue(los.size() == NR_SOLUTIONS);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNamedSequenceN_selection_forward() {
		int N = 10;
		int M = 3;
		
		int NR_SOLUTIONS = 1;
		
		String script = "N="+N+". all_forward. s: SEQUENCE ";
		for(int i=1; i<=N; i++) {
			script += "[";
			for(int j=1; j<=M; j++) {
				script += "p"+i+""+j;
				
				if(j < M) {
					script += "|";
				}
			}
			script += "]";
			
			if(i < N) {
				script += ", ";
			}
		
			NR_SOLUTIONS *= M;
			// since all parts must be forward 
			// oriented, we don't consider orientation
			
		}
		script += ".";
		
		MiniEugene me = new MiniEugene();
		try {
			me.solve(script);
			List<Component[]> los = me.getSolutions();
			
			assertTrue(los.size() == NR_SOLUTIONS);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMultipleSequences() {
		
		double NR_SOLUTIONS = 2 * Math.pow(2, 2);
		String script = "N=2. s1: SEQUENCE p1. SEQUENCE p2.";
		
		MiniEugene me = new MiniEugene();
		try {
			me.solve(script);
			List<Component[]> los = me.getSolutions();
			
			for(int i=0; i<los.size(); i++) {
				System.out.println(ArrayUtils.toString(los.get(i)));
			}
			assertTrue(los.size() == NR_SOLUTIONS);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}	
}
