package ca.uqam.casinotopia.commande.client.salle;

import ca.uqam.casinotopia.commande.CommandeClientControleurSalle;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurSalleClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;

public class CmdAjouterClientSalle implements CommandeClientControleurSalle {
	
	private static final long serialVersionUID = 6976863856053615180L;
	
	private ModeleClientClient nouveauClient;
	
	public CmdAjouterClientSalle(ModeleClientClient nouveauClient) {
		this.nouveauClient = nouveauClient;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurSalleClient) controleur).actionAjouterClientSalle(this.nouveauClient);
	}
}