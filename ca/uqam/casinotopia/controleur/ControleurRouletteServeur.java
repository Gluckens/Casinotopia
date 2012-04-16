package ca.uqam.casinotopia.controleur;

import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.commande.CmdUpdateCasesRoulette;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.modele.ModeleRouletteServeur;
import ca.uqam.casinotopia.vue.VueRoulette;

public class ControleurRouletteServeur extends ControleurServeur {

	public ControleurRouletteServeur(Connexion connexion) {
		super(connexion);
		
		if(!this.lstModeles.containsKey("ModeleRouletteServeur")) {
			ModeleRouletteServeur modeleRoulette = new ModeleRouletteServeur(0, true, true, null);
			this.ajouterModele(modeleRoulette);
		}
	}
	
	public void actionEffectuerMises(Map<Integer, Map<Case, Integer>> mises) {
		ModeleRouletteServeur modele = (ModeleRouletteServeur) this.getModele(ModeleRouletteServeur.class.getSimpleName());
		modele.effectuerMises(mises);
		
		this.cmdUpdateTableJoueurs(modele.getId(), modele.getTableJeu().getCases());
	}
	
	//TODO Les cases (mises) seront récupérées à partie de la partie, je le passe en paramètre pour les tests
	public void cmdUpdateTableJoueurs(int partieId, Map<Case, Map<Integer, Integer>> cases) {
		//TODO Rechercher les joueurs de la partie et mettre à jour leur table de jeu
		
		Commande cmd = new CmdUpdateCasesRoulette(cases);
	}

}
