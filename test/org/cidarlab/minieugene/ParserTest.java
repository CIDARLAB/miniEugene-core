package org.cidarlab.minieugene;

import java.io.File;
import java.io.IOException;

import org.cidarlab.minieugene.util.FileUtil;

/**
 * 
 * @author Ernst Oberortner
 */
public class ParserTest {

	private MiniEugene me;
	
	public ParserTest() {
		this.me = new MiniEugene();
	}
	
	public static void main(String[] args) 
			throws IOException {
		ParserTest pt = new ParserTest();
		
//		pt.parse(FileUtil.readFile(new File("./tests/basic.eug")));
//		pt.parse(FileUtil.readFile(new File("./tests/contains.eug")));
//		pt.parse(FileUtil.readFile(new File("./tests/drives.eug")));
//		pt.parse(FileUtil.readFile(new File("./tests/interactions.eug")));
//		pt.parse(FileUtil.readFile(new File("./tests/not-morethan.eug")));
//		pt.parse(FileUtil.readFile(new File("./tests/not.eug")));
//		pt.parse(FileUtil.readFile(new File("./tests/operand-naming.eug")));
//		pt.parse(FileUtil.readFile(new File("./tests/bryan/ex1")));
//		pt.parse(FileUtil.readFile(new File("./tests/bryan/ex2")));
//		pt.parse(FileUtil.readFile(new File("./tests/bryan/ex3")));
//		pt.parse(FileUtil.readFile(new File("./tests/bryan/ex4")));
//		pt.parse(FileUtil.readFile(new File("./tests/swati/test01")));
//		pt.parse(FileUtil.readFile(new File("./tests/then/then01")));
//		pt.parse(FileUtil.readFile(new File("./tests/then/then02")));
//		pt.parse(FileUtil.readFile(new File("./tests/then/then03")));
		pt.parse(FileUtil.readFile(new File("./tests/or")));
	}
	
	/**
	 * 
	 * @param script
	 */
	private void parse(String script) {
		
		try {
			this.me.solve(script);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}

}
