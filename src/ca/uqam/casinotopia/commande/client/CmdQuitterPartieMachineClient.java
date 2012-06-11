package ca.uqam.casinotopia.commande.client;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;

public class CmdQuitterPartieMachineClient implements CommandeClientControleurPrincipal {

	private static final long serialVersionUID = -5980144596846210799L;

	@Override
	public void action(Controleur controleur) {
		((ControleurPrincipalClient) controleur).actionQuitterPartieMachineClient();
	}

}
