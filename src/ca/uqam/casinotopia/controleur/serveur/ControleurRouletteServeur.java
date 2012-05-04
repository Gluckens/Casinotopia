package ca.uqam.casinotopia.controleur.serveur;

import java.util.Map;
import java.util.Set;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.JoueurRoulette;
import ca.uqam.casinotopia.JoueurServeur;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.client.CmdAfficherMenuPrincipal;
import ca.uqam.casinotopia.commande.client.CmdEnvoyerResultatRoulette;
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
		this.modele.effectuerMises(mises);

		this.cmdUpdateTableJoueurs(this.modele.getId());
	}

	public void cmdUpdateTableJoueurs(int idPartie) {
		Commande cmd = new CmdUpdateCasesRoulette(this.modele.getTableJeu().getCases());
		
		Set<JoueurServeur> lstJoueurs = this.modele.getLstJoueurs();
		
		for(JoueurServeur joueur : lstJoueurs) {
			joueur.getClient().getConnexion().envoyerCommande(cmd);
		}
	}

	//aaa
	public void actionTournerRoulette() {
		System.out.println("ACTION_TOURNER_ROULETTE");
		this.modele.tournerRoulette();
		Case result = this.modele.getCaseResultat();
		
		
		Set<JoueurServeur> lstJoueurs = this.modele.getLstJoueurs();
		
		for(JoueurServeur joueur : lstJoueurs) {
			int gain = actionCalculerGainRoulette(joueur);
			System.out.println("Vous avez gagné au total : " + gain);
			Commande cmd = new CmdEnvoyerResultatRoulette(result,gain);
			joueur.getClient().getConnexion().envoyerCommande(cmd);
		}
	}

	public int actionCalculerGainRoulette(JoueurServeur joueur) {
		System.out.println("ACTION_CALCULER_GAIN_ROULETTE");
		return this.modele.calculerGainRoulette(joueur);
	}

	public void actionMisesTerminees(int idJoueur) {
		((JoueurRoulette) this.modele.getJoueur(idJoueur)).setMisesTerminees(true);
		
		if(this.modele.isToutesMisesTerminees()) 
			{
			//aaa
			this.actionTournerRoulette();
			this.modele.resetMisesTerminees();
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
