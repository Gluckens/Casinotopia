package ca.uqam.casinotopia.commande.client;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

public class CmdAfficherMenuPrincipal implements CommandeClientControleurPrincipal {

	@Override
	public void action(Controleur controleur) {
		((ControleurClientPrincipal) controleur).afficherMenuPrincipal();
	}

}
