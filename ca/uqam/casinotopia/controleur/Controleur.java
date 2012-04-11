package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;

public abstract class Controleur {

	private Connexion connexion;

	/**
	 * @return the connexion
	 */
	public Connexion getConnexion() {
		return connexion;
	}

	/**
	 * @param connexion the connexion to set
	 */
	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}
	
	
}
