package ca.uqam.casinotopia.controleur.client;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;

public class ControleurClientClient extends ControleurClient {

	private static final long serialVersionUID = 2062988942063244845L;

	public ControleurClientClient(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNavigation) {
		super(connexion, client, modeleNavigation);
	}
	
	public void modifierSolde(int nouveauSolde) {
		this.client.setSolde(nouveauSolde);
	}
}
