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
