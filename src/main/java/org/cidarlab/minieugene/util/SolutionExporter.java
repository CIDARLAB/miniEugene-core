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

package org.cidarlab.minieugene.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.lang3.ArrayUtils;
import org.cidarlab.minieugene.data.pigeon.Pigeonizer;
import org.cidarlab.minieugene.data.sbol.SBOLExporter;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.interaction.Interaction;
import org.cidarlab.minieugene.predicates.interaction.Participation;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;

/**
 * The SolutionExporter class provides methods to export 
 * miniEugene solutions to Pigeon, SBOL, and Eugene Header files
 * 
 * @author Ernst Oberortner
 *
 */
public class SolutionExporter {
	
	// solutions
	private List<Component[]> solutions;
	
	// interactions
	private Set<Interaction> interactions;
	
	private static final int NR_OF_PIGEON = 25;
	private static final int NR_OF_SBOL = 1000;
	private static final String NEWLINE = System.getProperty("line.separator");
	
	/**
	 * The constructor takes two paramters: (1) a list of solutions and 
	 * (2) a set of regulatory interactions. 
	 * After executing the solve() method of miniEugene, the solutions 
	 * and the set of interactions can be obtained by miniEugeneObj.getSolutions 
	 * and miniEugeneObj.getInteractions respectively.
	 * 
	 * @param solutions  
	 * @param interactions
	 */
	public SolutionExporter(List<Component[]> solutions, Set<Interaction> interactions) {
		this.solutions = solutions;
		this.interactions = interactions;
	}

	/**
	 * toPigeon visualizes a randomly selected subset of 
	 * ten solutions using Pigeon (pigeoncad.org)
	 * 
	 * @return an URI referring to the generated Pigeon image
	 * @throws MiniEugeneException
	 */
//	public URI toPigeon() 
//			throws MiniEugeneException {
//
//		if(null != solutions) {
//			
//			try {
//	        	Pigeonizer pigeon = new Pigeonizer();
//	            
//	            /* 
//	             * we visualize up to NR_OF_PIGEON designs 
//	             */
//	        	if(this.solutions.size() > NR_OF_PIGEON) {
//	            	return pigeon.pigeonize(
//	            			this.getRandomSolutions(NR_OF_PIGEON), 
//	            			this.interactions);
//	        	}
//	        	
//	        	return pigeon.pigeonize(
//	        			this.solutions, 
//	        			this.interactions);
//	        	
//	        } catch(Exception e) {
//	            throw new MiniEugeneException(e.getMessage());
//	        }
//		}
//		
//		return URI.create("");
//	}
        
        
        public Image genPigeonImage(int N, String name, String loc)  throws MiniEugeneException {
           try{
               MrCron(loc);
            Pigeonizer pig = new Pigeonizer();          
                List<Component[]> sols = this.solutions;
                
                	if(N != -1 && this.solutions.size() > N) {
	        		sols = this.getRandomSolutions(N); 
	        	}
            
                List<String> temp = new ArrayList<String>();
                int count = 0;
                for (Component[] solution : sols){
                    String tempName = "temp" + count;
                    temp.add(tempName);
                    pig.pigeonImage(solution, interactions, tempName, loc);
                    count++;
                }


                return this.toMergedImage(temp, name);
            }catch (Exception e){
                throw new MiniEugeneException("cmdline not working");
            }
//            
        }
        
        private void MrCron(String loc){
            File dir = new File(loc);
            long time = new Date().getTime();
               for(File file: dir.listFiles()) {
                   long diff = time - file.lastModified();
                   System.out.println(file + ", " + diff);
                   if (diff > 5 * 60 * 1000){
                    if (!file.isDirectory()) 
                        System.out.println(file.toString() + " deleted");
                        file.delete();
                   }
               }
        }
        

