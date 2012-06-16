package ca.uqam.casinotopia.connexion;

import ca.uqam.casinotopia.objet.Utilisateur;

public interface Connectable {

	/**
	 * Connecter l'utilisateur à un objet
	 * 
	 * @param utilisateur L'utilisateur a connecter
	 */
	public void connecter(Utilisateur utilisateur);

	/**
	 * Parcourir la liste des connectables de l'utilisateur et afin de le déconnecter
	 * 
	 * @param utilisateur L'utilisateur à déconnecter
	 */
	public void deconnecter(Utilisateur utilisateur);

}