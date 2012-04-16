package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;

public abstract class ControleurClient extends Controleur {
	
	public ControleurClient() {
		
	}
	
	public ControleurClient(Connexion connexion) {
		super(connexion);
	}
}