	public Image pigeonize(String filename, Map<String, Integer> colors, boolean label, int N) 
			throws MiniEugeneException {

		if(null != solutions) {
			
			try {
//	        	Pigeonizer pigeon = new Pigeonizer(colors, label);
//	            
//	        	List<URI> uris = new ArrayList<URI>();
//	            /* 
//	             * we visualize up to NR_OF_PIGEON designs 
//	             */
//	        	List<Component[]> sols = this.solutions;
//	        	
//	        	if(N != -1 && this.solutions.size() > N) {
//	        		sols = this.getRandomSolutions(N); 
//	        	}
//	        	
//	        	for(Component[] solution : sols) {
//	        		uris.add(
//	        			pigeon.pigeonizeSingle(solution, this.interactions));
//	        	}
                File f = new File("./test_design.png"); 
                int w = 0;
                int h = 0;
                
                BufferedImage image = ImageIO.read(f);
                
                if(w < image.getWidth()) {
					w = image.getWidth();
				}
				
				h += image.getHeight();
                
                
                BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                combined = image;
                
                String pigeonname = "newPigeonImage";
                File pic = new File(pigeonname);
                ImageIO.write(combined, "PNG", new File(pigeonname));

                return ImageIO.read(pic);
	        	
	        	// finally, we merge all pigeon images 
	        	// into one big image
	        	//return this.toMergedImage(uris, filename);
	        	
	        } catch(Exception e) {
	            throw new MiniEugeneException(e.getMessage());
	        }
		}
		
        throw new MiniEugeneException("nothing to visualize!");
	}
	
	private Image toMergedImage(List<String> imagePath, String filename) 
			throws MiniEugeneException {

		try {
			int w = 0;
			int h = 0;
			
			List<BufferedImage> images = new ArrayList<BufferedImage>();
			
			int idx = 0;
			for(String path : imagePath) {
                                
                                File imageFile = new File("./webapps/ROOT/data/pigeon/" + path + ".png");
				images.add(ImageIO.read(imageFile));
				
				// create the new image, canvas size is the max. of both image sizes
				if(w < images.get(idx).getWidth()) {
					w = images.get(idx).getWidth();
				}
				
				h += images.get(idx).getHeight();
				
				idx++;
			}
	
			BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

			Graphics g = combined.getGraphics();
			int ch = 0;
			for(BufferedImage img : images) {
				g.drawImage(img, 0, ch, null);
				ch += img.getHeight();
			}

			File pic = new File("./webapps/ROOT/data/pigeon/" + filename + ".png");
			ImageIO.write(combined, "PNG", new File("./webapps/ROOT/data/pigeon/" + filename + ".png"));
			
			return ImageIO.read(pic);
		} catch(Exception e) {
			throw new MiniEugeneException(e.getMessage());
		}
	}
	
	public void show(Image img) {
		JFrame frame = new JFrame();
		
		// TODO: get image width + height
        frame.setSize(300, 300);
        JLabel label = new JLabel(new ImageIcon(img));
        frame.add(label);
        frame.setVisible(true);
	}

	/**
	 * toSBOL takes as input the name of the SBOL file into 
	 * which the solutions will be serialized following 
	 * the SBOL standard. 
	 * toSBOL utilizes the libSBOLj library 
	 * (www.sbolstandard.org -> libSBOL -> Software) 
	 *  
	 * @param filename  ... the path+filename of the SBOL file
	 * 
	 * @throws MiniEugeneException
	 */
	public void toSBOL(String filename) 
			throws MiniEugeneException {
		
		/*
		 * create the SBOLDocument
		 */
		SBOLDocument doc = this.sbolExport();
		
		/*
		 * write the document to the given filename
		 */
		try {
			FileOutputStream fos = new FileOutputStream(
				new File(filename));
			SBOLFactory.write(doc, fos);
		} catch(Exception e) {
			throw new MiniEugeneException(e.toString());
		}
	}
	
	private SBOLDocument sbolExport()  
			throws MiniEugeneException {
		if(null != this.solutions) {
			try {
				
	            /* 
	             * we SBOL up to 100 designs 
	             */
				if(this.solutions.size() > NR_OF_SBOL) {
					return new SBOLExporter().toSBOLDocument(this.getRandomSolutions(NR_OF_SBOL));
				} 
				return new SBOLExporter().toSBOLDocument(this.solutions);
				
			} catch(MiniEugeneException ee) {
				throw new MiniEugeneException(ee.getMessage());
			}
		}
		/*
		 * we return an empty document if there 
		 * are no solutions
		 */
		return SBOLFactory.createDocument();
	}
	
