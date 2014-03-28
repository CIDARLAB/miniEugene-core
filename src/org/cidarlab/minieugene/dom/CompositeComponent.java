package org.cidarlab.minieugene.dom;

import java.util.ArrayList;
import java.util.List;

public class CompositeComponent 
		extends Component {

	private static final long serialVersionUID = 4774134879565380842L;
	
	private List<Component> components;
	
	/**
	 * 
	 * @param name
	 */
	public CompositeComponent(String name) {
		super(name);
		this.components = new ArrayList<Component>();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Component> getComponents() {
		return this.components;
	}

}
