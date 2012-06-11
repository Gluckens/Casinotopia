package ca.uqam.casinotopia.commande.client.salle;

import ca.uqam.casinotopia.commande.CommandeClientControleurSalle;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurSalleClient;

public class CmdRetirerClientSalle implements CommandeClientControleurSalle {
	
	private static final long serialVersionUID = -5821954583094356578L;
	
	private int idClient;
	
	public CmdRetirerClientSalle(int idClient) {
		this.idClient = idClient;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurSalleClient) controleur).actionRetirerClientSalle(this.idClient);

	}

}
