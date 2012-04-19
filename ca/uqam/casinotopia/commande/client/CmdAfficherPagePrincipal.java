package ca.uqam.casinotopia.commande.client;

import ca.uqam.casinotopia.commande.CommandeClientControleurChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

public class CmdAfficherPagePrincipal implements CommandeClientControleurChat {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3623151295646193586L;

	@Override
	public void action(Controleur controleur) {
		((ControleurClientPrincipal)controleur).getVueConnexionFrame().setVisible(false);
		((ControleurClientPrincipal)controleur).afficherFrameApplication();
		
	}

}