package ca.uqam.casinotopia.modele.client;

import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.FrameConnexion;
import ca.uqam.casinotopia.vue.Vue;

public class ModelePrincipalClient {

	private Map<String, ControleurClient> lstControleurs = new HashMap<String, ControleurClient>();
	
	private FrameConnexion frameConnexion;
	
	private FrameApplication frameApplication;
	
	public ModelePrincipalClient() {
		//TODO initialiser les frame ici, apres avoir creer une vue de connexion au lieu de le faire dans le frame direct
	}
	
	public void initFrame() {
		//TODO Faudrait créer une vue séparée du frame pour la connexion
		this.frameConnexion = new FrameConnexion((ControleurPrincipalClient) this.getControleur("ControleurPrincipalClient"));
		this.frameApplication = new FrameApplication();
	}
	
	public FrameConnexion getFrameConnexion() {
		return this.frameConnexion;
	}
	
	public FrameApplication getFrameApplication() {
		return this.frameApplication;
	}
	
	public boolean hasControleur(String nom) {
		return this.lstControleurs.containsKey(nom);
	}
	
	public ControleurClient getControleur(String nom) {
		return this.lstControleurs.get(nom);
	}
	
	public void ajouterControleur(String nom, ControleurClient ctrl) {
		this.lstControleurs.put(nom, ctrl);
	}
	
	public void cacherFrameConnexion() {
		this.frameConnexion.dispose();
	}
	
	public void cacherFrameApplication() {
		this.frameApplication.dispose();
	}
	
	public void changerVueFrameApplication(String nom, Vue vue) {
		this.frameApplication.removeAll();
		this.frameApplication.addOrReplace(nom, vue);
	}
}
