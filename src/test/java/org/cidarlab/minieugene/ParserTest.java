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
