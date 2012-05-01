package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public abstract class ControleurServeur extends Controleur {
	
	private static final long serialVersionUID = 8989454498758078372L;
	
	protected ModeleClientServeur client;
	
	public ControleurServeur() {
		
	}

	public ControleurServeur(Connexion connexion) {
		this(connexion, null);
	}

	public ControleurServeur(Connexion connexion, ModeleClientServeur client) {
		super(connexion);
		this.client = client;
	}
}
