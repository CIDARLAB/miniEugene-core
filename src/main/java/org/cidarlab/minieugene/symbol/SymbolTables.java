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

package org.cidarlab.minieugene.symbol;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.cidarlab.minieugene.act.ACT;
import org.cidarlab.minieugene.constants.PredefinedTypes;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.dom.ComponentType;
import org.cidarlab.minieugene.dom.Identified;
import org.cidarlab.minieugene.predicates.Constraint;
import org.cidarlab.minieugene.predicates.interaction.Interaction;


/**
 * 
 * @author Ernst Oberortner
 */
public class SymbolTables {

	/*
	 * set of components
	 */
	private Map<String, Component> components;	
	private Map<String, ComponentType> types;
	
	private Map<String, Set<Component>> typedComponents;
	private Map<Integer, Identified> identifiers;
	
	private Set<Constraint> constraints;
	private Set<Interaction> interactions;
	

	/*
	 * the Abstract Composition Tree
	 */
	private ACT act;
	
	/*
	 * N ... the length of the device
	 *       -1 ... random length
	 *       >0 ... length 
	 */
	public SymbolTables() {
		/*
		 * the symbols HashMap keeps track of all defined 
		 * symbols
		 * the predefined ones are: p, r, g, t
		 */
		this.components = new HashMap<String, Component>();
	
		/*
		 * 
		 */
		this.types = new HashMap<String, ComponentType>();
		
		/*
		 * a helper map for a more efficient search of
		 * components given a specific type
		 */
		this.typedComponents = new HashMap<String, Set<Component>>();
		
		/*
		 * another helper map for a more efficient search
		 */
		this.identifiers = new HashMap<Integer, Identified>();
		
		/*
		 * all specified constraints
		 */
		this.constraints = new HashSet<Constraint>();
		
		/*
		 * facts on regulatory interactions
		 */
		this.interactions = new HashSet<Interaction>();
		
		/*
		 * Abstract Composition Tree
		 */
		this.act = new ACT();
		
	}
	
	/*---------------------------------------------------
	 * Methods for component types 
	 *---------------------------------------------------*/
	public ComponentType putType(String t) {
		
		if(!this.types.containsKey(t)) {
			this.types.put(t, new ComponentType(t));
		}
		
		if(!this.typedComponents.containsKey(this.getType(t).toString())) {
			this.typedComponents.put(this.getType(t).toString(), new HashSet<Component>());
		}
		
		if(!this.identifiers.containsKey(this.getType(t).getId())) {
			this.identifiers.put(this.getType(t).getId(), this.getType(t));
		}
		
		return this.getType(t);
	}
	
	public boolean containsType(String t) {
		return this.types.containsKey(t);
	}
	
	public ComponentType getType(String t) {
		return this.types.get(t);
	}
	
	public int getTypesSize() {		
		return this.types.keySet().size();
	}
	
	/*---------------------------------------------------
	 * Methods for components 
	 *---------------------------------------------------*/
	
	/* 
	 * put a symbol into the symbol tables
	 */
	public Component put(String s) {
		if(this.components.containsKey(s)) {
			return this.components.get(s);
		}

		/*
		 * in this case, the user has NOT specified what s is
		 * i.e. we assume that s is a component and we're trying to figure out its type 
		 */
		
		return this.put(s, this.findTypeOf(s));		
	}
	
	/**
	 * The printFacts/0 method outputs all specified facts 
	 * in the console (System.out).
	 */
	public void printFacts() {
		if(null!=this.components && !this.components.isEmpty()) {
			for(String s: this.components.keySet()) {
				System.out.println(this.components.get(s));
			}
		}
	}
	
	private ComponentType findTypeOf(String s) {
		
		if(this.types.containsKey(s)) {
			return this.types.get(s);
		}
		
		return this.putType(PredefinedTypes.toPartType(s).toString());
	}
	
	private boolean containsComponent(String s) {
		return this.components.containsKey(s);
	}
	
	/**
	 * The put/2 method takes as input 
	 * - the name of the component
	 * - the type of the component
	 * 
	 * @param s
	 * @param t
	 */
	public Component put(String s, ComponentType ct) {
		/*
		 * then, we check if the component exists
		 */
		Component c = null;
		if(this.containsComponent(s)) {

			// if the component exists, then
			// we need to update its type
			Identified id = this.get(this.getId(s));
			if(null == id || !(id instanceof Component)) {
				c = new Component(s, ct);
			} else {
				c = (Component)id;
				c.setType(ct);
			}
			
		} else {
			
			// if the component does not exists,
			// then we create a new component with 
			// the specified type
			c = new Component(s, ct);
			
		}

		// then, we put the component into the 
		// symbols map
		this.put(c);
		
		// we also keep track of the components and their 
		// types
		if(!this.typedComponents.containsKey(ct.toString())) {
			this.typedComponents.put(ct.toString(), new HashSet<Component>());
		}
		this.typedComponents.get(ct.toString()).add(c);
		
		// lastly, we return a reference to the 
		// create Component object
		return c;
	}
		
