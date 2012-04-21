package ca.uqam.casinotopia.commande.serveur;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class CmdJouerRoulette implements CommandeServeurControleurThread {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3726034859881126673L;
	
	private int idJeu;
	
	public CmdJouerRoulette(int idJeu) {
		this.idJeu = idJeu;
	}

	@Override
	public void action(Controleur controleur) {
		System.out.println("ACTION DE CMD JOUER ROULETTE SUR SERVEUR");
		((ControleurServeurThread) controleur).actionAjouterJoueurDansRoulette(this.idJeu);
	}
}
