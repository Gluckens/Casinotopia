package ca.uqam.casinotopia.controleur.serveur;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public class ControleurClientServeur extends ControleurServeur {

	private static final long serialVersionUID = 204499404449988843L;

	public ControleurClientServeur(Connexion connexion, ModeleClientServeur client) {
		super(connexion, client);
	}
}
