package ca.uqam.casinotopia.controleur.serveur;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public class ControleurChatServeur extends ControleurServeur {

	private static final long serialVersionUID = -1017191811573157577L;

	public ControleurChatServeur(Connexion connexion, ModeleClientServeur client) {
		super(connexion, client);
	}
}
