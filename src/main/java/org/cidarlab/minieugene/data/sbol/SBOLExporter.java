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

package org.cidarlab.minieugene.data.sbol;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.core.StrandType;


/**
 * 
 * @author Ernst Oberortner
 */
public class SBOLExporter {

	/*
	 * that's the mapping:
	 * 
	 * List<Symbol[]>  -> Collection
	 * Symbol[]        -> composite DNAComponent
	 * Symbol          -> basic     DNAComponent
	 * 
	 * - in miniEugene, we do not support DNA sequences
	 * - always we create an SBOL Collection (also if there is only 1 solution!)
	 */

	public SBOLDocument toSBOLDocument(List<Component[]> solutions) 
			throws MiniEugeneException {
		
		try {
			// create an empty document populated with the SBOL objects
			// from the given collection
			SBOLDocument document = SBOLFactory.createDocument();

			String uuid = UUID.randomUUID().toString();
			
			Collection col = SBOLFactory.createCollection();
			col.setDisplayId("design"+uuid);
			col.setName("design"+uuid);
			col.setURI(
					URI.create("http://www.cidarlab.org/miniEugene/designs/"+uuid));
			
			int i=1;
			for(Component[] solution : solutions) {
				DnaComponent dc = toSBOL(solution, uuid, i);
				col.getComponents().add(dc);
				i++;
			}
			
			// add the DnaComponent to this document
			document.addContent(col);

			return document;

		} catch (Exception e) {
			throw new MiniEugeneException(e.getMessage());
		}
	}
	
	private DnaComponent toSBOL(Component[] solution, String uuid, int i) {
		
		DnaComponent dc = SBOLFactory.createDnaComponent();
		dc.setDisplayId("solution_"+i);
		dc.setName("solution_"+i);
		dc.setURI(
				URI.create("http://www.cidarlab.org/miniEugene/designs/"+uuid+"/solution_"+i));
		
		int pos = 0;
		for(Component symbol : solution) {
			
			/*
			 * the annotation for the sub-component
			 */
			SequenceAnnotation sa = SBOLFactory.createSequenceAnnotation();
			sa.setURI(
					URI.create("http://www.cidarlab.org/miniEugene/designs/"+uuid+"/solution_"+i+"/"+pos));

			/*
			 * miniEugene orientation --> SBOL SequenceAnnotation strand
			 */
			if(symbol.isForward()) {
				sa.setStrand(StrandType.POSITIVE);
			} else {
				sa.setStrand(StrandType.NEGATIVE);
			}
			
			/*
			 * the sub-component
			 */
			DnaComponent subDC = SBOLFactory.createDnaComponent();
			subDC.setURI(
					URI.create("http://www.cidarlab.org/miniEugene/designs/"+uuid+"/solution_"+i+"/"+pos+"/"+symbol.getId()));
			subDC.setDisplayId(i+"_"+pos+"_"+symbol.getName());
			subDC.setName(symbol.getName());
			
			/*
			 * add the sub-component to the annotation
			 */
			sa.setSubComponent(subDC);
			
			/*
			 * reverse direction?
			 */
			if(!symbol.isForward()) {
				sa.setStrand(StrandType.NEGATIVE);
			}
			
			/*
			 * todo: precedes 
			 */
			
			/*
			 * ultimately add the annotation to the composite DNA component
			 */
			dc.addAnnotation(sa);
			
			pos++;			
		}			
		
		return dc;
	}
	
}
