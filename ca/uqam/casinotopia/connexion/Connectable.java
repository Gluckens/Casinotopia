package ca.uqam.casinotopia.connexion;

import ca.uqam.casinotopia.Utilisateur;

public interface Connectable {

	/**
	 *  connecte l'utilisateur et ajoute this a la liste de connectable de l'utilisateur
	 * @param utilisateur
	 */
	public void connecter(Utilisateur utilisateur);
	
	/**
	 * itère dans la liste de connectable de l'utilisateur pour le déconnecté
	 * @param utilisateur
	 */
	public void deconnecter(Utilisateur utilisateur);
	
}
