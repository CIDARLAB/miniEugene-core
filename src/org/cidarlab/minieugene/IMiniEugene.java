package org.cidarlab.minieugene;

import java.util.List;

import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.symbol.Symbol;

public interface IMiniEugene {
	
	public void solve(String[] rules, int N)
			throws EugeneException;

	public void solve(String[] rules, int N, int NR_OF_SOLUTIONS)
			throws EugeneException;
	
	public MiniEugeneStatistics getStatistics();
	public List<Symbol[]> getSolutions();
}
