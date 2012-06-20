package ca.uqam.casinotopia.commande.client.machine;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;

public class CmdAfficherJeuMachine implements CommandeClientControleurPrincipal {

	
	private static final long serialVersionUID = 8314399057813220106L;

	@Override
	public void action(Controleur controleur) {
		((ControleurPrincipalClient) controleur).actionAfficherJeuMachine();

	}

}
