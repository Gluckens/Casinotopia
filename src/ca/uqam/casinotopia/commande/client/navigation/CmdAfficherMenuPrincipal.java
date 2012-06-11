package ca.uqam.casinotopia.commande.client.navigation;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;

public class CmdAfficherMenuPrincipal implements CommandeClientControleurPrincipal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7507787639756100983L;

	@Override
	public void action(Controleur controleur) {
		((ControleurPrincipalClient) controleur).actionAfficherMenuPrincipal();
	}
}
