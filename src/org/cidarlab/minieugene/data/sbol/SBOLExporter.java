/*
Copyright (c) 2012 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
 */

package org.cidarlab.minieugene.data.sbol;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.cidarlab.minieugene.Symbol;
import org.cidarlab.minieugene.exception.EugeneException;
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

	public SBOLDocument toSBOLDocument(List<Symbol[]> solutions) 
			throws EugeneException {
		
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
			for(Symbol[] solution : solutions) {
				DnaComponent dc = toSBOL(solution, uuid, i);
				col.getComponents().add(dc);
				i++;
			}
			
			// add the DnaComponent to this document
			document.addContent(col);

			return document;

		} catch (Exception e) {
			throw new EugeneException(e.getMessage());
		}
	}
	
	private DnaComponent toSBOL(Symbol[] solution, String uuid, int i) {
		
		DnaComponent dc = SBOLFactory.createDnaComponent();
		dc.setDisplayId("solution_"+i);
		dc.setName("solution_"+i);
		dc.setURI(
				URI.create("http://www.cidarlab.org/miniEugene/designs/"+uuid+"/solution_"+i));
		
		int pos = 0;
		for(Symbol symbol : solution) {
			
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
