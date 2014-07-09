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

package org.cidarlab.minieugene.constants;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.predicates.templating.Sequence;
import org.cidarlab.minieugene.predicates.templating.Template;
import org.cidarlab.minieugene.predicates.templating.TemplatingPredicate;
import org.cidarlab.minieugene.symbol.SymbolTables;

public enum TemplateType {
	TEMPLATE {
		@Override
		public TemplatingPredicate createPredicate(
				SymbolTables symbols, String name, List<List<String>> ids) {

			Template t = new Template(name);
			for(List<String> selection : ids) {
				
				List<Component> components = new ArrayList<Component>();
				for(String id : selection) {
					components.add(
							symbols.get(symbols.put(id)));
				}
				t.getComponents().add(components);
			}
			
			return t;
		}
	}, 
	PATTERN {
		@Override
		public TemplatingPredicate createPredicate(
				SymbolTables symbols, String name, List<List<String>> ids) {
			// TODO Auto-generated method stub
			return null;
		}
	}, 
	SEQUENCE {
		@Override
		public TemplatingPredicate createPredicate(
				SymbolTables symbols, String name, List<List<String>> ids) {

			Sequence s = new Sequence(name);
			for(List<String> selection : ids) {
				
				List<Component> components = new ArrayList<Component>();
				for(String id : selection) {
					components.add(
							symbols.get(symbols.put(id)));
				}
				s.getComponents().add(components);
			}
			
			return s;
		}
	}, 
	GROUP {
		@Override
		public TemplatingPredicate createPredicate(
				SymbolTables symbols, String name, List<List<String>> ids) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	public abstract TemplatingPredicate createPredicate(
			SymbolTables symbols, String name, List<List<String>> ids);
}
