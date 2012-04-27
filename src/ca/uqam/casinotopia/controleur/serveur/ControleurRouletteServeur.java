package ca.uqam.casinotopia.controleur.serveur;

import java.util.Map;
import java.util.Set;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.JoueurRoulette;
import ca.uqam.casinotopia.JoueurServeur;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.client.CmdAfficherMenuPrincipal;
import ca.uqam.casinotopia.commande.client.CmdUpdateCasesRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.modele.serveur.ModelePartieRouletteServeur;

//TODO étant donné que les controleurs serveur sont unique pour chaque client loggué, on pourrait rajouté un attribut client dans la classe ControleurServeur

public class ControleurRouletteServeur extends ControleurServeur {

	private static final long serialVersionUID = 5984983846828475123L;

	private ModelePartieRouletteServeur modele;

	public ControleurRouletteServeur(Connexion connexion, ModeleClientServeur client, ModelePartieRouletteServeur modele) {
		super(connexion, client);
		this.modele = modele;
	}

	public void actionEffectuerMises(Map<Integer, Map<Case, Integer>> mises) {
		System.out.println("ACTION_EFFECTUER_MISES");
		this.modele.effectuerMises(mises);

		this.cmdUpdateTableJoueurs(this.modele.getId());
	}

	public void cmdUpdateTableJoueurs(int idPartie) {
		// TODO Rechercher les joueurs de la partie et mettre à jour leur table de jeu
		


		Commande cmd = new CmdUpdateCasesRoulette(this.modele.getTableJeu().getCases());
		
		Set<JoueurServeur> lstJoueurs = this.modele.getLstJoueurs();

		System.out.println("AVANT ENVOI UPDATE ROULLETE PARTIE ==> " + this.modele.getId());
		
		for(JoueurServeur joueur : lstJoueurs) {
			joueur.getClient().getConnexion().envoyerCommande(cmd);
		}

		//this.getConnexion().envoyerCommande(cmd);
	}

	public void actionTournerRoulette() {
		System.out.println("ACTION_TOURNER_ROULETTE");
		this.modele.tournerRoulette();
	}

	public void actionCalculerGainRoulette() {
		System.out.println("ACTION_CALCULER_GAIN_ROULETTE");
		this.modele.calculerGainRoulette();
	}

	public void actionMisesTerminees(int idJoueur) {
		((JoueurRoulette) this.modele.getJoueur(idJoueur)).setMisesTerminees(true);
		
		if(this.modele.isToutesMisesTerminees()) {
			this.actionTournerRoulette();
		}
	}

	public void actionQuitterPartie(int idJoueur) {
		//TODO Comment on quitte une partie en cours? perd automatiquement ses gains misées?
		this.modele.quitterPartie(idJoueur);
		if(this.modele.isPartieVide()) {
			ControleurPrincipalServeur.getInstance().retirerPartie(this.modele);
		}
	}
}
