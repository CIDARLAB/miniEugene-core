package org.cidarlab.minieugene.predicates.interaction;

import org.cidarlab.minieugene.constants.RuleOperator;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.predicates.positional.before.AllBefore;
import org.cidarlab.minieugene.solver.jacop.PartTypes;
import org.cidarlab.minieugene.solver.jacop.Variables;

import JaCoP.constraints.And;
import JaCoP.constraints.Constraint;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.Or;
import JaCoP.constraints.PrimitiveConstraint;
import JaCoP.constraints.Reified;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XeqY;
import JaCoP.constraints.XneqC;
import JaCoP.core.BooleanVar;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class Drives 
	extends InteractionPredicate {
	
	public Drives(int a, int b) {
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
	public Constraint toJaCoP(Store store, IntVar[][] variables) 
				throws EugeneException {
		
//		System.out.println("[Drives.toJaCoP] -> "+this.toString());

    	int N = variables[Variables.TYPE].length;

    	/*
		 *  0 <= i,j < N, i < j
		 *	a DRIVES b := 
		 *		a(i) /\ b(j) => i < j
		 */
    	
		PrimitiveConstraint pc[] = new PrimitiveConstraint[N];
		for(int i=0; i<N-1; i++) {

//			System.out.println("*****");
//			System.out.println("Promoter at "+i+"...");
			PrimitiveConstraint[] gene = new PrimitiveConstraint[N-(i+1)];
			for(int j=(i+1); j<N; j++) {

//				System.out.print("Gene at "+j+" -> ");
				if(j-i > 1) {
					PrimitiveConstraint[] noTerminator = new PrimitiveConstraint[j-(i+1)];
//					System.out.print("no Terminator at ");
					for(int k=i+1; k<=j-1; k++) {
//						System.out.print(k+", ");

						noTerminator[k-(i+1)] = new XneqC(variables[Variables.TYPE][k], PartTypes.get("TERMINATOR"));
					}
					
					gene[j-(i+1)] = 
							new And(
									new XeqC(variables[Variables.PART][j], b),
									new And(noTerminator));

				} else {
					gene[j-(i+1)] = new XeqC(variables[Variables.PART][j], b);
				}
//				System.out.println();
				
			}
			
			pc[i] = new IfThen(
						new XeqC(variables[Variables.PART][i], a),
						new Or(gene));
		}
		
		pc[N-1] = new XneqC(variables[Variables.PART][N-1], a);
				
		return new And(pc);
	}
	
    private PrimitiveConstraint[] PBeforeG_Forward(Store store, IntVar[][] variables) {

    	int N = variables[Variables.TYPE].length;

    	PrimitiveConstraint[] pc = new PrimitiveConstraint[N-1];
    	for(int i=1; i<N; i++) {

	    	PrimitiveConstraint[] upstreamPromoter = new PrimitiveConstraint[i];
			PrimitiveConstraint[] noInterveningTerminator = new PrimitiveConstraint[i];
			
			for(int j=0; j<i; j++) {				
	    		upstreamPromoter[j] = new XeqC(variables[Variables.PART][j], this.getA()); 

	    		noInterveningTerminator[j] = new XneqC(variables[Variables.TYPE][j], PartTypes.get("TERMINATOR"));
			}

			pc[i-1] = new IfThen(
							new And(
								new XeqC(variables[Variables.PART][i], this.getB()),
								new Or(upstreamPromoter)),
							new And(noInterveningTerminator));
    	}
    	
    	return pc;
    }
    
    private PrimitiveConstraint[] PAfterG_Reverse(Store store, IntVar[][] variables) {
    	
    	int N = variables[Variables.TYPE].length;
    	
    	PrimitiveConstraint[] pc = new PrimitiveConstraint[N-1];
    	for(int i=0; i<N-1; i++) {

	    	PrimitiveConstraint[] upstreamPromoter = new PrimitiveConstraint[N-i];
			
			for(int j=i; j<N; j++) {				
	    		upstreamPromoter[j-i] = new XeqC(variables[Variables.PART][j], this.getA());
			}
			
    		if(i != N-1) {
    			PrimitiveConstraint[] noInterveningTerminator = new PrimitiveConstraint[N-i-1];
    			for(int j=i; j<N-1; j++) {
    	    		noInterveningTerminator[j-i] = new XneqC(variables[Variables.TYPE][j], 4);
    			}
    			
    			pc[i] = new IfThen(
    						new XeqC(variables[Variables.PART][i], this.getB()),
    						new And(new Or(upstreamPromoter),
    								new And(noInterveningTerminator)));
    		} else {
    			pc[i] = new IfThen(
							new XeqC(variables[Variables.PART][i], this.getB()),
							new Or(upstreamPromoter));
    		}
    	}
    	
    	return pc;
    }

}
