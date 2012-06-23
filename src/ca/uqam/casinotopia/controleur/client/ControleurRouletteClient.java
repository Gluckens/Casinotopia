package ca.uqam.casinotopia.controleur.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ca.uqam.casinotopia.commande.serveur.chat.CmdQuitterChat;
import ca.uqam.casinotopia.commande.serveur.roulette.CmdMiserRoulette;
import ca.uqam.casinotopia.commande.serveur.roulette.CmdMisesTermineesRoulette;
import ca.uqam.casinotopia.commande.serveur.roulette.CmdQuitterPartieRoulette;
import ca.uqam.casinotopia.commande.serveur.roulette.CmdTournerRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.objet.Case;
import ca.uqam.casinotopia.objet.JoueurClient;
import ca.uqam.casinotopia.type.TypeMise;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.roulette.VueRoulette;
import ca.uqam.casinotopia.vue.roulette.VueRouletteListeJoueurs;
import ca.uqam.casinotopia.vue.roulette.VueRouletteTapis;

/**
 * Controleur gérant les actions du jeu de roulette.
 */
public class ControleurRouletteClient extends ControleurClient {
	
	/**
	 * La vue de la roulette
	 */
	private VueRoulette vue;
	
	/**
	 * Le modèle de la roulette
	 */
	private ModelePartieRouletteClient modele;

	public ControleurRouletteClient(Connexion connexion, ModelePartieRouletteClient modele, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion, client, modeleNav);

		this.modele = modele;
		this.vue = new VueRoulette(this);
		
		this.modele.ajouterObservateur(this.vue);
		this.modele.ajouterObservateur((VueRouletteListeJoueurs)this.vue.getComponentByName("vueListeJoueurs"));
		this.modele.getTableJeu().ajouterObservateur((VueRouletteTapis)this.vue.getComponentByName("tapis"));
	}
	
	/**
	 * Retourne le squelette du Map des mises
	 * @return le map
	 */
	public Map<Integer, Map<Case, Integer>> creerMapMises() {
		return new HashMap<Integer, Map<Case, Integer>>();
	}
	
	/**
	 * Valider si le client possède les fonds nécessaires pour effectuer une mise
	 * 
	 * @param montant Le montant à miser
	 * @return true si valide, false sinon
	 */
	public boolean validerMise(int montant) {
		return (this.client.getSolde() >= montant);
	}
	
	/**
	 * Ajouter une mise dans un map de mise.
	 * Fonction servant uniquement à simplifier le processus
	 * 
	 * @param caseMisee La case sur laquelle miser
	 * @param montant Le montant à miser
	 * @param mises Le map de mise dans lequel ajouter la mise
	 */
	public void ajouterMise(Case caseMisee, int montant, Map<Integer, Map<Case, Integer>> mises) {
		Map<Case, Integer> mise = mises.get(this.client.getId());
		if(mise == null) {
			mise = new HashMap<Case, Integer>();
		}
		mise.put(caseMisee, montant);
		mises.put(this.client.getId(), mise);
	}

	/**
	 * Envoyer une commande au serveur pour effectuer une mise sur la table de jeu.
	 * 
	 * @param caseMisee La case sur laquelle miser
	 * @param typeMise Le type de mise (montant)
	 */
	public void cmdMiserRoulette(Case caseMisee, TypeMise typeMise) {
		Map<Integer, Map<Case, Integer>> mises = new HashMap<Integer, Map<Case, Integer>>();
		Map<Case, Integer> mise = new HashMap<Case, Integer>();
		mise.put(caseMisee, typeMise.getMontant());
		mises.put(this.client.getId(), mise);
		System.out.println("Joueur " + this.client.getId() + " mise " + typeMise.getMontant() + " sur " + caseMisee);
		this.cmdMiserRoulette(mises);
	}

	/**
	 * Envoyer une commande au serveur pour effectuer plusieurs mises sur la table de jeu.
	 * 
	 * @param mises Map représentant les mises
	 */
	public void cmdMiserRoulette(Map<Integer, Map<Case, Integer>> mises) {
		this.connexion.envoyerCommande(new CmdMiserRoulette(mises));
	}

	/**
	 * Mettre à jour la table de jeu pour refléter les mises des autres joueurs.
	 * 
	 * @param cases Map représentant les cases avec leurs mises
	 */
	public void actionUpdateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		this.modele.updateTableJeu(cases);
	}
	
	/**
	 * Envoyer une commande au serveur pour avertir que le client à terminer de miser et est prêt à tourner la roulette.
	 */
	public void cmdMisesTermineesRoulette() {
		this.connexion.envoyerCommande(new CmdMisesTermineesRoulette(this.client.getId()));
	}

	/**
	 * Envoyer une commande au serveur pour tourner la roulette et calculer les gains.
	 */
	public void cmdTournerRoulette() {
		this.connexion.envoyerCommande(new CmdTournerRoulette());
	}

	/**
	 * Mettre à jour les informations après avoir tourner la roulette.
	 * Cette action est exécutée suite à la demande du serveur (commande)
	 * 
	 * @param resultat La case gagnante
	 * @param gain Le montant gagné/perdu par le client
	 */
	public void actionUpdateResultat(Case resultat, int gain) {
		this.modele.setCaseResultat(resultat);
		this.modele.setGain(gain);
	}

	/**
	 * Mettre à jour la liste des joueurs de la partie.
	 * Cette action est exécutée suite à la demande du serveur (commande)
	 * 
	 * @param lstJoueurs La nouvelle liste de joueurs.
	 */
	public void actionUpdateListeJoueurs(Set<JoueurClient> lstJoueurs) {
		this.modele.updateListeJoueurs(lstJoueurs);
	}

	/**
	 * Envoyer une commande au serveur pour quitter la partie de roulette.
	 * Envoie aussi une ocmmande pour quitter le chat associé à la partie.
	 */
	public void cmdQuitterPartie() {
		this.connexion.envoyerCommande(new CmdQuitterChat());
		this.connexion.envoyerCommande(new CmdQuitterPartieRoulette(this.client.getId()));
	}

	public VueRoulette getVue() {
		return this.vue;
	}

	public ModelePartieRouletteClient getModele() {
		return this.modele;
	}

	public FrameApplication getFrame() {
		return this.modeleNav.getFrameApplication();
	}
	
	public Set<JoueurClient> getListeJoueurs() {
		return this.modele.getLstJoueurs();
	}
}