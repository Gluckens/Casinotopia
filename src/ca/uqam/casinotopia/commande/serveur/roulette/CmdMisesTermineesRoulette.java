package ca.uqam.casinotopia.commande.serveur.roulette;

import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurRouletteServeur;

public class CmdMisesTermineesRoulette implements CommandeServeurControleurRoulette {
	
	private static final long serialVersionUID = -5864787457870123976L;
	
	private int idJoueur;
	
	public CmdMisesTermineesRoulette(int idJoueur) {
		this.idJoueur = idJoueur;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurRouletteServeur) controleur).actionMisesTerminees(this.idJoueur);
	}
}