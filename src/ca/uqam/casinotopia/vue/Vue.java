package ca.uqam.casinotopia.vue;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

import ca.uqam.casinotopia.observateur.Observateur;

/**
 * Classe abstraite représentant les vues de l'application Casinotopia
 * Chaque vue hérite de cette classe.
 */
@SuppressWarnings("serial")
public abstract class Vue extends JPanel implements Observateur {
	
	/**
	 * Tous les components dont l'attribut "Name" a été défini sont stocké dans un map.
	 * Il seront ensuite accessible facilement.
	 */
	protected Map<String, JComponent> componentMap = new HashMap<String, JComponent>();

	/**
	 * Chaque classe qui hérite doit définir sa propre implémentantions de la fonction addComponents.
	 * C'est ici que les component sont créés et ajouté à la vue.
	 */
	protected abstract void addComponents();

	/**
	 * Définir les paramètres globals de la vue
	 */
	protected void setPanelOptions() {
		// this.setLayout(null);
	}

	/**
	 * Créer le map des components
	 */
	protected void createComponentsMap() {
		this.componentMap = this.createComponentsMapRecursive(this);
	}

	/**
	 * Fonction pour la création du map des components.
	 * Elle est récursive pour ajouter les component à l'intérieur d'autres components, le cas échéant.
	 * 
	 * @param container Le container dans lequel on fait la récursion
	 * @return Un map contenant tous les components nommés
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
	 * Récupérer un component par son nom
	 * 
	 * @param name Le nom du component
	 * @return Le component demandé
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