package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;

public abstract class Controleur {
	
	protected Connexion connexion;

	public Controleur() {
		this.connexion = new Connexion();
	}

	public Controleur(Connexion connexion) {
		this.connexion = connexion;
	}

	/**
	 * @return the connexion
	 */
	public Connexion getConnexion() {
		return this.connexion;
	}

	/**
	 * @param connexion
	 *            the connexion to set
	 */
	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}
}