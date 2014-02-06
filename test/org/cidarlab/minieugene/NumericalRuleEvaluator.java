/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cidarlab.minieugene;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.exception.EugeneException;

/**
 *
 * @author erik
 */
public class NumericalRuleEvaluator {
    public static final int MAX_N = 7;
    public static final int MAX_K = 5;
    
    public static void main(String[] args) {
        try {
        	System.out.println("**** CONTAINS ****");
            if(validateContains()) {
                System.out.println("CONTAINS WORKS");
            } else {
                System.out.println("CONTAINS DOES NOT WORK");
            }
            
        	System.out.println("**** NOTCONTAINS ****");
            if(validateNotContains()) {
                System.out.println("NOTCONTAINS WORKS");
            } else {
                System.out.println("NOTCONTAINS DOES NOT WORK");
            }
            
        	System.out.println("**** EXACTLY ****");
            if(validateExactly()) {
                System.out.println("EXACTLY WORKS");
            } else {
                System.out.println("EXACTLY DOES NOT WORK");
            }
            
        	System.out.println("**** MORETHAN ****");
            if(validateMoreThan()) {
                System.out.println("MORETHAN WORKS");
            } else {
                System.out.println("MORETHAN DOES NOT WORK");
            }
            
        	System.out.println("**** STARTSWITH ****");
            if(validateStartsWith()) {
                System.out.println("STARTSWITH WORKS");
            } else {
                System.out.println("STARTSWITH DOES NOT WORK");
            }
            
        	System.out.println("**** ENDSWITH ****");
            if(validateEndsWith()) {
                System.out.println("ENDSWITH WORKS");
            } else {
                System.out.println("ENDSWITH DOES NOT WORK");
            }
            
        	System.out.println("**** ALL_BEFORE ****");
            if(validateAllBefore()) {
                System.out.println("ALLBEFORE WORKS");
            } else {
                System.out.println("ALLBEFORE DOES NOT WORK");
            }
            
        	System.out.println("**** ALL_AFTER ****");
            if(validateAllAfter()) {
                System.out.println("ALL_AFTER WORKS");
            } else {
                System.out.println("ALL_AFTER DOES NOT WORK");
            }
            
        } catch (EugeneException ex) {
            Logger.getLogger(NumericalRuleEvaluator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean validateContains() throws EugeneException {
        boolean validated = true;
        for (int n = 1; n <= MAX_N; n++) {
            for(int k = 1; k <= n && k <= MAX_K; k++) {
                validated &= validateContains(n, k);
            }
        }
        return validated;
    }
    
    public static boolean validateContains(int N, int k) throws EugeneException {
        String constrainedPart = "CONTAINS i0";
        List<String> ruleDisjunction = getRuleDisjunction(k, constrainedPart);
        long totalDevices = 0;
        for(String rule : ruleDisjunction) {
			MiniEugene me = new MiniEugene();				
            me.solve(rule.split(NEWLINE), N, -1);
            totalDevices += (long) me.getStatistics().getValueByKey("NumSolutions");
        }

//        System.out.println("N: " + N + " k: " + k);
//        System.out.println("CONTAINS DEVICE AMOUNT: " + containsDeviceAmount(N,k));
//        System.out.println("TOTAL DEVICES: " + totalDevices);

        return containsDeviceAmount(N, k) == totalDevices;
    }
    
    public static boolean validateNotContains() throws EugeneException {
        boolean validated = true;
        for(int n = 1; n <= MAX_N; n++) {
            for(int k = 1; k <= n && k <= MAX_K; k++ ) {
                validated &= validateNotContains(n, k);
            }
        }
        return validated;
    }
    
    public static boolean validateNotContains(int N, int k) throws EugeneException {
        String constrainedPart = "NOTCONTAINS i0";
        List<String> ruleDisjunction = getRuleDisjunction(k, constrainedPart);
        long totalDevices = 0;
        for (String rule : ruleDisjunction) {
            try {
    			MiniEugene me = new MiniEugene();				
                me.solve(rule.split(NEWLINE), N, -1);
                totalDevices += (long) me.getStatistics().getValueByKey("NumSolutions");
            } catch(EugeneException e) {
                totalDevices += 0;
            }
        }
        return notContainsDeviceAmount(N, k) == totalDevices;
    }
    
    public static boolean validateExactly() throws EugeneException {
        boolean validated = true;
        for(int n = 1; n <= MAX_N; n++) {
            for(int k = 1; k <=n && k <= MAX_K; k++) {
                for(int i = 0; i <= n; i++) {
                    validated &= validateExactly(n, k, i);
                }
            }
        }
        return validated;
    }
    
    public static boolean validateExactly(int N, int k, int i) throws EugeneException {
        String constrainedPart = " i0 EXACTLY " + i;
        List<String> ruleDisjunction = getRuleDisjunction(k, constrainedPart);
        long totalDevices = 0;
        for (String rule : ruleDisjunction) {
            try {
    			MiniEugene me = new MiniEugene();				
                me.solve(rule.split(NEWLINE), N, -1);
                totalDevices += (long) me.getStatistics().getValueByKey("NumSolutions");
            } catch(EugeneException e) {
                totalDevices += 0;
            }
        }
        return exactlyDeviceAmount(N, k, i) == totalDevices;
    }
    
    public static boolean validateMoreThan() {
        boolean validated = true;
        for(int n = 1; n <= MAX_N; n++) {
            for(int k = 1; k <= n && k <= MAX_K; k++) {
                for(int x = 0; x <= n-1; x++) {
                    validated &= validateMoreThan(n, k, x);
                }
            }
        }
        return validated;
    }
    
    public static boolean validateMoreThan(int N, int k, int x) {
        String constrainedPart = "i0 MORETHAN " + x;
        List<String> ruleDisjunction = getRuleDisjunction(k, constrainedPart);
        long totalDevices = 0;
        for(String rule : ruleDisjunction) {
            try {
    			MiniEugene me = new MiniEugene();				
                me.solve(rule.split(NEWLINE), N, -1);
                totalDevices += (long) me.getStatistics().getValueByKey("NumSolutions");
            } catch(EugeneException e) {
                totalDevices += 0;
            }
            /*
            System.out.println("N: " + N + " k: " + k + " x: " + x);
            System.out.println("CONTAINS DEVICE AMOUNT: " + containsDeviceAmount(N,k));
            System.out.println("TOTAL DEVICES: " + totalDevices);
            */
        }
        return moreThanDeviceAmount(N, k, x) == totalDevices;
    }
    
    public static boolean validateStartsWith() {
        boolean validated = true;
        for(int n = 1; n <= MAX_N; n++) {
            for(int k = 1; k <= n && k <= MAX_K; k++) {
                validated &= validateStartsWith(n, k);
            }
        }
        return validated;
    }
    
    public static boolean validateStartsWith(int N, int k) {
        String constrainedPart = "STARTSWITH i0";
        List<String> ruleDisjunction = getRuleDisjunction(k, constrainedPart);
        long totalDevices = 0;
        for(String rule : ruleDisjunction) {
            try {
    			MiniEugene me = new MiniEugene();				
                me.solve(rule.split(NEWLINE), N, -1);
                totalDevices += (long) me.getStatistics().getValueByKey("NumSolutions");
            } catch(EugeneException e) {
                totalDevices += 0;
            }   
        }
        /*
        System.out.println("N: " + N + " k: " + k);
        System.out.println("STARTSWITH DEVICE AMOUNT: " + containsDeviceAmount(N,k));
        System.out.println("TOTAL DEVICES: " + totalDevices);
        */
        return startsWithEndsWithDeviceAmount(N, k) == totalDevices;
    }
    
    public static boolean validateEndsWith() {
        boolean validated = true;
        for(int n = 1; n <= MAX_N; n++) {
            for(int k = 1; k <= n && k <= MAX_K; k++) {
                validated &= validateEndsWith(n, k);
            }
        }
        return validated;
    }
    
    public static boolean validateEndsWith(int N, int k) {
        String constrainedPart = "ENDSWITH i0";
        List<String> ruleDisjunction = getRuleDisjunction(k, constrainedPart);
        long totalDevices = 0;
        for(String rule : ruleDisjunction) {
            try {
    			MiniEugene me = new MiniEugene();				
                me.solve(rule.split(NEWLINE), N, -1);
                totalDevices += (long) me.getStatistics().getValueByKey("NumSolutions");
            } catch(EugeneException e) {
                totalDevices += 0;
            }   
        }
        /*
        System.out.println("N: " + N + " k: " + k);
        System.out.println("STARTSWITH DEVICE AMOUNT: " + containsDeviceAmount(N,k));
        System.out.println("TOTAL DEVICES: " + totalDevices);
        */
        return startsWithEndsWithDeviceAmount(N, k) == totalDevices;
    }
    
    public static boolean validateAllBefore() {
        boolean validated = true;
        for(int n = 1; n <= MAX_N; n++) {
            for(int k = 1; k <= n && k <= MAX_K; k++) {
                validated &= validateAllBefore(n, k);
            }
        }
        return validated;
    }
    
    public static boolean validateAllBefore(int N, int k) {
        String constrainedPart = "a ALL_BEFORE b";
        List<String> ruleDisjunction = getRuleDisjunction(k, constrainedPart);
        long totalDevices = 0;
        for(String rule : ruleDisjunction) {
            try {
    			MiniEugene me = new MiniEugene();				
                me.solve(rule.split(NEWLINE), N, -1);
                totalDevices += (long) me.getStatistics().getValueByKey("NumSolutions");
            } catch(EugeneException e) {
                totalDevices += 0;
            }   
        }
        /*
        System.out.println("N: " + N + " k: " + k);
        System.out.println("ALLBEFORE DEVICE AMOUNT: " + allBeforeAllAfterDeviceAmount(N,k+1));
        System.out.println("TOTAL DEVICES: " + totalDevices);
        */
        return allBeforeAllAfterDeviceAmount(N, k+1) == totalDevices;
    }
    
    public static boolean validateAllAfter() {
        boolean validated = true;
        for(int n = 1; n <= MAX_N; n++) {
            for(int k = 1; k <= n && k <= MAX_K; k++) {
                validated &= validateAllAfter(n, k);
            }
        }
        return validated;
    }
    
    public static boolean validateAllAfter(int N, int k) {
        String constrainedPart = "a ALL_AFTER b";
        List<String> ruleDisjunction = getRuleDisjunction(k, constrainedPart);
        long totalDevices = 0;
        for(String rule : ruleDisjunction) {
            try {
    			MiniEugene me = new MiniEugene();				
                me.solve(rule.split(NEWLINE), N, -1);
                totalDevices += (long) me.getStatistics().getValueByKey("NumSolutions");
            } catch(EugeneException e) {
                totalDevices += 0;
            }   
        }
        /*
        System.out.println("N: " + N + " k: " + k);
        System.out.println("ALLBEFORE DEVICE AMOUNT: " + allBeforeAllAfterDeviceAmount(N,k+1));
        System.out.println("TOTAL DEVICES: " + totalDevices);
        */
        return allBeforeAllAfterDeviceAmount(N, k+1) == totalDevices;
    }
    
    public static long containsDeviceAmount(int n, int k) {
        return (long) Math.pow(k, n) - notContainsDeviceAmount(n,k);
    }
    
    public static long notContainsDeviceAmount(int n, int k) {
        return (long) Math.pow(k-1, n);
    }
    
    public static long exactlyDeviceAmount(int n, int k, int i) {
        return nCr(n, i) * (long) Math.pow(k - 1, n - i);
    }
    
    public static long moreThanDeviceAmount(int n, int k, int x) {
        long total = 0;
        for(int i = x + 1; i <= n; i++) {
            total += exactlyDeviceAmount(n, k, i);
        }
        return total;
    }
    
    public static long startsWithEndsWithDeviceAmount(int n, int k) {
        return (long) Math.pow(k, n-1);
    }
    
    public static long allBeforeAllAfterDeviceAmount(int n, int k) {
        long total = 0;
        for (int e = 1; e <= n; e++) {
            for(int i = e; i <= n; i++) {
                total += nCr(i-1, e-1) * (long)Math.pow(k - 2, i - e) * (long)Math.pow(k - 1, n - i);
            }
        }
        total += (long)Math.pow(k-1, n);
        return total;
    }
    
    public static long nCr(int n, int r) {
        return fact(n) / (fact(n-r) * fact(r));
    }
    
    public static long fact(int n) {
        long total = 1;
        for(int i = 2; i <= n; i++) {
            total *= i;
        }
        return total;
    }
    
    private static final String NEWLINE = System.getProperty("line.separator");
    
    public static List<String> getRuleDisjunction(int k, String constrainedPart ) {
        List<String> ruleList = new ArrayList<String>();
        String ADDi1 = NEWLINE+"CONTAINS i1";
        String ADDi2 = NEWLINE+"CONTAINS i2";
        String ADDi3 = NEWLINE+"CONTAINS i3";
        String ADDi4 = NEWLINE+"CONTAINS i4";
        if(k >= 1) {
            ruleList.add(constrainedPart);
        }
        if(k >= 2) {
            ruleList.add(constrainedPart + ADDi1);
        }
        if(k >= 3) {
            ruleList.add(constrainedPart + ADDi2);
            ruleList.add(constrainedPart + ADDi1 + ADDi2 );
        }
        if(k >= 4) {
            ruleList.add(constrainedPart + ADDi3);
            ruleList.add(constrainedPart + ADDi1 + ADDi3);
            ruleList.add(constrainedPart + ADDi2 + ADDi3);
            ruleList.add(constrainedPart + ADDi1 + ADDi2 + ADDi3);
        }
        
        if(k >= 5) {
            ruleList.add(constrainedPart + ADDi4);
            ruleList.add(constrainedPart + ADDi1 + ADDi4);
            ruleList.add(constrainedPart + ADDi2 + ADDi4);
            ruleList.add(constrainedPart + ADDi3 + ADDi4);
            ruleList.add(constrainedPart + ADDi1 + ADDi2 + ADDi4);
            ruleList.add(constrainedPart + ADDi1 + ADDi3 + ADDi4);
            ruleList.add(constrainedPart + ADDi2 + ADDi3 + ADDi4);
            ruleList.add(constrainedPart + ADDi1 + ADDi2 + ADDi3 + ADDi4);
        }
        return ruleList;
    }
}
