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
