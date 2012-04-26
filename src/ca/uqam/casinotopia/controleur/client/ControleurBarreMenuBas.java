package ca.uqam.casinotopia.controleur.client;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.VueBarreMenuBas;

public class ControleurBarreMenuBas extends ControleurClient {

	private static final long serialVersionUID = -1237502765377625983L;

	private VueBarreMenuBas vue;

	// private ModelePartieRouletteClient modele;

	public ControleurBarreMenuBas(ModelePrincipalClient modeleNav) {
		super(modeleNav);
		// TODO Auto-generated constructor stub
	}

	public ControleurBarreMenuBas(Connexion connexion, ModelePrincipalClient modeleNav) {
		super(connexion, modeleNav);

		this.vue = new VueBarreMenuBas(this);
		/*
		 * this.modele = modele; this.modele.ajouterObservateur(this.vue);
		 */
	}

	public FrameApplication getFrame() {
		return this.modeleNav.getFrameApplication();
	}
}
