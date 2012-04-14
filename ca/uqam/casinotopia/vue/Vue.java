package ca.uqam.casinotopia.vue;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Vue extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4498865400974010365L;
	
	
	protected Map<String, Component> componentMap = new HashMap<String, Component>();
	
	protected abstract void addComponents();
	
	protected void setPanelOptions() {
		this.setLayout(null);
	}
	
	protected void createComponentsMap() {
		Component[] components = this.getComponents();
        for (int i=0; i < components.length; i++) {
                this.componentMap.put(components[i].getName(), components[i]);
        }
	}
	
	public Component getComponentByName(String name) {
		if (this.componentMap.containsKey(name)) {
            return this.componentMap.get(name);
	    }
	    else {
	    	return null;
	    }
	}

}
