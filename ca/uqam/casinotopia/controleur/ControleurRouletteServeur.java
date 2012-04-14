package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.commande.CmdUpdateMisesRoulette;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.connexion.Connexion;

public class ControleurRouletteServeur extends ControleurServeur {

	public ControleurRouletteServeur(Connexion connexion) {
		super(connexion);
	}
	
	public void updateTableJoueurs(int partieId) {
		//TODO Rechercher les joueurs de la partie et mettre à jour leur table de jeu
		
		Commande cmd = new CmdUpdateMisesRoulette();
	}

}
