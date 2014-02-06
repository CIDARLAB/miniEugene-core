package org.cidarlab.minieugene.parser;

import org.cidarlab.minieugene.predicates.Predicate;

public interface MiniEugeneParser {
	public Predicate[] parse(String[] input);
}
