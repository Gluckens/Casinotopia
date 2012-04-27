package ca.uqam.casinotopia.commande.serveur;

import ca.uqam.casinotopia.commande.CommandeServeur;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurRouletteServeur;

public class CmdMisesTermineesRoulette implements CommandeServeur {

	private static final long serialVersionUID = -924725531305179986L;
	
	private int idJoueur;
	
	public CmdMisesTermineesRoulette(int idJoueur) {
		this.idJoueur = idJoueur;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurRouletteServeur) controleur).actionMisesTerminees(this.idJoueur);
	}

}
