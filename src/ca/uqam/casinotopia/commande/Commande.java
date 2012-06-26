package ca.uqam.casinotopia.commande;

import java.io.Serializable;

import ca.uqam.casinotopia.controleur.Controleur;

/**
 * Interface de base représentant les commandes clients et serveurs
 */
public interface Commande extends Serializable {

	/**
	 * Action lancée lors de la réception de la commande
	 * 
	 * @param controleur Le controleur sur lequel on fait l'action
	 */
	public void action(Controleur controleur);
}