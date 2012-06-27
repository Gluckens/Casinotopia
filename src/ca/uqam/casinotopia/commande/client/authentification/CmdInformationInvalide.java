package ca.uqam.casinotopia.commande.client.authentification;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;

public class CmdInformationInvalide implements CommandeClientControleurPrincipal {
	
	private static final long serialVersionUID = -9186740704927954025L;
	
	private String message = "";

	public CmdInformationInvalide(String message) {
		this.message = message;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurPrincipalClient) controleur).setMessageConnexionErreur(this.message);
	}
}