package ca.uqam.casinotopia.controleur.client;

import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.commande.serveur.CmdMiserRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.roulette.VueRoulette;

public class ControleurRouletteClient extends ControleurClient {
	
	private static final long serialVersionUID = -8588955394361302868L;

	private VueRoulette vue;
	private ModelePartieRouletteClient modele;
	
	private FrameApplication frame;

	public ControleurRouletteClient(Connexion connexion, ModelePartieRouletteClient modele, ModelePrincipalClient modeleNavigation, FrameApplication frame) {
		super(connexion, modeleNavigation);
		
		this.frame = frame;
		
		this.vue = new VueRoulette(this);
		this.modele = modele;
		this.modele.ajouterObservateur(this.vue);
	}

	//public void updateTableJeu(Map<Integer, Map<Case, Integer>> mises, JFrame frame) {
	public void actionUpdateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		/*if(!this.lstVues.containsKey("VueRoulette")) {
			VueRoulette vueRoulette = new VueRoulette();
			this.ajouterVue(vueRoulette);
			
			//TODO Si la vue n'existait pas, on devrait pas changer le contentPane mais seulement l'ajouter au contentPane
			((FrameApplication)frame).changeContentPane(vueRoulette);
		}
		((VueRoulette)this.lstVues.get("VueRoulette")).updateTableJeu(cases);*/
		
		//TODO Les modele ici ne devrait jamais etre vide, il devrait etre créer lors de la construction du controleur???
		//ModelePartieRouletteClient modeleRoulette = (ModelePartieRouletteClient)this.lstModeles.get("ModelePartieRouletteClient");
		
		/*if(modeleRoulette == null) {
			System.out.println("LE MODÈLE EST NUL");
			modeleRoulette = new ModelePartieRouletteClient(0, false, false, null);
			this.ajouterModele(modeleRoulette);
		}
		
		VueRoulette vueRoulette = (VueRoulette)this.lstVues.get("VueRoulette");
		if(vueRoulette == null) {
			System.out.println("LA VUE EST NULLE");
			vueRoulette = new VueRoulette(this, this.frame);
			this.ajouterVue(vueRoulette);
		}
		
		if(!modeleRoulette.estObservePar(vueRoulette)) {
			modeleRoulette.ajouterObservateur(vueRoulette);
		}*/
		
		this.modele.updateTableJeu(cases);
	}
	


	public VueRoulette getVue() {
		return this.vue;
	}
	
	public ModelePartieRouletteClient getModele() {
		return this.modele;
	}
	
	public FrameApplication getFrame() {
		return this.frame;
	}
	
	
	public void cmdMiserRoulette(Map<Integer, Map<Case, Integer>> mises) {
		System.out.println("TEST DE MISE");
		this.connexion.envoyerCommande(new CmdMiserRoulette(mises));
	}
	
}
