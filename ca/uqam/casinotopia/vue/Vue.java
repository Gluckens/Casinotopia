package ca.uqam.casinotopia.vue;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

import ca.uqam.casinotopia.observateur.Observateur;

@SuppressWarnings("serial")
public abstract class Vue extends JPanel implements Observateur {
	protected Map<String, JComponent> componentMap = new HashMap<String, JComponent>();

	protected abstract void addComponents();

	/*
	 * public Vue(Controleur controleur) { this.controleur = controleur; }
	 */

	protected void setPanelOptions() {
		// this.setLayout(null);
	}

	protected void createComponentsMap() {
		/*
		 * Component[] components = this.getComponents(); for (int i=0; i <
		 * components.length; i++) { if(components[i].getName() != null) {
		 * this.componentMap.put(components[i].getName(), components[i]); } }
		 */
		this.componentMap = this.createComponentsMapRecursive(this);
	}

	private Map<String, JComponent> createComponentsMapRecursive(JComponent container) {
		Map<String, JComponent> containerComponentMap = new HashMap<String, JComponent>();

		Component[] components = container.getComponents();
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof JComponent) {
				JComponent jComp = (JComponent) components[i];
				if (components[i].getName() != null) {
					containerComponentMap.put(jComp.getName(), jComp);
				}

				if (jComp.getComponentCount() > 0) {
					containerComponentMap.putAll(this.createComponentsMapRecursive(jComp));
				}
			}
		}

		return containerComponentMap;
	}

	public JComponent getComponentByName(String name) {
		if (this.componentMap.containsKey(name)) {
			return this.componentMap.get(name);
		}
		else {
			return null;
		}
	}

}
