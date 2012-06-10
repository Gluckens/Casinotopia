package ca.uqam.casinotopia.commande.client;

import ca.uqam.casinotopia.commande.CommandeClientControleurSalle;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurSalleClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;

public class CmdAjouterClientSalle implements CommandeClientControleurSalle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8836544415840517077L;
	private ModeleClientClient nouveauClient;
	
	public CmdAjouterClientSalle(ModeleClientClient nouveauClient) {
		this.nouveauClient = nouveauClient;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurSalleClient) controleur).actionAjouterClientSalle(this.nouveauClient);
	}

}
