package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.serveur.ControleurClientServeur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public abstract class ControleurServeur extends Controleur {
	
	private static final long serialVersionUID = 8989454498758078372L;
	
	protected ControleurServeurThread ctrlThread;
	
	public ControleurServeur() {
		
	}

	public ControleurServeur(Connexion connexion) {
		this(connexion, null);
	}

	public ControleurServeur(Connexion connexion, ControleurServeurThread ctrlThread) {
		super(connexion);
		this.ctrlThread = ctrlThread;
	}
	
	public ControleurServeur getControleur(String nom) {
		return this.ctrlThread.getControleur(nom);
	}
	
	public ModeleClientServeur getModeleClient() {
		ControleurClientServeur ctrlClient = (ControleurClientServeur) this.getControleur("ControleurClientServeur");
		if(ctrlClient == null) {
			return null;
		}
		
		return ctrlClient.getModele();
	}
}
