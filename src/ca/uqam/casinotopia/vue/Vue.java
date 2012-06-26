package ca.uqam.casinotopia.vue;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

import ca.uqam.casinotopia.observateur.Observateur;

/**
 * Classe abstraite repr�sentant les vues de l'application Casinotopia
 * Chaque vue h�rite de cette classe.
 */
@SuppressWarnings("serial")
public abstract class Vue extends JPanel implements Observateur {
	
	/**
	 * Tous les components dont l'attribut "Name" a �t� d�fini sont stock� dans un map.
	 * Il seront ensuite accessible facilement.
	 */
	protected Map<String, JComponent> componentMap = new HashMap<String, JComponent>();

	/**
	 * Chaque classe qui h�rite doit d�finir sa propre impl�mentantions de la fonction addComponents.
	 * C'est ici que les component sont cr��s et ajout� � la vue.
	 */
	protected abstract void addComponents();

	/**
	 * D�finir les param�tres globals de la vue
	 */
	protected void setPanelOptions() {
		// this.setLayout(null);
	}

	/**
	 * Cr�er le map des components
	 */
	protected void createComponentsMap() {
		this.componentMap = this.createComponentsMapRecursive(this);
	}

	/**
	 * Fonction pour la cr�ation du map des components.
	 * Elle est r�cursive pour ajouter les component � l'int�rieur d'autres components, le cas �ch�ant.
	 * 
	 * @param container Le container dans lequel on fait la r�cursion
	 * @return Un map contenant tous les components nomm�s
	 */
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

	/**
	 * R�cup�rer un component par son nom
	 * 
	 * @param name Le nom du component
	 * @return Le component demand�
	 */
	public JComponent getComponentByName(String nom) {
		if (this.componentMap.containsKey(nom)) {
			return this.componentMap.get(nom);
		}
		else {
			return null;
		}
	}

	public Map<String, JComponent> getComponentMap() {
		return this.componentMap;
	}
}