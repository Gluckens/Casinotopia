package ca.uqam.casinotopia.controleur;

import java.util.Map;

import javax.swing.JFrame;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.VueRoulette;

public class ControleurRouletteClient extends ControleurClient {
	
	public ControleurRouletteClient(Connexion connexion) {
		super(connexion);
	}

	public void updateTableJeu(Map<Integer, Map<Case, Integer>> mises, JFrame frame) {
		System.out.println(this.connexion.isConnected());
		System.out.println(this.lstVues.toString());
		if(!this.lstVues.containsKey("VueRoulette")) {
			VueRoulette vueRoulette = new VueRoulette();
			this.ajouterVue(vueRoulette);
			
			((FrameApplication)frame).changeContentPane(vueRoulette);
		}
		((VueRoulette)this.lstVues.get("VueRoulette")).updateTableJeu("test");
	}
	
}
