package ca.uqam.casinotopia.commande.client.compte;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;


public class CmdInformationCreationCompte implements CommandeClientControleurPrincipal {

	private static final long serialVersionUID = 8911438224598458768L;

	String message = "";

	public CmdInformationCreationCompte(String message) {
		this.message = message;
	}

	@Override
	public void action(Controleur controleur) {

		((ControleurPrincipalClient) controleur).setMessageInformationCreationCompte(this.message);
	}
}
