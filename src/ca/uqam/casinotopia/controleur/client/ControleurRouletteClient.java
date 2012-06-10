package ca.uqam.casinotopia.controleur.client;

import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.TypeMise;
import ca.uqam.casinotopia.commande.serveur.CmdCalculerGainRoulette;
import ca.uqam.casinotopia.commande.serveur.CmdMiserRoulette;
import ca.uqam.casinotopia.commande.serveur.CmdMisesTermineesRoulette;
import ca.uqam.casinotopia.commande.serveur.CmdQuitterPartieRoulette;
import ca.uqam.casinotopia.commande.serveur.CmdTournerRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.roulette.VueRoulette;
import ca.uqam.casinotopia.vue.roulette.VueRouletteTapis;

public class ControleurRouletteClient extends ControleurClient {

	private static final long serialVersionUID = 4718341989471372885L;
	
	private VueRoulette vue;
	private ModelePartieRouletteClient modele;

	public ControleurRouletteClient(Connexion connexion, ModelePartieRouletteClient modele, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion, client, modeleNav);

		this.vue = new VueRoulette(this);
		this.modele = modele;
		this.modele.ajouterObservateur(this.vue);
		
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

	public void cmdTournerRoulette(){
		System.out.println("TEST DE TOURNER ROULETTE");
		this.connexion.envoyerCommande(new CmdTournerRoulette());
	}
	
	public void cmdCalculerGainRoulette(){
		System.out.println("TEST DE CALCULER GAIN");
		this.connexion.envoyerCommande(new CmdCalculerGainRoulette());
	}

	public void cmdQuitterPartie() {
		this.connexion.envoyerCommande(new CmdQuitterPartieRoulette(this.client.getId()));
	}

	public void actionUpdateResultat(Case resultat, int gain) {
		System.out.println("Alexei --> ControleurRouletteClient.actionupdateResultat()");
		this.modele.setCaseResultat(resultat);
		this.modele.setGain(gain);
		//this.client.updateSolde(gain);
	}
}
