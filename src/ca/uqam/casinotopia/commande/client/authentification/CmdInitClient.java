package ca.uqam.casinotopia.commande.client.authentification;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;

public class CmdInitClient implements CommandeClientControleurPrincipal {
	
	private static final long serialVersionUID = 612826343037164614L;
	
	private ModeleClientClient modele;
	
	public CmdInitClient(ModeleClientClient modele) {
		this.modele = modele;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurPrincipalClient) controleur).actionInitClient(this.modele);
	}

}
