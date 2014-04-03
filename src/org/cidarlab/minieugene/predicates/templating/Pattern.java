package org.cidarlab.minieugene.predicates.templating;

import org.apache.commons.lang3.ArrayUtils;
import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.Predicate;
import org.cidarlab.minieugene.solver.jacop.Variables;
import org.cidarlab.minieugene.dom.Component;

import JaCoP.constraints.And;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pattern 
	extends Predicate {

	private String name;
	private List<Component> components;
	private boolean negated;
	
	public Pattern() {
		this.name = UUID.randomUUID().toString();
		this.components = new ArrayList<Component>();
		this.negated = false;
	}

	public Pattern(String name) {
		this.name = name;
		this.components = new ArrayList<Component>();
		this.negated = false;
	}
	
	public Pattern(String name, List<Component> components) {

		if(null != name && !name.isEmpty()) {
			this.name = name;
		} else {
			this.name = UUID.randomUUID().toString();
		}
		
		this.components = components;
		this.negated = false;
	}
	
	public void setNegated() {
		this.negated = true;
	}
	
	public boolean isNegated() {
		return this.negated;
	}
	
	public List<Component> getComponents() {
		return this.components;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.PATTERN.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables)
			throws EugeneException {
		
		if(this.isNegated()) {
			return this.toJaCoPNot(store, variables);
		}
		
		int maxN = variables[Variables.PART].length;
		
//		if(maxN < this.getComponents().size()) {
//			throw new EugeneException("I cannot impose the strict template!");
//		} else if(maxN % this.getComponents().size() != 0) {
//			throw new EugeneException(
//					"The max. length "+maxN+" is not a multiple of the template size ("+this.getComponents().size()+")");
//		}
		
		return createTemplate(variables, maxN);
	}
	
	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		int maxN = variables[Variables.PART].length;
		
		if(maxN < this.getComponents().size()) {
			throw new EugeneException("I cannot impose the strict template!");
		} else if(maxN % this.getComponents().size() != 0) {
			throw new EugeneException(
					"The max. length "+maxN+" is not a multiple of the template size ("+this.getComponents().size()+")");
		}
		
		return new Not(createTemplate(variables, maxN));
	}
	
	private PrimitiveConstraint createTemplate(IntVar[][] variables, int maxN) {
		
		PrimitiveConstraint[] pc = null;
		for(int i=0; i + this.getComponents().size() <= maxN; i++) {
			
			// get the first component
			Component c = this.getComponents().get(0);
			PrimitiveConstraint[] pcTemplate = new PrimitiveConstraint[this.getComponents().size() - 1];
			for(int j=1; j<this.getComponents().size(); j++) {
				Component cj = this.getComponents().get(j);
				pcTemplate[j-1] = new XeqC(variables[Variables.PART][j+i], cj.getId());
			}
			
			if(pc == null) {
				pc = new PrimitiveConstraint[1];
				pc[0] =	new And(
							new XeqC(variables[Variables.PART][i], c.getId()),
							new And(pcTemplate)); 
			} else {
				pc = ArrayUtils.add(pc, 
					new And(
						new XeqC(variables[Variables.PART][i], c.getId()),
						new And(pcTemplate)));
			}
		}

//		return new And(pc);
		return new Or(pc);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(null != this.getName() || !this.getName().isEmpty()) {
			sb.append(this.getName()).append(" ");
		}
		sb.append(this.getOperator()).append(" ");
		for(int i=0; i<this.getComponents().size(); i++) {
			sb.append(this.getComponents().get(i).getName());
			if(i<this.getComponents().size()-1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
}
