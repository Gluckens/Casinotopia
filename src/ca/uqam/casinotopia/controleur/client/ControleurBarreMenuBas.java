package ca.uqam.casinotopia.controleur.client;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.navigation.VueBarreMenuBas;

public class ControleurBarreMenuBas extends ControleurClient {

	private static final long serialVersionUID = -2128979284720466267L;
	
	private VueBarreMenuBas vue;

	public ControleurBarreMenuBas(ModeleClientClient client, ModelePrincipalClient modeleNav) {
		this(new Connexion(), client, modeleNav);
	}

	public ControleurBarreMenuBas(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion, client, modeleNav);

		this.vue = new VueBarreMenuBas(this);
		this.client.ajouterObservateur(this.vue);
	}
	
	public VueBarreMenuBas getVue() {
		return this.vue;
	}

	public FrameApplication getFrame() {
		return this.modeleNav.getFrameApplication();
	}

	public void afficherGestionCompte() {
		ControleurPrincipalClient controleur = (ControleurPrincipalClient) modeleNav.getControleur("ControleurPrincipalClient");
		controleur.afficherModificationCompte();
	}
}
