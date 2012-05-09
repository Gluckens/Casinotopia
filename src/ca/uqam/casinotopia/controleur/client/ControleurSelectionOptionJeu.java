package ca.uqam.casinotopia.controleur.client;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.vue.VueSelectionOptionJeu;

public class ControleurSelectionOptionJeu extends ControleurClient {
	
	private static final long serialVersionUID = -656523311101716414L;
	
	private VueSelectionOptionJeu vue;
	//private ModeleSalleClient modele;

	public ControleurSelectionOptionJeu(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion, client, modeleNav);

		//this.vue = new VueSelectionOptionJeu(this);
		/*this.modele = modele;
		this.modele.ajouterObservateur(this.vue);*/
	}
	
	public VueSelectionOptionJeu getVue() {
		return this.vue;
	}
	
	/*public ModeleSalleClient getModele() {
		return this.modele;
	}*/
}
