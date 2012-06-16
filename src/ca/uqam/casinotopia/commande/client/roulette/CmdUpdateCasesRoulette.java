package ca.uqam.casinotopia.commande.client.roulette;

import java.util.HashMap;
import java.util.Map;
import ca.uqam.casinotopia.commande.CommandeClientControleurRoulette;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.objet.Case;

public class CmdUpdateCasesRoulette implements CommandeClientControleurRoulette {
	
	private static final long serialVersionUID = -8878006879834817032L;
	
	/**
	 * Map<Case, Map<idJoueur, NbrJetonsMises>>
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