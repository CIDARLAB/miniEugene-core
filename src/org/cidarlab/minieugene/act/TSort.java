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
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.ACTException;

public class TSort {
	/** <p>
	 * <b>Topological sort</b> solves a problem of - finding a linear ordering
	 * of the vertices of <i>V</i> such that for each edge <i>(i, j) $\in$ E</i>,
	 * vertex <i>i</i> is to the left of vertex <i>j</i>. (Skiena 2008, p. 481)
	 * </p>
	 * 
	 * <p>
	 * Method is derived from of <a
	 * href="http://en.wikipedia.org/wiki/Topological_sort#Algorithms" > Kahn's
	 * pseudo code</a> and traverses over vertices as they are returned by input
	 * map. Leaf nodes can have null or empty values. This method assumes, that
	 * input is valid DAG, so if cyclic dependency is detected, error is thrown.
	 * tSortFix is a fix to remove self dependencies and add missing leaf nodes.
	 * </p>
	 * 
	 * <pre>
	 * // For input with elements:
	 * { F1=[F2, F3, F4], F10=[F7, F4], F11=[F4], F2=[F3, F8, F4], F3=[F6], 
	 *   F4=null, F5=[F6, F4], F6=[F7, F8, F4], F7=[F4], F8=[F4], F9=[F4]}
	 *   
	 * // Output based on input map type: 
	 * HashMap: [F4, F11, F8, F9, F7, F10, F6, F5, F3, F2, F1]
	 * TreeMap: [F4, F11, F7, F8, F9, F10, F6, F3, F5, F2, F1]
	 * </pre>
	 * 
	 * @param g
	 *            <a href="http://en.wikipedia.org/wiki/Directed_acyclic_graph"
	 *            > Directed Acyclic Graph</a>, where vertices are stored as
	 *            {@link java.util.HashMap HashMap} elements.
	 * 
	 * @return Linear ordering of input nodes.
	 * @throws Exception
	 *             Thrown when cyclic dependency is detected, error message also
	 *             contains elements in cycle.
	 * 
	 */
	public static List<Component> tSort(java.util.Map<Component, ArrayList<Component>> g)
	        throws ACTException
	/**
	 * @param L
	 *            Answer.
	 * @param S
	 *            Not visited leaf vertices.
	 * @param V
	 *            Visited vertices.
	 * @param P
	 *            Defined vertices.
	 * @param n
	 *            Current element.
	 */
	{
	    List<Component> L = new ArrayList<Component>(g.size());
	    Queue<Component> S = new LinkedBlockingDeque<Component>();
	    Set<Component> V = new HashSet<Component>(), 
	    P = new java.util.HashSet<Component>();
	    P.addAll(g.keySet());
	    Component n;

	    // find the leaf nodes
	    for (Component t : P) {
	        if (g.get(t) == null || g.get(t).isEmpty()) {
	            S.add(t); 
	        }
	    }
	    
	    // Visit all leaf nodes. Build result from vertices, that are visited
	    // for the first time. Add vertices to not visited leaf vertices S, if
	    // it contains current element n an all of it's values are visited.
	    while (!S.isEmpty()) {
	        if (V.add(n = S.poll())) {
	            L.add(n);
	        }
	        for (Component t : g.keySet()) {
	            if (g.get(t) != null && !g.get(t).isEmpty() && !V.contains(t) && V.containsAll(g.get(t))) {
	                S.add(t);
	            }
	        }
	    }

	    
	    // if there are no cycles, then 
	    // return the result
	    if (L.containsAll(P))
	        return L;

	    // TODO:
	    // if there are cycles, then 
	    // detect what components cause the cycle
	    List<Component> cycles = new ArrayList<Component>();
	    for (Component t : P) {
	        if (!L.contains(t)) {
	            cycles.add(t);
	        }
	    }
	    throw new ACTException(cycles);
	}

	/**
	 * Method removes self dependencies and adds missing leaf nodes.
	 * 
	 * @param g
	 *            <a href="http://en.wikipedia.org/wiki/Directed_acyclic_graph"
	 *            > Directed Acyclic Graph</a>, where vertices are stored as
	 *            {@link java.util.HashMap HashMap} elements.
	 */
	public static <T> void tSortFix(java.util.Map<T, ArrayList<T>> g) {
	    java.util.ArrayList<T> tmp;
	    java.util.HashSet<T> P = new java.util.HashSet<T>();
	    P.addAll(g.keySet());

	    for (T t : P)
	        if (g.get(t) != null || !g.get(t).isEmpty()) {
	            (tmp = g.get(t)).remove(t);
	            for (T m : tmp)
	                if (!P.contains(m))
	                    g.put(m, new ArrayList<T>(0));
	        }
	}
}
