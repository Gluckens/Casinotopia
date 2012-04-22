package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;

public abstract class ControleurServeur extends Controleur {
	
	private static final long serialVersionUID = 3250928097030158541L;

	public ControleurServeur() {
		
	}

	public ControleurServeur(Connexion connexion) {
		super(connexion);
	}
}
