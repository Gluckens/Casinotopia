package ca.uqam.casinotopia.commande.serveur;

import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurRouletteServeur;

public class CmdQuitterPartieRoulette implements CommandeServeurControleurRoulette {
	
	private int idJoueur;
	
	public CmdQuitterPartieRoulette(int idJoueur) {
		this.idJoueur = idJoueur;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurRouletteServeur) controleur).actionQuitterPartie(this.idJoueur);
	}

}
