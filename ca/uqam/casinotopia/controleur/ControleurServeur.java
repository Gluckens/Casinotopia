package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;

public abstract class ControleurServeur extends Controleur {
	
	public ControleurServeur() {
		
	}

	public ControleurServeur(Connexion connexion) {
		super(connexion);
	}
}
