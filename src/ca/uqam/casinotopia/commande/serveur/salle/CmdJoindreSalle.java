package ca.uqam.casinotopia.commande.serveur.salle;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class CmdJoindreSalle implements CommandeServeurControleurThread {
	
	private static final long serialVersionUID = -4571323471877967969L;
	
	private int idSalle;
	
	public CmdJoindreSalle(int idSalle) {
		this.idSalle = idSalle;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionJoindreSalle(this.idSalle);
	}
}