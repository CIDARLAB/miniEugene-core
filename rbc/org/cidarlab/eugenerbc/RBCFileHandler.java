package org.cidarlab.eugenerbc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.LogicalPredicate;

public class RBCFileHandler {
	
	public static void main(String[] args) {
		RBCDataStruct rbc = null;
		try {
			 rbc = getRBCDataStruct("tests/rbc/testfile.txt");
		} catch (FileNotFoundException | EugeneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(rbc.getCDSCount());
		} catch (EugeneRBCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static RBCDataStruct getRBCDataStruct(String filename) throws FileNotFoundException, EugeneException {
		return new RBCDataStruct(getLogicalPredicate(filename), getSize(filename), getLibrary(filename));
	}

	/* Returns the logical predicate generated from a specified MiniEugene file */
	private static LogicalPredicate getLogicalPredicate(String filename) throws FileNotFoundException, EugeneException {
		MiniEugene me = new MiniEugene();
		String script = me.buildScript(getSize(filename), getRules(filename));
		return me.parse(script);
	}
	
	/* Reads the first line of the file to get the design size */
	private static int getSize(String filename) throws FileNotFoundException, EugeneRBCException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String firstline = null;
		try {
			firstline = br.readLine();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(firstline.charAt(0) != 'N' || firstline.charAt(1) != '=') {
			throw new EugeneRBCException("First line does not set size");
		}
		return Integer.parseInt(firstline.substring(2));	
	}
	
	private static int getLibrary(String filename) throws FileNotFoundException, EugeneRBCException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String secondline = null;
		try {
			br.readLine();
			secondline = br.readLine();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(secondline.charAt(0) != 'L' || secondline.charAt(1) != '=') {
			throw new EugeneRBCException("Second line does not set size");
		}
		return Integer.parseInt(secondline.substring(2));
	}
	
	/* Reads the lines after the first line to get the rules in the file */
	private static String[] getRules(String filename) throws FileNotFoundException {
		List<String> rulelist = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		try {
			br.readLine(); // skip first line specifying size
			br.readLine(); // skip second line specifying library
			while ((line = br.readLine()) != null) {
				rulelist.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rulelist.toArray(new String[rulelist.size()]);
	}
}
