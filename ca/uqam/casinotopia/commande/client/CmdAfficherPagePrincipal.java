package ca.uqam.casinotopia.commande.client;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

public class CmdAfficherPagePrincipal implements CommandeClientControleurPrincipal {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3802322880136944538L;

	@Override
	public void action(Controleur controleur) {
		((ControleurClientPrincipal)controleur).getVueConnexionFrame().setVisible(false);
		//((ControleurClientPrincipal)controleur).afficherFrameApplication();
		
	}
}
