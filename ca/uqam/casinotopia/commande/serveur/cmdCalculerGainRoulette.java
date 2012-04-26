package ca.uqam.casinotopia.commande.serveur;

import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurRouletteServeur;

public class cmdCalculerGainRoulette implements CommandeServeurControleurRoulette {
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 3728163550640486131L;

	
	public cmdCalculerGainRoulette() {
	}

	public void action(Controleur controleur) {
		System.out.println("ACTION DE LA COMMANDE CMD_CALCULER_GAIN_ROULETTE");
		((ControleurRouletteServeur)controleur).actionCalculerGainRoulette();
	}
}
