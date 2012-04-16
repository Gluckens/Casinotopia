package ca.uqam.casinotopia.commande;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.ControleurRouletteServeur;


public class CmdMiserRoulette implements CommandeServeurControleurRoulette {
	/**
	 * Map<idJoueur, Map<CaseMisee, NbrJetonsMises>>
	 */
	private Map<Integer, Map<Case, Integer>> mises = new HashMap<Integer, Map<Case, Integer>>();
	
	public CmdMiserRoulette(Map<Integer, Map<Case, Integer>> mises) {
		this.mises = mises;
	}

	@Override
	public void action(Controleur controleur, JFrame frame) {
		((ControleurRouletteServeur)controleur).actionEffectuerMises(this.mises);
	}

}
