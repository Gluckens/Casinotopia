package ca.uqam.casinotopia.connexion;

import ca.uqam.casinotopia.objet.Utilisateur;

public interface Connectable {

	/**
	 * Connecter l'utilisateur � un objet
	 * 
	 * @param utilisateur L'utilisateur a connecter
	 */
	public void connecter(Utilisateur utilisateur);

	/**
	 * Parcourir la liste des connectables de l'utilisateur et afin de le d�connecter
	 * 
	 * @param utilisateur L'utilisateur � d�connecter
	 */
	public void deconnecter(Utilisateur utilisateur);

}