	private List<Component[]> getRandomSolutions(int N) {
		int[] idx = null;
		if(N != -1 && N < this.solutions.size()) {
			idx = generateRandomIndices(N, this.solutions.size());
		} else {
			idx = new int[this.solutions.size()];
			for(int i=0; i<this.solutions.size(); i++) {
				idx[i] = i;
			}
		}
//		
//		System.out.println(Arrays.toString(idx));
		
		List<Component[]> lst = new ArrayList<Component[]>(idx.length);
		for(int i=0; i<idx.length; i++) {
			lst.add(this.solutions.get(idx[i]));
		}
		return lst;
	}
	
	private int[] generateRandomIndices(int N, int range) {
		
		int[] idx = new int[N];
		Random generator = new Random();
		for(int i=0; i<N; i++) {
	
			// here, we also need to check if
			// the index is present already
			int ix = generator.nextInt( range );
			while(ArrayUtils.contains(idx, ix)) {
				ix = generator.nextInt( range );
			}
			idx[i] = ix;
		}
		return idx;
	}

	/**
	  * the method toEugene takes as input the name of the Eugene script 
	  * file into which the solutions will be serialized using 
	  * the Eugene language's syntax.
	  * 
	  * @param filename ... the desired Eugene script file name
	  *
	  */
	 public void toEugene(String filename) 
	 		throws MiniEugeneException {
		 
		StringBuilder sb = new StringBuilder();
		if(null == this.solutions || this.solutions.isEmpty())
			throw new MiniEugeneException("There are no solutions!");
	
		// first, we process the interactions
		if(null != this.interactions && !this.interactions.isEmpty()) {
			for(Interaction i : this.interactions) {
				sb.append(this.toEugeneInteraction(i)).append(NEWLINE);
			}
		}
		
		int d = 1;
		for(Component[] solution : this.solutions) {
			sb.append("Device d").append(d++).append("(");
			for(int i=0; i<solution.length; i++) {
				// Orientation
				if(!solution[i].isForward()) {
					sb.append("-");
				}
				
				// name of the part
				sb.append(solution[i].getName());
				if(i < solution.length-1) {
					sb.append(", ");
				}
				
			}
			sb.append(");").append(NEWLINE);
		}
		
		/*
		 * serialize the string to the file
		 */
		try {
			FileUtil.writeToFile(sb.toString(), new File(filename));
		} catch(Exception e) {
			throw new MiniEugeneException("Cannot serialize to "+filename+"!");
		}
	 }
	 
	 private String toEugeneInteraction(Interaction ia) {
			StringBuilder sb = new StringBuilder();

			if(ia.getType() == Interaction.InteractionType.REPRESSES ) {
				sb.append(toRepresses(ia));
			} else if(ia.getType() == Interaction.InteractionType.INDUCES ) {
				sb.append(toInduces(ia));
			} 
			
			return sb.toString();
	}
		
	private String toRepresses(Interaction ia) {
		String repressee = null;
		String repressor = null;
		for(Participation p : ia.getParticipations()) {
			if(p.getRole() == Participation.Role.REPRESSOR ) {
				repressor = p.getParticipant().getName();
			} else if(p.getRole() == Participation.Role.REPRESSEE ) {
				repressee = p.getParticipant().getName();
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(repressor).append(" ").append(ia.getType()).append(" ").append(repressee).append(";");
		return sb.toString();
	}

	private String toInduces(Interaction ia) {
		String inducee = null;
		String inducer = null;
		for(Participation p : ia.getParticipations()) {
			if(p.getRole() == Participation.Role.INDUCER ) {
				inducer = p.getParticipant().getName();
			} else if(p.getRole() == Participation.Role.INDUCEE ) {
				inducee = p.getParticipant().getName();
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(inducer).append(" ").append(ia.getType()).append(" ").append(inducee).append(";");
		return sb.toString();
	}

	 /**
	  * 
	  */
	 public void toConsole() {
		 if(null != this.solutions) {
			 StringBuilder sb = new StringBuilder();
			 for(Component[] solution : this.solutions) {
				 for(int i=0; i<solution.length; i++) {
					 // Orientation
					 if(!solution[i].isForward()) {
						 sb.append("-");
					 }
					
					 // name of the part
					 sb.append(solution[i].getName());
					 if(i < solution.length-1) {
						 sb.append(", ");
					 }
					
				 }
				 sb.append(NEWLINE);
			 }
			 
			 System.out.println(sb.toString());
		 }
	 }
	 
	 	 
}