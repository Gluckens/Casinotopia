package ca.uqam.casinotopia.commande.client.roulette;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;

public class CmdQuitterPartieRouletteClient implements CommandeClientControleurPrincipal {

	private static final long serialVersionUID = 5251282327843831449L;

	@Override
	public void action(Controleur controleur) {
		((ControleurPrincipalClient) controleur).actionQuitterPartieRouletteClient();
	}

}
