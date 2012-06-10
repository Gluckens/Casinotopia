package ca.uqam.casinotopia.modele.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.FrameConnexion;
import ca.uqam.casinotopia.vue.FrameGestionCompte;
import ca.uqam.casinotopia.vue.Vue;

public class ModelePrincipalClient implements Modele, Serializable {

	private static final long serialVersionUID = -1636594066378148905L;

	private Map<String, ControleurClient> lstControleurs = new HashMap<String, ControleurClient>();

	private FrameConnexion frameConnexion;

	private FrameApplication frameApplication;
	
	private FrameGestionCompte frameGestionCompte;

	public ModelePrincipalClient() {
		// TODO initialiser les frame ici, apres avoir creer une vue de
		// connexion au lieu de le faire dans le frame direct
	}

	public void initFrame() {
		// TODO Faudrait créer une vue séparée du frame pour la connexion
		this.frameConnexion = new FrameConnexion((ControleurPrincipalClient) this.getControleur("ControleurPrincipalClient"));
	}
	
	public void initFrameApplication() {
		this.frameApplication = new FrameApplication();
	}
	
	public void initFrameGestionCompte(ControleurPrincipalClient controleurPrincipalClient, boolean nouvCompte) {
		this.frameGestionCompte = new FrameGestionCompte(controleurPrincipalClient, nouvCompte);
	}

	public FrameConnexion getFrameConnexion() {
		return this.frameConnexion;
	}

	public FrameApplication getFrameApplication() {
		return this.frameApplication;
	}
	
	public FrameGestionCompte getFrameGestionCompte() {
		return frameGestionCompte;
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
	
	public void retirerControleur(String nom) {
		this.lstControleurs.remove(nom);
	}

	public void cacherFrameConnexion() {
		this.frameConnexion.dispose();
	}

	public void cacherFrameApplication() {
		this.frameApplication.dispose();
	}
	
	public void cacherFrameGestionCompte(){
		this.frameGestionCompte.dispose();
	}

	public void changerVueFrameApplication(String nom, Vue vue) {
		this.frameApplication.removeAllVue();
		this.frameApplication.addOrReplaceVue(nom, vue);
	}

	public void changerMenuFrameApplication(String nom, Vue vue) {
		this.frameApplication.removeAllMenu();
		this.frameApplication.addOrReplaceMenu(nom, vue);
	}
}
