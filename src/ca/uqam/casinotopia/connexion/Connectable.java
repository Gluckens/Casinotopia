package ca.uqam.casinotopia.connexion;

import java.io.Serializable;

import ca.uqam.casinotopia.Utilisateur;

public interface Connectable extends Serializable {

	/**
	 * connecte l'utilisateur et ajoute this a la liste de connectable de
	 * l'utilisateur
	 * 
	 * @param utilisateur
	 */
	public void connecter(Utilisateur utilisateur);

	/**
	 * it�re dans la liste de connectable de l'utilisateur pour le d�connect�
	 * 
	 * @param utilisateur
	 */
	public void deconnecter(Utilisateur utilisateur);

}