	private void put(Component c) {
		
		/*
		 * first, also need to check if the type of the component
		 * exists already
		 */
		
		if(!this.types.containsKey(c.getType())) {
			this.putType(c.getType().getName());
		}
		
		if(!this.components.containsKey(c.getName())) {
			this.components.put(c.getName(), c);
		}
		
		if(!this.identifiers.containsKey(c.getId())) {
			this.identifiers.put(c.getId(), c);
		}
	}
	

	public void put(Constraint p) {
		this.constraints.add(p);
	}
	
	public Set<Constraint> getConstraints() {
		return this.constraints;
	}
	
	public boolean containsId(int i) {
		return this.components.containsKey(i);
	}
	
	public boolean contains(String s) {
		return this.components.containsKey(s) || this.types.containsKey(s);
	}
	
	public Identified get(int id) {
		return this.identifiers.get(id);
	}
	
	public Identified get(String s) {
		/*
		 * first, we check if s is a component
		 */
		if(this.components.containsKey(s)) {
			return this.components.get(s);
			
		/*
		 * if s is not a component, then we 
		 * check if it is a type	
		 */
		} else if(this.types.containsKey(s)) {
			return this.types.get(s);
		}
		
		/*
		 * if s is not defined yet, then
		 * we define it here as a component 
		 */
		this.put(s);

		return this.get(s);
	}
	
	public int[] getIds() {
		Integer[] ids = new Integer[this.components.keySet().size()];
		this.components.keySet().toArray(ids);
		return ArrayUtils.toPrimitive(ids);
	}
	
	public Component[] getComponents() {
		Component[] s = new Component[this.components.keySet().size()];
		return this.components.values().toArray(s);
	}
	
	public Set<Component> getComponents(ComponentType ct) {
		return this.typedComponents.get(ct.toString());
	}
	
	public int getId(String s) {
		
		/*
		 * get b's id from the symbol
		 */
		if(this.components.containsKey(s)) {
			return this.components.get(s).getId();
		} else if(this.types.containsKey(s)) {
			return this.types.get(s).getId();
		}
		
		return -1;
	}
	
	public void print() {
		
		for(String ct : this.typedComponents.keySet()) {
			System.out.println("Type "+ct+" -> "+this.typedComponents.get(ct));
		}
		
		System.out.println("**** TYPES ****");
		for(String s : this.types.keySet()) {
			System.out.println(s+" -> "+this.types.get(s));
		}
		System.out.println("**** COMPONENTS ****");
		for(String s : this.components.keySet()) {
			System.out.println(s+" -> "+this.components.get(s));
		}
		System.out.println("**** PREDICATES ****");
		Iterator<Constraint> it = this.constraints.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
//	/*
//	 * methods to change the directionality of all symbols 
//	 * or of a specific symbol
//	 */
//	public void allReverse() {
//		for(int i : this.symbols.keySet()) {
//			this.symbols.get(i).setForward(false);
//		}
//	}
//	public void reverse(int a) {
//		this.symbols.get(a).setForward(false);
//	}
//	public void allForward() {
//		for(int i : this.symbols.keySet()) {
//			this.symbols.get(i).setForward(true);
//		}
//	}
//	public void forward(int a) {
//		this.symbols.get(a).setForward(true);
//	}

	
	/*
	 * methods to store information on regulatory interactions
	 */
	public void putInteraction(Interaction ip) {	
		if(!this.interactions.contains(ip)) {
			this.interactions.add(ip);
		}		
	}
	
	/**
	 * 
	 * @return the set of regulatory interactions
	 */
	public Set<Interaction> getInteractions() {
		return this.interactions;
	}
	

	/**
	 * 
	 * @return the Abstract Composition Tree
	 */
	public ACT getACT() {
		return this.act;
	}
	
	/*
	 * clear ... to clear all hashtables
	 */
	public void clear() {
		if(null != this.interactions) {
			this.interactions.clear();
		}
		
		if(null != this.constraints) {
			this.constraints.clear();
		}
		
		if(null != this.components) {
			this.components.clear();
		}
		
		if(null != this.typedComponents) {
			this.typedComponents.clear();
		}
		
		if(null != this.types) {
			this.types.clear();
		}
	}
}
