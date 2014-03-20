package org.cidarlab.minieugene.predicates.orientation;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.UnaryPredicate;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.IfThenElse;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.Reified;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XneqC;
import JaCoP.core.BooleanVar;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

/*
 * ALTERNATE a
 *
 * i in {1, ..., N-1}
 * for all i: orientation(i) != orientation(i+1)
 * 
 */
public class Alternate 
	extends UnaryPredicate 
	implements OrientationPredicate {

	public Alternate(Component rhs) {
		super(rhs);
	}

	@Override
	public String getOperator() {
		return RuleOperator.ALTERNATE.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getOperator());
		if(null != this.getA()) {
			sb.append(" ").append(this.getA().getName());
		}
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {
		
		if(null != this.getA()) {
			return alternate(store, variables, this.getA());
		} else {
			return alternate(store, variables);
		}

	}
	
	/**
	 * to alternate the orientation of the component c
	 * 
	 * @param store
	 * @param variables
	 * @param c
	 * @return
	 */
	private PrimitiveConstraint alternate(Store store, IntVar[][] variables, Component c) {
		
		int N = variables[Variables.ORIENTATION].length;
		
		BooleanVar[] isC = new BooleanVar[N];
		BooleanVar[] isForward = new BooleanVar[N];
		
		PrimitiveConstraint[] pc = new PrimitiveConstraint[N];
		for(int i=0; i<N; i++) {
			
			isC[i] = new BooleanVar(store, "is"+c.getName());
			isForward[i] = new BooleanVar(store, c.getName()+".isForward");
			
			/*
			 * IF c(i) /\ forward(i) THEN
			 * 		c.isForward(i) = TRUE
			 * ELSE
			 * 		c.isForward(i) = FALSE
			 */
			store.impose(
					new Reified(
							new XeqC(variables[Variables.PART][i], c.getId()), 
							isC[i]));
			store.impose(
					new Reified(
							new XeqC(variables[Variables.ORIENTATION][i], 1), 
							isForward[i]));
			
			pc[i] = new IfThen(
						new XeqC(variables[Variables.PART][i], c.getId()), 
						new XeqC(variables[Variables.ORIENTATION][i], 1)); 
		}
		
		
		return new Or(pc);
	}
	
	/**
	 * to alternate all components of the design
	 * 
	 * @param store
	 * @param variables
	 * @return
	 */
	private PrimitiveConstraint alternate(Store store, IntVar[][] variables) {
		
		int N = variables[Variables.ORIENTATION].length;
		

		/*
		 * 
		 * IF orientation(i) == 1 THEN orientation(i+1) != 1 
		 * OR
		 * IF orientation(i) == -1 THEN orientation(i+1) != -1
		 * 
		 * 0 <= i < N
		 */
		PrimitiveConstraint pcForward[] = new PrimitiveConstraint[N];
		for(int i=0; i<N; i++) {
			if(i%2==0) {
				pcForward[i] = new XeqC(variables[Variables.ORIENTATION][i], 1);				
			} else {
				pcForward[i] = new XneqC(variables[Variables.ORIENTATION][i], 1);
			}
		}
		PrimitiveConstraint pcReverse[] = new PrimitiveConstraint[N];
		for(int i=0; i<N; i++) {
			if(i%2==0) {
				pcReverse[i] = new XeqC(variables[Variables.ORIENTATION][i], -1);				
			} else {
				pcReverse[i] = new XneqC(variables[Variables.ORIENTATION][i], -1);
			}
		}
		
		return new Or(new And(pcForward), new And(pcReverse));
	}

	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		return new Not((PrimitiveConstraint)this.toJaCoP(store, variables));
	}
	
}
