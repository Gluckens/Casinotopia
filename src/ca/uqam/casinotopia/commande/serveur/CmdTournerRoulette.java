package ca.uqam.casinotopia.commande.serveur;

import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurRouletteServeur;

public class CmdTournerRoulette implements CommandeServeurControleurRoulette {
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 3728163550640486131L;

	
	public CmdTournerRoulette() {
	}

	public void action(Controleur controleur) {
		System.out.println("ACTION DE LA COMMANDE CMD_TOURNER_ROULETTE");
		((ControleurRouletteServeur)controleur).actionTournerRoulette();
	}
}
