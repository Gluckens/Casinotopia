package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;

/**
 * Classe abstraite représentant l'implémentation de base d'un controleur.
 */
public abstract class Controleur {
	
	/**
	 * La connexion permettant la communication entre le côté client et le côté serveur.
	 */
	protected Connexion connexion;

	public Controleur() {
		this.connexion = new Connexion();
	}

	/**
	 * 
	 * @param connexion La connexion associé au client
	 */
	public Controleur(Connexion connexion) {
		this.connexion = connexion;
	}

	/**
	 * Retourne la connexion associé au client
	 * 
	 * @return la connexion
	 */
	public Connexion getConnexion() {
		return this.connexion;
	}

	/**
	 * Définit la connexion associé au client
	 * 
	 * @param connexion
	 */
	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}
}