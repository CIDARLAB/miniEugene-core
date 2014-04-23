package org.cidarlab.minieugene.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.lang3.ArrayUtils;
import org.cidarlab.minieugene.data.pigeon.Pigeonizer;
import org.cidarlab.minieugene.data.sbol.SBOLExporter;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.interaction.Interaction;
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
	 * @throws EugeneException
	 */
	public URI toPigeon() 
			throws EugeneException {

		if(null != solutions) {
			
			try {
	        	Pigeonizer pigeon = new Pigeonizer();
	            
	            /* 
	             * we visualize up to NR_OF_PIGEON designs 
	             */
	        	if(this.solutions.size() > NR_OF_PIGEON) {
	            	return pigeon.pigeonize(
	            			this.getRandomSolutions(NR_OF_PIGEON), 
	            			this.interactions);
	        	}
	        	
	        	return pigeon.pigeonize(
	        			this.solutions, 
	        			this.interactions);
	        	
	        } catch(Exception e) {
//	        	e.printStackTrace();
	            throw new EugeneException(e.getMessage());
	        }
		}
		
		return URI.create("");
	}

	public Image pigeonize(String filename, Map<String, Integer> colors, boolean label, int N) 
			throws EugeneException {

		if(null != solutions) {
			
			try {
	        	Pigeonizer pigeon = new Pigeonizer(colors, label);
	            
	        	List<URI> uris = new ArrayList<URI>();
	            /* 
	             * we visualize up to NR_OF_PIGEON designs 
	             */
	        	List<Component[]> sols = this.solutions;
	        	
	        	if(N != -1 && this.solutions.size() > N) {
	        		sols = this.getRandomSolutions(N); 
	        	}
	        	
	        	for(Component[] solution : sols) {
	        		uris.add(pigeon.pigeonizeSingle(solution, this.interactions));
	        	}
	        	
	        	return pigeonize(uris, filename);
	        	// here, we need to merge all images...
	        	
	        } catch(Exception e) {
//	        	e.printStackTrace();
	            throw new EugeneException(e.getMessage());
	        }
		}
		
        throw new EugeneException("nothing to better visualize!");
	}
	
	private Image pigeonize(List<URI> uris, String filename) 
			throws EugeneException {

		try {
			int w = 0;
			int h = 0;
			
			List<BufferedImage> images = new ArrayList<BufferedImage>();
			
			int idx = 0;
			for(URI uri : uris) {

				images.add(ImageIO.read(uri.toURL()));
				
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

			File pic = new File(filename);
			ImageIO.write(combined, "PNG", new File(filename));
			
			return ImageIO.read(pic);
		} catch(Exception e) {
			throw new EugeneException(e.getMessage());
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
	 * @throws EugeneException
	 */
	public void toSBOL(String filename) 
			throws EugeneException {
		
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
			throw new EugeneException(e.toString());
		}
	}
	
	private SBOLDocument sbolExport()  
			throws EugeneException {
		if(null != this.solutions) {
			try {
				
	            /* 
	             * we SBOL up to 100 designs 
	             */
				if(this.solutions.size() > NR_OF_SBOL) {
					return new SBOLExporter().toSBOLDocument(this.getRandomSolutions(NR_OF_SBOL));
				} 
				return new SBOLExporter().toSBOLDocument(this.solutions);
				
			} catch(EugeneException ee) {
				throw new EugeneException(ee.getMessage());
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
	 		throws EugeneException {
		 
		StringBuilder sb = new StringBuilder();
		if(null == this.solutions || this.solutions.isEmpty())
			throw new EugeneException("There are no solutions!");
	
		// first, we process the interactions
		if(null != this.interactions && !this.interactions.isEmpty()) {
			for(Interaction i : this.interactions) {
				sb.append(i.toEugene()).append(NEWLINE);
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
			throw new EugeneException("Cannot serialize to "+filename+"!");
		}
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