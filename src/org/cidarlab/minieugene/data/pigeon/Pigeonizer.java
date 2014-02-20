package org.cidarlab.minieugene.data.pigeon;

import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.exception.EugeneException;
import org.cidarlab.minieugene.interaction.Interaction;

public class Pigeonizer {

	private static Map<String, Character> Eugene2PigeonMap;
	private Map<String, Integer> colors;
		
	public Pigeonizer() {
		if(Eugene2PigeonMap == null) {
			loadMap();
		}
		colors = new HashMap<String, Integer>();
	}
	
	public URI pigeonize(Component[] solution) 
			throws EugeneException {
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<solution.length; i++) {
			sb.append(toPigeon(solution[i])).append("\r\n");
		}
		
		/*
		 * finally, we add the arcs line
		 */
		sb.append("# Arcs").append("\r\n");

		/*
		 * here, we need to figure out the ``regulatory interactions''
		 * therefore, we need some access to the symbol tables in 
		 * that we keep track of all user-specified interactions ...
		 */
		
		WeyekinPoster.setPigeonText(sb.toString());
		
		return WeyekinPoster.getMyBirdsURL();		
	}
	
	public URI pigeonize(List<Component[]> solutions, Set<Interaction> interactions) 
			throws EugeneException {

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

		/*
		 * finally, we add the arcs line
		 */
		sb.append("# Arcs").append("\r\n");
		
		/*
		 * here, we iterate of the interactions
		 */
		if(null != interactions && !(interactions.isEmpty())) {
			Iterator<Interaction> it = interactions.iterator();
			while(it.hasNext()) {
				sb.append((it.next()).toPigeon()).append("\r\n");
			}
		}
		
//		System.out.println(sb.toString());
		WeyekinPoster.setPigeonText(sb.toString());
		
		return WeyekinPoster.getMyBirdsURL();		
	}
	
	private String toPigeon(Component symbol) {

		String s = symbol.getName();
		StringBuilder sb = new StringBuilder();
		if(s.startsWith("p") || s.startsWith("P")) {
			/*
			 * promoter
			 */
			if(!symbol.isForward()) {
				sb.append("<");
			}			
			sb.append("p ").append(s).append(" ").append(getColor(s));
		} else if(s.startsWith("r")) {
			/*
			 * rbs
			 */
			if(!symbol.isForward()) {
				sb.append("<");
			}			
			sb.append("r ").append(s).append(" ").append(getColor(s));
		} else if(s.startsWith("c") || s.startsWith("g")  || s.startsWith("C")  || s.startsWith("G")) {
			/*
			 * coding sequence v gene
			 */
			if(!symbol.isForward()) {
				sb.append("<");
			}			
			sb.append("c ").append(s).append(" ").append(getColor(s));
		} else if(s.startsWith("t") || s.startsWith("T")) {
			/*
			 * terminator
			 */
			if(!symbol.isForward()) {
				sb.append("<");
			}			
			sb.append("t ").append(s).append(" ").append(getColor(s));
		} else {
			char type = getPigeonType(s);
			if(type != '>') {
				sb.append(type);
			} if(!symbol.isForward() && type!='?') {
				sb.append("<");
			}			
			sb.append(" ").append(s).append(" ").append(getColor(s));
		}
		
		return sb.toString();
	}
	
	private int getColor(String s) {
		if(this.colors.containsKey(s)) {
			return colors.get(s);
		}
		/*
		 * otherwise, we put the name into the coloring map
		 */
		int color = getRandomColor();
		this.colors.put(s, color);
		return color;
	}
	
	
	private static void loadMap() {
		Eugene2PigeonMap = new HashMap<String, Character>();
		
		/*
		 * key   ... Eugene type
		 * value ... Pigeon type 
		 */
		Eugene2PigeonMap.put("p", 'p');
		Eugene2PigeonMap.put("PROMOTER", 'p');
		Eugene2PigeonMap.put("INDUCIBLEPROMOTER", 'p');
		Eugene2PigeonMap.put("CONSTITUTIVEPROMOTER", 'p');
		Eugene2PigeonMap.put("REGULATEDPROMOTER", 'p');

		Eugene2PigeonMap.put("RPROMOTER", 'p');
		Eugene2PigeonMap.put("IPROMOTER", 'p');
		Eugene2PigeonMap.put("PROMOTER", 'p');
		
		Eugene2PigeonMap.put("r", 'r');
		Eugene2PigeonMap.put("RBS", 'r');
		
		Eugene2PigeonMap.put("c", 'c');
		Eugene2PigeonMap.put("RECOMBINASE", 'c');
		Eugene2PigeonMap.put("PACKAGINGSITE", 'c');
		Eugene2PigeonMap.put("LYTICREPLICON", 'c');		
		Eugene2PigeonMap.put("CODINGSEQUENCE", 'c');
		Eugene2PigeonMap.put("RIBOZYME", 'c');
		Eugene2PigeonMap.put("LEADER", 'c');
		Eugene2PigeonMap.put("CDS", 'c');
		Eugene2PigeonMap.put("REPRESSOR", 'c');
		Eugene2PigeonMap.put("REPORTER", 'c');
		Eugene2PigeonMap.put("INDUCER", 'c');
		Eugene2PigeonMap.put("GENE", 'c');
		
		Eugene2PigeonMap.put("t", 't');
		Eugene2PigeonMap.put("TERMINATOR", 't');

		Eugene2PigeonMap.put("MO", 'o');
		Eugene2PigeonMap.put("FUSIONSITE", 'o');
		Eugene2PigeonMap.put("RECSITE", '>');
		Eugene2PigeonMap.put("ANTIREPRESSOR", 'g');
		Eugene2PigeonMap.put("SPACER", 's');
	}
	
	private static char getPigeonType(String eugeneType) {
//		System.out.println("[getPigeonType] -> "+eugeneType);
		if(Eugene2PigeonMap.containsKey(eugeneType.toUpperCase())) {
			return (Eugene2PigeonMap.get(eugeneType.toUpperCase())).charValue();
		}
		return '?';
	}
	
	private static final int COLOR_MIN = 1;
	private static final int COLOR_MAX = 13;
	
	private static int getRandomColor() {
		return COLOR_MIN + (int)(Math.random() * ((COLOR_MAX - COLOR_MIN) + 1));
	}
}
