package ca.uqam.casinotopia.commande.serveur.chat;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class CmdQuitterChat implements CommandeServeurControleurThread {
	
	private static final long serialVersionUID = 2683347143169727635L;
	
	private int idJoueur;
	
	public CmdQuitterChat(int idJoueur) {
		this.idJoueur = idJoueur;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionQuitterChat(this.idJoueur);
	}

}
