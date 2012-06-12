package ca.uqam.casinotopia.commande.client.navigation;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.commande.CommandeClientControleurSalle;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;
import ca.uqam.casinotopia.controleur.client.ControleurSalleClient;

//public class CmdAfficherAttentePartie implements CommandeClientControleurSalle {
public class CmdAfficherAttentePartie implements CommandeClientControleurPrincipal {

	private static final long serialVersionUID = -3284141121309181864L;

	@Override
	public void action(Controleur controleur) {
		//((ControleurSalleClient) controleur).actionAfficherAttentePartie();
		((ControleurPrincipalClient) controleur).actionAfficherAttentePartie();
	}
}
