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

/**
 * Controleur gérant les actions du jeu de roulette.
 */
public class ControleurRouletteServeur extends ControleurServeur {
	
	/**
	 * Le modèle de la roulette
	 */
	private ModelePartieRouletteServeur modele;

	public ControleurRouletteServeur(Connexion connexion, ControleurServeurThread ctrlThread, ModelePartieRouletteServeur modele) {
		super(connexion, ctrlThread);
		this.modele = modele;
	}

	/**
	 * Effectuer des mises.
	 * Cette action est exécutée suite à la demande du client (commande)
	 * 
	 * @param mises Un map contenant les mises : Map<IdJoueur, Map<CaseMisee, NbrJetons>>
	 */
	public void actionEffectuerMises(Map<Integer, Map<Case, Integer>> mises) {
		this.modele.effectuerMises(mises);

		this.cmdUpdateTableJoueurs(this.modele.getId());
	}

	/**
	 * Envoyer une commande au client pour mettre à jour la liste des joueurs
	 * 
	 * @param idPartie L'id de la partie à mettre à jour
	 */
	private void cmdUpdateTableJoueurs(int idPartie) {
		Commande cmd = new CmdUpdateCasesRoulette(this.modele.getTableJeu().getCases());
		
		Set<JoueurServeur> lstJoueurs = this.modele.getLstJoueurs();
		
		for(JoueurServeur joueur : lstJoueurs) {
			joueur.getClient().getConnexion().envoyerCommande(cmd);
		}
	}

	/**
	 * Tourner la roulette, calculer les gains et envoyer des commandes aux joueurs pour mettre à jour leur vue.
	 * Cette action est exécutée suite à la demande du client (commande)
	 */
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

	/**
	 * Calculer les gains d'un joueur
	 * 
	 * @param joueur Le joueur sur lequel on veut calculer les gains
	 * @return Le montant des gains
	 */
	public int actionCalculerGainRoulette(JoueurServeur joueur) {
		return this.modele.calculerGainRoulette(joueur);
	}

	/**
	 * Indiquer que le joueur à terminé de miser.
	 * Cette action est exécutée suite à la demande du client (commande)
	 * 
	 * @param idJoueur L'id du joueur
	 */
	public void actionMisesTerminees(int idJoueur) {
		((JoueurRoulette) this.modele.getJoueur(idJoueur)).setMisesTerminees(true);
		
		if(this.modele.isToutesMisesTerminees()) {
			this.actionTournerRoulette();
			this.modele.resetMisesTerminees();
		}
	}

	/**
	 * Quitter la partie de roulette.
	 * Cette action est exécutée suite à la demande du client (commande)
	 * 
	 * @param idJoueur L'id du joueur qui quitte
	 */
	public void actionQuitterPartie(int idJoueur) {
		this.modele.deconnecter(this.modele.getJoueur(idJoueur).getClient());
	}
}