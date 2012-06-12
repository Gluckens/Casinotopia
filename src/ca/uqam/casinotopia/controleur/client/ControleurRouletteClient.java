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

public class ControleurRouletteClient extends ControleurClient {

	private static final long serialVersionUID = 4718341989471372885L;
	
	private VueRoulette vue;
	private ModelePartieRouletteClient modele;

	public ControleurRouletteClient(Connexion connexion, ModelePartieRouletteClient modele, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion, client, modeleNav);

		this.modele = modele;
		this.vue = new VueRoulette(this);
		
		this.modele.ajouterObservateur(this.vue);
		this.modele.ajouterObservateur((VueRouletteListeJoueurs)this.vue.getComponentByName("vueListeJoueurs"));
		this.modele.getTableJeu().ajouterObservateur((VueRouletteTapis)this.vue.getComponentByName("tapis"));
	}

	public void actionUpdateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		this.modele.updateTableJeu(cases);
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

	public void cmdMiserRoulette(Case caseMisee, TypeMise typeMise) {
		Map<Integer, Map<Case, Integer>> mises = new HashMap<Integer, Map<Case, Integer>>();
		Map<Case, Integer> mise = new HashMap<Case, Integer>();
		mise.put(caseMisee, typeMise.getMontant());
		mises.put(this.client.getId(), mise);
		System.out.println("Joueur " + this.client.getId() + " mise " + typeMise.getMontant() + " sur " + caseMisee);
		this.cmdMiserRoulette(mises);
	}
	
	public Map<Integer, Map<Case, Integer>> creerMapMises() {
		return new HashMap<Integer, Map<Case, Integer>>();
	}
	
	public boolean validerMise(int montant) {
		return (this.client.getSolde() >= montant);
	}
	
	public void ajouterMise(Case caseMisee, int montant, Map<Integer, Map<Case, Integer>> mises) {
		Map<Case, Integer> mise = mises.get(this.client.getId());
		if(mise == null) {
			mise = new HashMap<Case, Integer>();
		}
		mise.put(caseMisee, montant);
		mises.put(this.client.getId(), mise);
	}

	public void cmdMiserRoulette(Map<Integer, Map<Case, Integer>> mises) {
		this.connexion.envoyerCommande(new CmdMiserRoulette(mises));
	}
	
	public void cmdMisesTermineesRoulette() {
		this.connexion.envoyerCommande(new CmdMisesTermineesRoulette(this.client.getId()));
	}

	public void cmdTournerRoulette() {
		this.connexion.envoyerCommande(new CmdTournerRoulette());
	}

	public void cmdQuitterPartie() {
		this.connexion.envoyerCommande(new CmdQuitterChat(this.client.getId()));
		this.connexion.envoyerCommande(new CmdQuitterPartieRoulette(this.client.getId()));
	}

	public void actionUpdateResultat(Case resultat, int gain) {
		this.modele.setCaseResultat(resultat);
		this.modele.setGain(gain);
	}
}
