package ca.uqam.casinotopia.commande;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.ControleurRouletteClient;


public class CmdMiserRoulette implements CommandeClientControleurRoulette {
	/**
	 * Map<idJoueur, Map<CaseMisee, NbrJetonsMises>>
	 */
	private Map<Integer, Map<Case, Integer>> mises = new HashMap<Integer, Map<Case, Integer>>();

	public void action(Controleur controleur, JFrame frame) {
		((ControleurRouletteClient)controleur).updateTableJeu(mises, frame);
	}

	@Override
	public void action(Controleur controleur) {
		// TODO Auto-generated method stub
		
	}

}
