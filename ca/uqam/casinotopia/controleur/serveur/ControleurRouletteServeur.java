package ca.uqam.casinotopia.controleur.serveur;

import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.client.CmdUpdateCasesRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModelePartieRouletteServeur;

public class ControleurRouletteServeur extends ControleurServeur {
	
	private static final long serialVersionUID = 5984983846828475123L;
	
	private ModelePartieRouletteServeur modele;

	/*public ControleurRouletteServeur(Connexion connexion) {
		super(connexion);
		
		ModelePartieRouletteServeur modeleRoulette = new ModelePartieRouletteServeur(ControleurPrincipalServeur.getInstance().getIdPartieLibre(), true, true);
		this.ajouterModele(modeleRoulette);
		
		if(!this.lstModeles.containsKey("ModelePartieRouletteServeur")) {
			ModelePartieRouletteServeur modeleRoulette = new ModelePartieRouletteServeur(0, true, true);
			this.ajouterModele(modeleRoulette);
		}
	}*/
	
	public ControleurRouletteServeur(Connexion connexion, ModelePartieRouletteServeur modele) {
		super(connexion);
		this.modele = modele;
	}
	
	public void actionEffectuerMises(Map<Integer, Map<Case, Integer>> mises) {
		System.out.println("ACTION_EFFECTUER_MISES");
		this.modele.effectuerMises(mises);
		
		this.cmdUpdateTableJoueurs(this.modele.getId(), this.modele.getTableJeu().getCases());
	}
	
	//TODO Les cases (mises) seront récupérées à partie de la partie, je le passe en paramètre pour les tests
	public void cmdUpdateTableJoueurs(int partieId, Map<Case, Map<Integer, Integer>> cases) {
		//TODO Rechercher les joueurs de la partie et mettre à jour leur table de jeu
		
		Commande cmd = new CmdUpdateCasesRoulette(cases);
		
		System.out.println("AVANT ENVOI UPDATE ROULLETE");
		
		this.getConnexion().envoyerCommande(cmd);
	}

}
