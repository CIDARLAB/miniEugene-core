package org.cidarlab.minieugene;

import java.util.Arrays;
import java.util.List;

import org.cidarlab.minieugene.dom.Component;

public class RandomizedSearch {

	public static void main(String[] args) 
			throws Exception {
		MiniEugene me = new MiniEugene();		

		
		System.out.println("*** 1st Run ***");
		me.solve("N=2. contains p1. contains r1.");
		printSolutions(me.getSolutions());
		
		System.out.println("*** 2nd Run ***");
		me.solve("N=3. contains p2. contains r2. contains c2.");
		printSolutions(me.getSolutions());

		System.out.println("*** 3rd Run ***");
		me.solve("N=4. contains p3. contains r3. contains c3. contains t3.");
		printSolutions(me.getSolutions());

		System.out.println("*** 4th Run ***");
		me.solve("N=2. contains p4. contains t4.");
		printSolutions(me.getSolutions());

	}
	
	private static void printSolutions(List<Component[]> solutions) {
		if(null != solutions && !solutions.isEmpty()) {
			for(Component[] solution : solutions) {
				System.out.println(Arrays.toString(solution));
			}
		} else {
			System.err.println("no solution found!");
		}
	}
}
