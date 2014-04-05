package org.cidarlab.minieugene;

import java.util.Collection;

import org.cidarlab.minieugene.constants.Type;
import org.cidarlab.minieugene.data.sbol.SBOLImporter;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;

public class ScriptBuilder {

	public static void main(String[] args) 
			throws Exception {
		ScriptBuilder sb = new ScriptBuilder();
		
		// first, we import the parts from an SBOL file
		Object o = sb.importParts("./imports/sbol/small_all.sbol.xml");
		
		if(o instanceof Collection) {
			String script = sb.buildScript((Collection<Component>)o);
			System.out.println(script);
			
			// now, we invoke miniEugene in order to execute the script
			MiniEugene me = new MiniEugene();
			
			me.solve(script);
			
			me.getStatistics().print();
		}

	}
	
	private Object importParts(String filename) 
			throws EugeneException {
		try {
			/*
			 * here, instead of importing the data from an SBOL file, 
			 * we can also import the data from Sparrow...
			 */
			return SBOLImporter.importSBOL(filename);
		} catch(Exception e) {
			throw new EugeneException(e.getMessage());
		}
	}
	
	private String buildScript(Collection<Component> col) 
			throws EugeneException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("N=4.");
		
		StringBuilder sbP = new StringBuilder();
		StringBuilder sbR = new StringBuilder();
		StringBuilder sbC = new StringBuilder();
		StringBuilder sbT = new StringBuilder();
		
		int i = 0;
		for(Component c : col) {
			
			if(i % 4 == 0) {
				if(c.getType().equals(Type.PROMOTER)) {
					sbP.append("contains ").append(c.getName()).append(" \\/ ");
				} else if(c.getType().equals(Type.RIBOSOME_BINDING_SITE)) {
					sbR.append("contains ").append(c.getName()).append(" \\/ ");
				} else if(c.getType().equals(Type.CODING_SEQUENCE)) {
					sbC.append("contains ").append(c.getName()).append(" \\/ ");
				} else if(c.getType().equals(Type.TERMINATOR)) {
					sbT.append("contains ").append(c.getName()).append(" \\/ ");
				}
			}
			i++;
		}
		
		sb.append(sbP.substring(0, sbP.length() - 4)).append(".");
		sb.append(sbR.substring(0, sbR.length() - 4)).append(".");
		sb.append(sbC.substring(0, sbC.length() - 4)).append(".");
		sb.append(sbT.substring(0, sbT.length() - 4)).append(".");
		return sb.toString();
	}
}
