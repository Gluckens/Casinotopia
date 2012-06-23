package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;

/**
 * Classe abstraite repr�sentant l'impl�mentation de base d'un controleur.
 */
public abstract class Controleur {
	
	/**
	 * La connexion permettant la communication entre le c�t� client et le c�t� serveur.
	 */
	protected Connexion connexion;

	public Controleur() {
		this.connexion = new Connexion();
	}

	/**
	 * 
	 * @param connexion La connexion associ� au client
	 */
	public Controleur(Connexion connexion) {
		this.connexion = connexion;
	}

	/**
	 * Retourne la connexion associ� au client
	 * 
	 * @return la connexion
	 */
	public Connexion getConnexion() {
		return this.connexion;
	}

	/**
	 * D�finit la connexion associ� au client
	 * 
	 * @param connexion
	 */
	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}
}