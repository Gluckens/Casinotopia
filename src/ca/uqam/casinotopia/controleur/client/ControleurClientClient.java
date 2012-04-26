package ca.uqam.casinotopia.controleur.client;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;

public class ControleurClientClient extends ControleurClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5068429727786824014L;

	public ControleurClientClient(Connexion connexion, ModelePrincipalClient modeleNavigation) {
		super(connexion, modeleNavigation);
	}
}
