package ca.uqam.casinotopia.commande.client;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

public class CmdAfficherJeuRoulette implements CommandeClientControleurPrincipal {
	
	int idPartie;
	
	public CmdAfficherJeuRoulette(int idPartie) {
		this.idPartie = idPartie;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurClientPrincipal) controleur).actionAfficherJeuRoulette(this.idPartie);
	}

}
