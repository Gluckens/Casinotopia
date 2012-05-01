package ca.uqam.casinotopia.commande.serveur;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class CmdQuitterPartieRoulette implements CommandeServeurControleurThread {
	
	private static final long serialVersionUID = -232195630606747437L;
	
	private int idJoueur;
	
	public CmdQuitterPartieRoulette(int idJoueur) {
		this.idJoueur = idJoueur;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionQuitterPartieRoulette(this.idJoueur);
	}

}
