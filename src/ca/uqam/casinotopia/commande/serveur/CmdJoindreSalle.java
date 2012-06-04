package ca.uqam.casinotopia.commande.serveur;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class CmdJoindreSalle implements CommandeServeurControleurThread {
	
	private static final long serialVersionUID = 6326456986609267799L;
	
	private int idSalle;
	
	public CmdJoindreSalle(int idSalle) {
		this.idSalle = idSalle;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionJoindreSalle(this.idSalle);
	}

}
