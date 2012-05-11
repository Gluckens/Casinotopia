package ca.uqam.casinotopia.commande.client;

import java.awt.Point;

import ca.uqam.casinotopia.commande.CommandeClientControleurSalle;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurSalleClient;

public class CmdAfficherDeplacementAvatar implements CommandeClientControleurSalle {
	
	private static final long serialVersionUID = 2683720680472219714L;
	
	private int idClient;
	private Point position;
	
	public CmdAfficherDeplacementAvatar(int idClient, Point position) {
		this.idClient = idClient;
		this.position = position;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurSalleClient) controleur).actionAfficherDeplacementAvatar(this.idClient, this.position);
	}
}
