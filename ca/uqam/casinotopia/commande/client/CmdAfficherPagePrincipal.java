package ca.uqam.casinotopia.commande.client;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;

public class CmdAfficherPagePrincipal implements CommandeClientControleurPrincipal {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3802322880136944538L;

	@Override
	public void action(Controleur controleur) {
		//((ControleurPrincipalClient)controleur).getFrameConnexion().setVisible(false);
		//((ControleurPrincipalClient)controleur).afficherFrameApplication();
		
	}
}
