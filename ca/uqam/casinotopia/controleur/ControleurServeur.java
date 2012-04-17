package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;

public abstract class ControleurServeur extends Controleur {

	public ControleurServeur(Connexion connexion) {
		super(connexion);
	}
	
	//private Connexion connexion1 = new Connexion();
	
	/*
	 * @return the connexion
	 */
	//public Connexion getConnexion() {
	//	return connexion1;
	//}

	/** 
	 * @param connexion the connexion to set
	 */
	//public void setConnexion(Connexion connexion) {
	//	this.connexion1 = connexion;
	//}
}
