package org.cidarlab.minieugene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;

import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.util.SolutionExporter;


public class TestSuite {

	public static void main(String[] args) {
//		new TestSuite().test(new File("./tests/drives.eug"));
//		new TestSuite().test(new File("./tests/interactions.eug"));

//		new TestSuite().test(new File("./designs/priority-encoder/rev3/cassette01.eug"));
//		new TestSuite().test(new File("./designs/priority-encoder/rev3/cassette02.eug"));
//		new TestSuite().test(new File("./designs/priority-encoder/rev3/cassette03.eug"));
//		new TestSuite().test(new File("./designs/priority-encoder/rev3/basic_composition.eug"));

		/*
		 * NOR GATE EXAMPLE
		 */
//		new TestSuite().test(new File("./designs/nor-gate/repressing-cassette.eug"));
//		new TestSuite().test(new File("./designs/nor-gate/reporting-cassette.eug"));
//		new TestSuite().test(new File("./designs/nor-gate/nor-gate.eug"));

//		new TestSuite().test(new File("./designs/nor-gate/rev1/repressing-cassette.eug"));
//		new TestSuite().test(new File("./designs/nor-gate/rev1/reporting-cassette.eug"));
//		new TestSuite().test(new File("./designs/nor-gate/rev1/nor-gate.eug"));

		//		new TestSuite().test(new File("./designs/nor-gate/nor-gate.eug"));
//		new TestSuite().test(new File("./examples/transcriptional-unit.eug"));

		/*
		 * naming of rule operands
		 */
//		new TestSuite().test(new File("./tests/operand-naming.eug"));
		//new TestSuite().test(new File("./tests/bryan/ex1"));
		//new TestSuite().test(new File("./tests/bryan/ex2"));
		//new TestSuite().test(new File("./tests/bryan/ex3"));
		new TestSuite().test(new File("./tests/bryan/ex4"));
		
		/*** TESTS ***/
//		new TestSuite().testAll("./tests");
	}

	public void test(File f) {
		try {
			/*
			 * read the file
			 */
			String script = this.readFile(f);
			long t1 = System.nanoTime();
			
			MiniEugene me = new MiniEugene();				
			me.executeScript(script, -1, -1);

//			MiniEugeneReturn mer = new MiniEugene(-1, -1, false).execute(script);
			long tProcessing = System.nanoTime() - t1;
			
			me.getStatistics().print();

			SolutionExporter se = new SolutionExporter(me.getSolutions(), me.getInteractions());
			System.out.println(se.toPigeon());
			
			String filename = java.util.UUID.randomUUID().toString();
			se.toSBOL("./test-results/"+filename+".sbol.xml");
			se.toEugene("./test-results/"+filename+".eug");
			
			System.out.println("[TestSuite.test] full processing time: "+tProcessing*Math.pow(10, -9)+"sec");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//new Eugene(sFile);
	}
	
	

	private String readFile(File f) throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(f));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();

		return fileData.toString();
	}
	
	public void testAll( String path ) {
		File root = new File( path );
		FilenameFilter filter = new FilenameFilter() {
                @Override 
	        public boolean accept(File directory, String fileName) {
	            return fileName.endsWith(".eug");
	        }
	    };
	    File[] list = root.listFiles(filter);

		for ( File f : list ) {
			if ( f.isDirectory() ) {
				testAll( f.getAbsolutePath() );
			} else {
				System.out.println("**** "+f.getAbsoluteFile()+" ****");
				try {
					this.test(f);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	
}
