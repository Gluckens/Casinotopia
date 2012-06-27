package ca.uqam.casinotopia.commande.client.salle;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;

public class CmdQuitterSalleClient implements CommandeClientControleurPrincipal {

	private static final long serialVersionUID = -280970579527192753L;
	
	private boolean afficherMenu;
	
	public CmdQuitterSalleClient(boolean afficherMenu) {
		this.afficherMenu = afficherMenu;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurPrincipalClient) controleur).actionQuitterSalleClient(this.afficherMenu);
	}
}