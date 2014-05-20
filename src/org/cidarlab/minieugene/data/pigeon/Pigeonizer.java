package org.cidarlab.minieugene.data.pigeon;

import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cidarlab.minieugene.constants.PartTypesTable;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.interaction.Interaction;
import org.cidarlab.minieugene.interaction.Interaction.InteractionType;
import org.cidarlab.minieugene.interaction.Participation;

public class Pigeonizer {

	private Map<String, Integer> colors;
	private boolean label;
	
	/**
	 * default constructor
	 * 
	 * i.e. random coloring + labeling
	 */
	public Pigeonizer() {
		colors = new HashMap<String, Integer>();
		this.label = true;
	}
	
	/**
	 * 
	 * @param colors ... the desired color coding map in that the keys are the part names and the values are the colors [1-14]  
	 * @param label  ... if true, then the Pigeon symbols will be labelled
	 */
	public Pigeonizer(Map<String, Integer> colors, boolean label) {

		if(null == colors) {
			this.colors = new HashMap<String, Integer>();
		} else {
			this.colors = colors;
		}
		this.label = label;
	}


	/**
	 * The pigeonize/2 method visualizes a list of solutions and a list of interactions
	 * using Pigeon.
	 * 
	 * Therefore, we first compile the solutions and the interactions into a Pigeon script,
	 * send the script to the Pigeon server, which returns an URL of the resulting 
	 * Pigeon image. 
	 * 
	 * The pigeonize/2 method returns the URL of the Pigeon image that resides on 
	 * the Pigeon server.
	 * 
	 * @param solutions     ... a list of solutions
	 * @param interactions  ... a list of interactions (i.e. the # Arcs)
	 * @return              ... a URI of the Pigeon image
	 * @throws EugeneException
	 */
	public URI pigeonize(List<Component[]> solutions, Set<Interaction> interactions) 
			throws EugeneException {

		/*
		 * Compilation to Pigeon script
		 */
		
		// SOLUTIONS
		StringBuilder sb = new StringBuilder();
		for(int k=0; k<solutions.size(); k++) {			
			Component[] solution = solutions.get(k);
			for(Component symbol : solution) {
				sb.append(toPigeon(symbol)).append("\r\n");
			}			
			if(k < solutions.size() - 1) {
				sb.append("\r\n");
			}
		}

		// INTERACTIONS
		sb.append(this.toPigeon(interactions));
		
		/*
		 * Sending the script to the Pigeon server. 
		 */
		WeyekinPoster.setPigeonText(sb.toString());
		
		/*
		 * return the resulting URL
		 */
		return WeyekinPoster.getMyBirdsURL();		
	}
	
	/**
	 * The pigeonizeSingle/2 method visualizes one single solution using 
	 * Pigeon.
	 * 
	 * It takes as input the solution as Component array (Component[]) and 
	 * a set of interactions among the solution's components.
	 * 
	 * @param solution     ... a Component[] representing the solution
	 * @param interactions ... a set of interactions representing the Arcs
	 * @return
	 * @throws EugeneException
	 */
	public URI pigeonizeSingle(Component[] solution, Set<Interaction> interactions) 
			throws EugeneException {

		/*
		 * COMPILATION into a Pigeon script
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("fontsize 1.5\r\n");
		for(Component symbol : solution) {
			sb.append(toPigeon(symbol)).append("\r\n");
		}

		// INTERACTIONS
		sb.append(this.toPigeon(interactions));

		
		/*
		 * sending the Pigeon script to the Pigeon server
		 */
		WeyekinPoster.setPigeonText(sb.toString());	
		
		/*
		 * returning the URL of the Pigeon image 
		 */
		return WeyekinPoster.getMyBirdsURL();		
	}

	/**
	 * The toPigeon/1 method compiles a miniEugene Component 
	 * into a corresponding Pigeon line
	 * 
	 * The toPigeon/1 method is not publicly available. It 
	 * is only being used by and can only be invoked through 
	 * the pigeonize methods.
	 * 
	 * @param symbol  ... the to converting Component
	 * 
	 * @return the corresponding Pigeon line
	 */
	private String toPigeon(Component component) {

		StringBuilder sb = new StringBuilder();
		
		// first, let's get the Pigeon letter
		// corresponding to the component's type
		char letter = PartTypesTable.toPigeonLetter(component.getTypeId());

		// reverse invertase site
		if(!component.isForward() && letter == '>') {
			sb.append("<");		
		// unknown part type => orientation irrelevant
		} else if(letter == '?') {
			sb.append("?");			
		// reverse oriented known part type
		} else if(!component.isForward()) {
			sb.append("<").append(letter);
		// forward oriented known part type	
		} else {
			sb.append(letter);
		}

		// name + color
		String s = component.getName();
		sb.append(" ").append(s).append(" ").append(getColor(s));

		// is labeling turned on?
		if(!this.label) {
			sb.append(" nl");
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param interactions
	 * @return
	 */
	private String toPigeon(Set<Interaction> interactions) {
		StringBuilder sb = new StringBuilder();
		sb.append("# Arcs").append("\r\n");
		if(null != interactions && !(interactions.isEmpty())) {
			Iterator<Interaction> it = interactions.iterator();
			while(it.hasNext()) {
				Interaction ia = it.next();
				if(InteractionType.REPRESSES == ia.getType()) {
					sb.append(toPigeonRep(ia));
				} else if(InteractionType.INDUCES == ia.getType()) {
					sb.append(toPigeonInd(ia));
				}
				sb.append("\r\n");
			}
		}
		return sb.toString();
	}
	
	/*
	 * REPRESSES -> REP
	 */
	private String toPigeonRep(Interaction represses) {
		StringBuilder sb = new StringBuilder();
		
		// get the individual roles
		String repressor = null;
		String repressee = null;		
		for(Participation part : represses.getParticipations()) {
			if(Participation.Role.REPRESSOR == part.getRole()) {
				repressor = part.getParticipant().getName();
			} else if (Participation.Role.REPRESSEE == part.getRole()) {
				repressee = part.getParticipant().getName();
			}
		}

		// finally, we build the Pigeon statement
		sb.append(repressor);
		sb.append(" rep ");
		sb.append(repressee);
		
		return sb.toString();
	}
	
	/*
	 * REPRESSES -> REP
	 */
	private String toPigeonInd(Interaction induces) {
		StringBuilder sb = new StringBuilder();
		
		// get the individual roles
		String inducer = null;
		String inducee = null;		
		for(Participation part : induces.getParticipations()) {
			if(Participation.Role.INDUCER == part.getRole()) {
				inducer = part.getParticipant().getName();
			} else if (Participation.Role.INDUCEE == part.getRole()) {
				inducee = part.getParticipant().getName();
			}
		}

		// finally, we build the Pigeon statement
		sb.append(inducer);
		sb.append(" ing ");
		sb.append(inducee);
		
		return sb.toString();
	}

	/**
	 * 
	 * @param s  ... the name of a component
	 * @return   the color code of the component
	 */
	private int getColor(String s) {
		if(this.colors.containsKey(s)) {
			int color = colors.get(s);
			if(color <= 1) {
				return 1;
			} else if(color >= 14) {
				return 14;
			}
			return color;
		}

		/*
		 * otherwise, we put the name into the coloring map
		 */
		int color = getRandomColor();
		this.colors.put(s, color);
		return color;
	}
	
	private static final int COLOR_MIN = 1;
	private static final int COLOR_MAX = 14;
	
	private static int getRandomColor() {
		return COLOR_MIN + (int)(Math.random() * ((COLOR_MAX - COLOR_MIN) + 1));
	}
}
