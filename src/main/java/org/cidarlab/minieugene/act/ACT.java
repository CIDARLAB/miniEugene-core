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

package org.cidarlab.minieugene.act;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cidarlab.minieugene.dom.Identified;
import org.cidarlab.minieugene.exception.ACTException;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.LogicalOr;
import org.cidarlab.minieugene.predicates.Constraint;
import org.cidarlab.minieugene.predicates.counting.BinaryContains;
import org.cidarlab.minieugene.predicates.counting.Contains;
import org.cidarlab.minieugene.predicates.counting.CountingConstraint;

public class ACT {

	/**
	 * the DAG
	 * 
	 * key   ... node
	 * value ... edges to the node's successors 
	 */
	private Map<Identified, ArrayList<Identified>> dg;
	
	/**
	 * cycles contains a list of components 
	 * that cause a cycle
	 */
	private List<Identified> cycles;
	
	public ACT() {
		//this.root = null;
		this.dg = new HashMap<Identified, ArrayList<Identified>>();
	}
	
	public void constructACT(List<Constraint> predicates) 
			throws MiniEugeneException {
		
		// first, we build a list of all
		// binary contains constraints
		List<CountingConstraint> lst = new ArrayList<CountingConstraint>();
		for(Constraint p : predicates) {
			if(p instanceof BinaryContains || p instanceof Contains) {
				lst.add((CountingConstraint)p);
			} else if(p instanceof LogicalOr) {
				for(Constraint subp : ((LogicalOr)p).getConstraints()) {
					if(subp instanceof BinaryContains || p instanceof Contains) {
						lst.add((CountingConstraint)subp);
					}
				}
			}
		}	

		// build the tree
		try {
			this.buildTree(lst);
			
			// sort the tree
			try {
				this.sortTree();
			} catch(ACTException e) {
				this.cycles = e.getCycles();
				throw new MiniEugeneException("Cyclic Design!");
			}
			
		} catch(MiniEugeneException ee) {
			throw new MiniEugeneException(ee.getMessage());
		}
		
	}
	
	private List<Identified> roots;
	
	public List<Identified> getRoots() {
		return this.roots;
	}
	
	private void buildTree(List<CountingConstraint> lst) 
			throws MiniEugeneException {
		
		this.roots = new ArrayList<Identified>();
		
		/*
		 * first, we build the DG
		 * based on the binary and unary CONTAINS predicates
		 */
		for(CountingConstraint cp : lst) {
			
			if(cp instanceof BinaryContains) {
				BinaryContains bc = (BinaryContains)cp;

				ArrayList<Identified> childs = new ArrayList<Identified>();
				if(this.dg.containsKey(bc.getA())) {
					childs = this.dg.get(bc.getA().getOperand());
					childs.add(bc.getB().getOperand());
				} else {
					childs.add(bc.getB().getOperand());	
					roots.add(bc.getA().getOperand());
				}
				
				this.dg.put(bc.getA().getOperand(), childs);
				
				if(!this.dg.containsKey(bc.getB())) {
					this.dg.put(bc.getB().getOperand(), new ArrayList<Identified>());
				}

				// B will not be a root anymore
				if(roots.contains(bc.getB())) {
					roots.remove(bc.getB());
				}
			} else if(cp instanceof Contains) {
				Contains c = (Contains)cp;
				if(!this.dg.containsKey(c.getA())) {
					this.dg.put(c.getA().getOperand(), new ArrayList<Identified>());
					roots.add(c.getA().getOperand());
				} else if(this.roots.contains(c.getA())) {
					this.roots.remove(c.getA());
				}
			}
		}
		

	}
	
	public List<Identified> sortTree() 
			throws ACTException {
		return TSort.tSort(this.dg);
	}
	
	public void printTree() {
		for(Identified c : this.dg.keySet()) {
			System.out.print(c.getName()+" -> {");
			for(int i=0; i<this.dg.get(c).size(); i++) {
				System.out.print(this.dg.get(c).get(i).getName()+", ");
			}
			System.out.println("}");
		}
	}

	/**
	 * 
	 * @return
	 */
	public Map<Identified, ArrayList<Identified>> getACT() {
		return this.dg;
	}
	
	/**
	 * 
	 * @return true if the ACT is acyclic
	 *         false otherwise
	 */
	public boolean isCyclic() {
		return this.cycles==null || this.cycles.isEmpty();
	}
	
	private static final String NEWLINE = System.getProperty("line.separator");	
	public String toGraphViz() 
			throws MiniEugeneException {
		
		// print the Tree
		StringBuilder sb = new StringBuilder();
		sb.append("digraph DESIGN {").append(NEWLINE);
		
		// first, let's check if there are any circles
		// in order to red color the involved nodes
		if(null != this.cycles) {
			sb.append("node [color=red];").append(NEWLINE);
			for(Identified c : this.cycles) {
				sb.append(c.getName()).append(";").append(NEWLINE);
			}
			sb.append("edge [color=red];").append(NEWLINE);
					
		}
		
		Iterator<Identified> it = this.dg.keySet().iterator();
		while(it.hasNext()) {
			Identified source = it.next();
			if(!this.dg.get(source).isEmpty()) {
				for(Identified child : this.dg.get(source)) {
					sb.append(source.getName()).append(" -> ");
					sb.append(child.getName()).append(";").append(NEWLINE);
				}
			} else {
				sb.append(source.getName()).append(";").append(NEWLINE);
			}
		}
		
		sb.append("}");

		return sb.toString();
	}
	
	
}
