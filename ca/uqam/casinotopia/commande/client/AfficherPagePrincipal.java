package ca.uqam.casinotopia.commande.client;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

public class AfficherPagePrincipal implements Commande {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8906289790821965654L;

	@Override
	public void action(Controleur controleur) {
		((ControleurClientPrincipal)controleur).getVueConnexionFrame().setVisible(false);
		((ControleurClientPrincipal)controleur).afficherFrameApplication();
		
	}

}
