package ca.uqam.casinotopia.commande.serveur;

import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.controleur.Controleur;

public class CmdCalculerGainRoulette implements CommandeServeurControleurRoulette {
	
	private static final long serialVersionUID = -7232242678213909096L;

	
	public CmdCalculerGainRoulette() {
	}

	public void action(Controleur controleur) {
		System.out.println("ACTION DE LA COMMANDE CMD_CALCULER_GAIN_ROULETTE");
		//((ControleurRouletteServeur)controleur).actionCalculerGainRoulette();
	}
}
