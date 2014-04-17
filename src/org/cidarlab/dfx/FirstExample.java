/*
 * Authored by Kevin LeShane. Apr 10, 2014
 */

package org.cidarlab.dfx;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.exception.EugeneException;

/**
 * In general, the utilization of miniEugene is divided into the following steps:<br/>
 * 1. instantiate MiniEugene<br/>
 * 2. specify an String array, each row containing a miniEugene rule<br/>
 * 3. let miniEugene solve the rules (using MiniEugene's solve() method)<br/>
 * 4. process the solutions (maybe by using the SolutionExporter)<br/>
 *    and/or have a look into the statistics of the solving process<br/>
 *
 * @author Kevin LeShane
 *
 */

public class FirstExample {
    
    public static void main(String[] args) {
        
        // Instantiate miniEugene
        MiniEugene me = new MiniEugene();
        
        String[] rules = {
            "CONTAINS p", "CONTAINS r", "CONTAINS c", "CONTAINS t",
            "p BEFORE r"
        };
        
        try {
            /*  Ask miniEugene to generate 4 solutions that
            *   are 8-characters long (N=8)
            */
            me.solve(rules, 8, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (me.getSolutions() != null) {
            System.out.println( "Number of Solutions: " + me.getSolutions().size() );
            System.out.println( "Solution 0: " + Arrays.toString(me.getSolutions().get(1)) );
        }
        
        // Dump the statistics
        if(me.getStatistics() != null) {
                me.getStatistics().print();
        }
    }
    
}
