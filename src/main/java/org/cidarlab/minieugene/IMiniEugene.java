/*
 * Copyright (c) 2014, Boston University
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 *    
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *    
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.minieugene;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
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
	 * @throws MiniEugeneException
	 */
	public void solve(String[] rules, int N)
			throws MiniEugeneException;

	/**
	 * 
	 * @param rules
	 * @param N
	 * @param NR_OF_SOLUTIONS
	 * @throws MiniEugeneException
	 */
	public void solve(String[] rules, int N, int NR_OF_SOLUTIONS)
			throws MiniEugeneException;
	
	/**
	 * solve/1 finds NR_OF_SOLUTIONS solutions based on the given
	 * miniEugene script.
	 * 
	 * @param script
	 * @param NR_OF_SOLUTIONS
	 * @throws MiniEugeneException
	 */
	public void solve(String script, int NR_OF_SOLUTIONS)
			throws MiniEugeneException;
	
	/**
	 * solve/1 finds all solutions based on the given
	 * miniEugene script.
	 *  
	 * @param script The miniEugene script.
	 * @throws MiniEugeneException 
	 */
	public void solve(String script) 
			throws MiniEugeneException;
	
	public MiniEugeneStatistics getStatistics();
	public List<Component[]> getSolutions();
	public Set<Interaction> getInteractions();

	/**
	 * 
	 * @return the Abstract Composition Tree (ACT)
	 */
	public URI visualizeACT()
			throws MiniEugeneException;
}
