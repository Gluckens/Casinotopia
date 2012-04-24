package ca.uqam.casinotopia.controleur.serveur;

import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.client.CmdUpdateCasesRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModelePartieRouletteServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleRoueRouletteServeur;

public class ControleurRouletteServeur extends ControleurServeur {

	/*public ControleurRouletteServeur(Connexion connexion) {
		super(connexion);
		
		ModelePartieRouletteServeur modeleRoulette = new ModelePartieRouletteServeur(ControleurServeurPrincipal.getInstance().getIdPartieLibre(), true, true);
		this.ajouterModele(modeleRoulette);
		
		if(!this.lstModeles.containsKey("ModelePartieRouletteServeur")) {
			ModelePartieRouletteServeur modeleRoulette = new ModelePartieRouletteServeur(0, true, true);
			this.ajouterModele(modeleRoulette);
		}
	}*/
	
	public ControleurRouletteServeur(Connexion connexion, ModelePartieRouletteServeur modeleRoulette) {
		super(connexion);
		this.ajouterModele(modeleRoulette);
	}
	
	public void actionEffectuerMises(Map<Integer, Map<Case, Integer>> mises) {
		System.out.println("ACTION_EFFECTUER_MISES");
		ModelePartieRouletteServeur modele = (ModelePartieRouletteServeur) this.getModele("ModelePartieRouletteServeur");
		modele.effectuerMises(mises);
		
		this.cmdUpdateTableJoueurs(modele.getId(), modele.getTableJeu().getCases());
	}
	
	//TODO Les cases (mises) seront récupérées à partie de la partie, je le passe en paramètre pour les tests
	public void cmdUpdateTableJoueurs(int partieId, Map<Case, Map<Integer, Integer>> cases) {
		//TODO Rechercher les joueurs de la partie et mettre à jour leur table de jeu
		
		Commande cmd = new CmdUpdateCasesRoulette(cases);
		
		System.out.println("AVANT ENVOI UPDATE ROULLETE");
		
		this.getConnexion().envoyerCommande(cmd);
	}

	public void actionTournerRoulette() {
		System.out.println("ACTION_TOURNER_ROULETTE");
		ModeleRoueRouletteServeur modele = (ModeleRoueRouletteServeur) this.getModele("ModeleRoueRouletteServeur");
		modele.tournerRoulette();
	}

}
