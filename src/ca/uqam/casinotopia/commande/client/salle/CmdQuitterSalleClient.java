package ca.uqam.casinotopia.commande.client.salle;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;

public class CmdQuitterSalleClient implements CommandeClientControleurPrincipal {

	private static final long serialVersionUID = 6337531978606582486L;

	@Override
	public void action(Controleur controleur) {
		((ControleurPrincipalClient) controleur).actionQuitterSalleClient();
	}
}
