package ca.uqam.casinotopia.commande.serveur.roulette;

import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurRouletteServeur;

public class CmdTournerRoulette implements CommandeServeurControleurRoulette {
	
	private static final long serialVersionUID = -4241009660193135927L;

	
	public CmdTournerRoulette() {
	}

	public void action(Controleur controleur) {
		System.out.println("ACTION DE LA COMMANDE CMD_TOURNER_ROULETTE");
		((ControleurRouletteServeur)controleur).actionTournerRoulette();
	}
}
