/*
 * Copyright (c) 2014, Boston University
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 *    
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *    
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.minieugene;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.cidarlab.minieugene.predicates.LogicalAnd;
import org.cidarlab.minieugene.predicates.Predicate;
import org.cidarlab.minieugene.util.FileUtil;

public class ScriptAnalyzer {

	private Map<String, Integer> counters;
	
	public ScriptAnalyzer() {
		this.counters = new HashMap<String, Integer>();
	}
	
	public static void main(String[] args) {
		
		ScriptAnalyzer sa = new ScriptAnalyzer();
		sa.analyze("./scripts/");

		
		sa.dumpStats();
	}

	
	public void analyze(String directory) {
		File root = new File( directory );
	    File[] directories = root.listFiles();

		for ( File f : directories ) {
			if ( f.isDirectory() ) {
				this.analyzeAll(f.toString());
//				testAll( f.getAbsolutePath() );
			} else {
				System.out.println("**** "+f.getAbsoluteFile()+" ****");
				try {
//					this.test(f);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void analyzeAll(String d) {
		//System.out.println(d.toString());
		File root = new File(d);
	    File[] scriptFiles = root.listFiles();

	    MiniEugene me = new MiniEugene();
	    
		for ( File f : scriptFiles ) {
			
			incrementCounter("NR_OF_SCRIPTS");
			
			String script = null;
			try {
				
				script = FileUtil.readFile(f);
				script = correctScript(script);
				LogicalAnd la = me.getCNF(script);
				this.analyze(la);
				incrementCounter("NR_OF_VALID_SCRIPTS");
			} catch(Exception e) {
				incrementCounter("NR_OF_ERROR_SCRIPTS");
				//System.err.println(script+" contains errors... ignoring it...");
			}
		}
	}
	
	private void analyze(LogicalAnd la) {
		for(Predicate p : la.getPredicates()) {
			incrementCounter(p.getOperator());
		}
	}
	
	private void incrementCounter(String counterName) {
		if(this.counters.containsKey(counterName)) {
			this.counters.put(counterName, this.counters.get(counterName) + 1);
		} else {
			this.counters.put(counterName, new Integer(1));
		}
	}
	
	private String correctScript(String script) {
    	String[] lines = script.split(
    			System.getProperty("line.separator"));
    	StringBuilder sb = new StringBuilder();
		for(int i=0; i<lines.length; i++) {
			if(!lines[i].trim().isEmpty()) {
				if(!lines[i].startsWith("//")) {
					sb.append(lines[i]);
					if(!lines[i].endsWith(".")) {
						sb.append(".").append(System.getProperty("line.separator"));
					}
				}
			}
		}

		return sb.toString();

	}
	
	public void dumpStats() {
		for(String counterName : this.counters.keySet()) {
			System.out.println(counterName+" -> "+this.counters.get(counterName));
		}
	}
}
