/*
Copyright (c) 2014 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
 */

package org.cidarlab.minieugene;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.interaction.Interaction;

/**
 * 
 * 
 * @author Ernst Oberortner
 */
public interface IMiniEugene {
	
	/**
	 * 
	 * @param rules
	 * @param N
	 * @throws EugeneException
	 */
	public void solve(String[] rules, int N)
			throws EugeneException;

	/**
	 * 
	 * @param rules
	 * @param N
	 * @param NR_OF_SOLUTIONS
	 * @throws EugeneException
	 */
	public void solve(String[] rules, int N, int NR_OF_SOLUTIONS)
			throws EugeneException;
	
	/**
	 * solve/1 finds NR_OF_SOLUTIONS solutions based on the given
	 * miniEugene script.
	 * 
	 * @param script
	 * @param NR_OF_SOLUTIONS
	 * @throws EugeneException
	 */
	public void solve(String script, int NR_OF_SOLUTIONS)
			throws EugeneException;
	
	/**
	 * solve/1 finds all solutions based on the given
	 * miniEugene script.
	 *  
	 * @param script The miniEugene script.
	 * @throws EugeneException 
	 */
	public void solve(String script) 
			throws EugeneException;
	
	public MiniEugeneStatistics getStatistics();
	public List<Component[]> getSolutions();
	public Set<Interaction> getInteractions();

	/**
	 * 
	 * @return the Abstract Composition Tree (ACT)
	 */
	public URI visualizeACT()
			throws EugeneException;
}
