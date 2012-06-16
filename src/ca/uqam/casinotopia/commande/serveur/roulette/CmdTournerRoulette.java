package ca.uqam.casinotopia.commande.serveur.roulette;

import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurRouletteServeur;

public class CmdTournerRoulette implements CommandeServeurControleurRoulette {

	private static final long serialVersionUID = 6109072034530700593L;

	public CmdTournerRoulette() {
		
	}

	public void action(Controleur controleur) {
		((ControleurRouletteServeur)controleur).actionTournerRoulette();
	}
}