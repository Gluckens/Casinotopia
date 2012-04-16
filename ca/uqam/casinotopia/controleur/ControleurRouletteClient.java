package ca.uqam.casinotopia.controleur;

import java.util.Map;

import javax.swing.JFrame;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.modele.ModeleRouletteClient;
import ca.uqam.casinotopia.vue.VueRoulette;

public class ControleurRouletteClient extends ControleurClient {
	
	public ControleurRouletteClient(Connexion connexion) {
		super(connexion);
	}

	//public void updateTableJeu(Map<Integer, Map<Case, Integer>> mises, JFrame frame) {
	public void updateTableJeu(Map<Case, Map<Integer, Integer>> cases, JFrame frame) {
		/*if(!this.lstVues.containsKey("VueRoulette")) {
			VueRoulette vueRoulette = new VueRoulette();
			this.ajouterVue(vueRoulette);
			
			//TODO Si la vue n'existait pas, on devrait pas changer le contentPane mais seulement l'ajouter au contentPane
			((FrameApplication)frame).changeContentPane(vueRoulette);
		}
		((VueRoulette)this.lstVues.get("VueRoulette")).updateTableJeu(cases);*/
		
		ModeleRouletteClient modeleRoulette = (ModeleRouletteClient)this.lstModeles.get("ModeleRouletteClient");
		if(modeleRoulette == null) {
			modeleRoulette = new ModeleRouletteClient(0, false, false, null);
			this.ajouterModele(modeleRoulette);
		}
		
		VueRoulette vueRoulette = (VueRoulette)this.lstVues.get("VueRoulette");
		if(vueRoulette == null) {
			vueRoulette = new VueRoulette();
			this.ajouterVue(vueRoulette);
		}
		
		if(!modeleRoulette.estObserveePar(vueRoulette)) {
			modeleRoulette.ajouterObservateur(vueRoulette);
		}
		
		modeleRoulette.updateTableJeu(cases);
	}
	
}
