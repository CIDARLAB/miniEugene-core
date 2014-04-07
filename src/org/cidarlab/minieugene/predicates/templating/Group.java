package org.cidarlab.minieugene.predicates.templating;

import org.apache.commons.lang3.ArrayUtils;
import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.Predicate;
import org.cidarlab.minieugene.solver.jacop.Variables;
import org.cidarlab.minieugene.dom.Component;

import JaCoP.constraints.And;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group 
	extends TemplatingPredicate {
		
	public Group() {
		super(null, new ArrayList<List<Component>>(), false);
	}
	
	public Group(String name) {
		super(name, new ArrayList<List<Component>>(), false);
	}
	
	public Group(String name, List<List<Component>> components) {
		super(name, components, false);
	}
	
	@Override
	public String getOperator() {
		return RuleOperator.GROUP.toString();
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
		
//		PrimitiveConstraint[] pc = null;
//		for(int i=0; i + this.getComponents().size() <= maxN; i++) {
//			
//			// get the first component
//			Component c = this.getComponents().get(0);
//			PrimitiveConstraint[] pcTemplate = new PrimitiveConstraint[this.getComponents().size() - 1];
//			for(int j=1; j<this.getComponents().size(); j++) {
//				Component cj = this.getComponents().get(j);
//				pcTemplate[j-1] = new XeqC(variables[Variables.PART][j+i], cj.getId());
//			}
//			
//			if(pc == null) {
//				pc = new PrimitiveConstraint[1];
//				pc[0] =	new And(
//							new XeqC(variables[Variables.PART][i], c.getId()),
//							new And(pcTemplate)); 
//			} else {
//				pc = ArrayUtils.add(pc, 
//					new And(
//						new XeqC(variables[Variables.PART][i], c.getId()),
//						new And(pcTemplate)));
//			}
//		}
//
////		return new And(pc);
//		return new Or(pc);
		
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		// NEGATED?
		if(this.isNegated()) {
			sb.append("NOT ");
		}
		
		// NAME
		if(null != this.getName() && !this.getName().isEmpty()) {
			sb.append(this.getName()).append(" ");
		}
		
		// OPERATOR
		sb.append(this.getOperator()).append(" ");

		// SELECTION
		for(int i=0; i<this.getComponents().size(); i++) {
			
			// SELECTION
			sb.append("[");
			List<Component> lst_selection = this.getComponents().get(i);
			for(int j=0; j<lst_selection.size(); j++) {	
				sb.append(lst_selection.get(j).getName());
				
				if(j < lst_selection.size() - 1) {
					sb.append("|");
				}
			}
					
			sb.append("]");
			
			if(i<this.getComponents().size()-1) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}
}
