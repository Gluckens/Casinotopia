package ca.uqam.casinotopia.commande.client;

import java.util.HashMap;
import java.util.Map;
import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.commande.CommandeClientControleurRoulette;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;

public class CmdUpdateCasesRoulette implements CommandeClientControleurRoulette {

	/**
	 * Map<idJoueur, Map<CaseMisee, NbrJetonsMises>>
	 */
	// private Map<Integer, Map<Case, Integer>> mises = new HashMap<Integer,
	// Map<Case, Integer>>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 3520768426950865395L;
	/**
	 * Map<Case, Map<idJoueur, nbrJetonsMises>>
	 */
	private Map<Case, Map<Integer, Integer>> cases = new HashMap<Case, Map<Integer, Integer>>();

	public CmdUpdateCasesRoulette(Map<Case, Map<Integer, Integer>> cases) {
		this.cases = cases;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurRouletteClient) controleur).actionUpdateTableJeu(this.cases);
	}
}
