package ca.uqam.casinotopia.commande.client.roulette;

import java.util.Set;

import ca.uqam.casinotopia.commande.CommandeClientControleurRoulette;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.objet.JoueurClient;

public class CmdUpdateListeJoueurs implements CommandeClientControleurRoulette {
	
	private static final long serialVersionUID = 5659458382073983459L;
	
	private Set<JoueurClient> lstJoueurs;
	
	public CmdUpdateListeJoueurs(Set<JoueurClient> lstJoueurs) {
		this.lstJoueurs = lstJoueurs;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurRouletteClient) controleur).actionUpdateListeJoueurs(this.lstJoueurs);
	}
}