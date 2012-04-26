package ca.uqam.casinotopia.commande.serveur;

import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurRouletteServeur;

public class CmdMiserRoulette implements CommandeServeurControleurRoulette {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3728163550640486131L;

	/**
	 * Map<idJoueur, Map<CaseMisee, NbrJetonsMises>>
	 */
	private Map<Integer, Map<Case, Integer>> mises = new HashMap<Integer, Map<Case, Integer>>();

	public CmdMiserRoulette(Map<Integer, Map<Case, Integer>> mises) {
		this.mises = mises;
	}

	@Override
	public void action(Controleur controleur) {
		System.out.println("ACTION DE LA COMMANDE CMD_MISER_ROULETTE");
		((ControleurRouletteServeur) controleur).actionEffectuerMises(this.mises);
	}
}
