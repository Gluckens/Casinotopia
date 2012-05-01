package ca.uqam.casinotopia.controleur;

import java.io.Serializable;

import ca.uqam.casinotopia.connexion.Connexion;

public abstract class Controleur implements Serializable {

	private static final long serialVersionUID = 8167862234465864178L;
	
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
