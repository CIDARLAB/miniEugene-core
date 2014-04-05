package org.cidarlab.minieugene;

import java.util.Collection;

import org.cidarlab.minieugene.constants.Type;
import org.cidarlab.minieugene.data.sbol.SBOLImporter;
import org.cidarlab.minieugene.dom.Component;

public class SBOLImport {

	public static void main(String[] args) 
			throws Exception {
		Object o = SBOLImporter.importSBOL("./imports/sbol/all.sbol.xml");
//		Object o = SBOLImporter.importSBOL("./imports/sbol/disjunction.sbol.xml");
//		Object o = SBOLImporter.importSBOL("./imports/sbol/conjunction.sbol.xml");
		System.out.println(o.getClass());
		if(o instanceof Collection) {
			print((Collection<Component>)o); 
		}
	}
	
	private static void print(Collection<Component> col) {
		System.out.println(col.size());
		for(Component c : col) {
			if(c.getType().equals(Type.RIBOSOME_BINDING_SITE)) {
				System.out.println(c);				
			}
		}
	}

}
