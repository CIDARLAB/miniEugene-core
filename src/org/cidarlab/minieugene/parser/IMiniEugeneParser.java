package org.cidarlab.minieugene.parser;

import org.cidarlab.minieugene.predicates.Predicate;

public interface IMiniEugeneParser {
	public Predicate[] parse(String[] input);
}
