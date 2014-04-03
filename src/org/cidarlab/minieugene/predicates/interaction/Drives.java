package org.cidarlab.minieugene.predicates.interaction;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.orientation.AllForward;
import org.cidarlab.minieugene.predicates.orientation.AllSameOrientation;
import org.cidarlab.minieugene.solver.jacop.PartTypes;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Not;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XneqC;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class Drives 
	extends InteractionPredicate {
	
	public Drives(Component a, Component b) {
		super(a, b);
	}
		

	@Override
	public String getOperator() {
		return RuleOperator.DRIVES.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getA())
			.append(" ").append(RuleOperator.DRIVES).append(" ")
			.append(this.getB());
		return sb.toString();
	}

	@Override
	public PrimitiveConstraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {

    	int N = variables[Variables.TYPE].length;
    	

    	/*
		 *  0 <= i,j < N, i < j
		 *	a DRIVES b := 
		 *		a(i) /\ b(j) => i < j
		 */
    	
		PrimitiveConstraint pc[] = new PrimitiveConstraint[N];
		for(int i=0; i<N-1; i++) {

			PrimitiveConstraint[] gene = new PrimitiveConstraint[N-(i+1)];
			for(int j=(i+1); j<N; j++) {

				if(j-i > 1) {
					PrimitiveConstraint[] noTerminator = new PrimitiveConstraint[j-(i+1)];
					for(int k=i+1; k<=j-1; k++) {

						noTerminator[k-(i+1)] = new XneqC(variables[Variables.TYPE][k], PartTypes.get("TERMINATOR"));
					}
					
					gene[j-(i+1)] = 
							new And(
									new XeqC(variables[Variables.PART][j], this.getB().getId()),
									new And(noTerminator));

				} else {
					gene[j-(i+1)] = new XeqC(variables[Variables.PART][j], this.getB().getId());
				}

			}
			
			pc[i] = new IfThen(
						new XeqC(variables[Variables.PART][i], this.getA().getId()),
						new Or(gene));
		}
		
		pc[N-1] = new XneqC(variables[Variables.PART][N-1], this.getA().getId());

    	// a DRIVES b =>
		//     a SAME_ORIENTATION b /\
		//     forward a => a before b /\
		//     reverse a => a after b
		
		PrimitiveConstraint[] pcReturn = new PrimitiveConstraint[5];
		
		pcReturn[2] = new AllSameOrientation(
				this.getA(), this.getB()).toJaCoP(store, variables);
		pcReturn[3] = new And(pc);
		pcReturn[4] = new And(
				new IfThen(
						new AllForward(this.getA()).toJaCoP(store, variables),
						this.PBeforeG_Forward(store, variables)),
				new IfThen(
						new AllForward(this.getA()).toJaCoP(store, variables),
						this.PAfterG_Reverse(store, variables))); 
		
		return new And(pcReturn);
	}
	
    private PrimitiveConstraint PBeforeG_Forward(Store store, IntVar[][] variables) {

    	int N = variables[Variables.TYPE].length;

    	PrimitiveConstraint[] pc = new PrimitiveConstraint[N];
    	pc[0] = new And(
    				new XneqC(variables[Variables.PART][0], this.getB().getId()),
    				new XneqC(variables[Variables.ORIENTATION][0], 1));
    				
    	for(int i=1; i<N; i++) {

	    	PrimitiveConstraint[] upstreamPromoter = new PrimitiveConstraint[i];
			
			for(int j=0; j<i; j++) {				
	    		upstreamPromoter[j] = new And( 
	    				new XeqC(variables[Variables.ORIENTATION][j], 1), 
	    				new XeqC(variables[Variables.PART][j], this.getA().getId())); 
			}

			pc[i] = new IfThen(
						new XeqC(variables[Variables.PART][i], this.getB().getId()),
						new And(new Or(upstreamPromoter),
								new XeqC(variables[Variables.ORIENTATION][i], 1)));
    	}
    	
    	return new Or(pc);
    }
    
    private PrimitiveConstraint PAfterG_Reverse(Store store, IntVar[][] variables) {
    	
    	int N = variables[Variables.TYPE].length;
    	
    	PrimitiveConstraint[] pc = new PrimitiveConstraint[N];
    	for(int i=0; i<N-1; i++) {

	    	PrimitiveConstraint[] upstreamPromoter = new PrimitiveConstraint[N-i];
			
			for(int j=i; j<N; j++) {				
	    		upstreamPromoter[j-i] = new And(
	    				new XeqC(variables[Variables.ORIENTATION][j], -1),
	    				new XeqC(variables[Variables.PART][j], this.getA().getId()));
			}
			
			pc[i] = new IfThen(
							new XeqC(variables[Variables.PART][i], this.getB().getId()),
							new And(new Or(upstreamPromoter),
									new XeqC(variables[Variables.ORIENTATION][i], -1)));
    	}
    	
    	pc[N-1] = new And(
					new XneqC(variables[Variables.PART][N-1], this.getB().getId()),
					new XneqC(variables[Variables.ORIENTATION][N-1], -1));
    	
    	return new Or(pc);
    }


	@Override
	public PrimitiveConstraint toJaCoPNot(Store store, IntVar[][] variables)
			throws EugeneException {
		
		// we just negate the toJaCoP primitive constraint
		return new Not((PrimitiveConstraint)this.toJaCoP(store, variables));
	}

}
