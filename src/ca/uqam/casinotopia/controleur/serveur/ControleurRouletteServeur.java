package ca.uqam.casinotopia.controleur.serveur;

import java.util.Map;
import java.util.Set;

import ca.uqam.casinotopia.JoueurRoulette;
import ca.uqam.casinotopia.JoueurServeur;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.client.compte.CmdModifierSolde;
import ca.uqam.casinotopia.commande.client.roulette.CmdEnvoyerResultatRoulette;
import ca.uqam.casinotopia.commande.client.roulette.CmdUpdateCasesRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModelePartieRouletteServeur;
import ca.uqam.casinotopia.objet.Case;

public class ControleurRouletteServeur extends ControleurServeur {
	
	private ModelePartieRouletteServeur modele;

	public ControleurRouletteServeur(Connexion connexion, ControleurServeurThread ctrlThread, ModelePartieRouletteServeur modele) {
		super(connexion, ctrlThread);
		this.modele = modele;
	}

	public void actionEffectuerMises(Map<Integer, Map<Case, Integer>> mises) {
		this.modele.effectuerMises(mises);

		this.cmdUpdateTableJoueurs(this.modele.getId());
	}

	private void cmdUpdateTableJoueurs(int idPartie) {
		Commande cmd = new CmdUpdateCasesRoulette(this.modele.getTableJeu().getCases());
		
		Set<JoueurServeur> lstJoueurs = this.modele.getLstJoueurs();
		
		for(JoueurServeur joueur : lstJoueurs) {
			joueur.getClient().getConnexion().envoyerCommande(cmd);
		}
	}

	public void actionTournerRoulette() {
		this.modele.tournerRoulette();
		Case resultat = this.modele.getCaseResultat();
		
		Set<JoueurServeur> lstJoueurs = this.modele.getLstJoueurs();
		
		for(JoueurServeur joueur : lstJoueurs) {
			int gain = actionCalculerGainRoulette(joueur);
			joueur.getClient().getConnexion().envoyerCommande(new CmdModifierSolde(joueur.getClient().getSolde() + gain));
			
			System.out.println("Vous avez gagné au total : " + gain);
			joueur.getClient().getConnexion().envoyerCommande(new CmdEnvoyerResultatRoulette(resultat, gain));
		}
		
		this.modele.resetMises();
	}

	public int actionCalculerGainRoulette(JoueurServeur joueur) {
		return this.modele.calculerGainRoulette(joueur);
	}

	public void actionMisesTerminees(int idJoueur) {
		((JoueurRoulette) this.modele.getJoueur(idJoueur)).setMisesTerminees(true);
		
		if(this.modele.isToutesMisesTerminees()) {
			this.actionTournerRoulette();
			this.modele.resetMisesTerminees();
		}
	}

	public void actionQuitterPartie(int idJoueur) {
		this.modele.deconnecter(this.modele.getJoueur(idJoueur).getClient());
	}
}