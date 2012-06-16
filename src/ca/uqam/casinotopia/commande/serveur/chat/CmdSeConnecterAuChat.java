package ca.uqam.casinotopia.commande.serveur.chat;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class CmdSeConnecterAuChat implements CommandeServeurControleurThread {

	private static final long serialVersionUID = -2302499804061663323L;
	
	private String salle;

	public CmdSeConnecterAuChat(String salle) {
		this.salle = salle;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionSeConnecterAuChat(this.salle);
	}
}