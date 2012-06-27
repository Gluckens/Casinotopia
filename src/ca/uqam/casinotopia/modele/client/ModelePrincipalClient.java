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

/**
 * Regroupe les informations générales sur l'environnement client du client.
 */
public class ModelePrincipalClient implements Modele, Serializable {
	
	private static final long serialVersionUID = -1569542649289944732L;

	/**
	 * La liste des controleurs actifs
	 */
	private Map<String, ControleurClient> lstControleurs = new HashMap<String, ControleurClient>();
	
	/**
	 * Le frame de connexion
	 */
	private FrameConnexion frameConnexion;
	
	/**
	 * Le frame de la gestion de compte
	 */
	private FrameGestionCompte frameGestionCompte;
	
	/**
	 * Le frame générale de l'application
	 */
	private FrameApplication frameApplication;

	public ModelePrincipalClient() {
		// TODO initialiser les frame ici, apres avoir creer une vue de connexion au lieu de le faire dans le frame direct
	}

	public void initFrameConnexion() {
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

	/**
	 * Déterminer si un certain controleur est actif dans l'environnement du client
	 * 
	 * @param nom Le nom du controleur
	 * @return True s'il est actif, false sinon
	 */
	public boolean hasControleur(String nom) {
		return this.lstControleurs.containsKey(nom);
	}

	/**
	 * Récupérer un controleur par son nom
	 * 
	 * @param nom Le nom du controleur
	 * @return Le controleur demandé
	 */
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

	/**
	 * Changer la vue active du frame application
	 * 
	 * @param nom Le nom de la nouvelle vue active
	 * @param vue La nouvelle vue active
	 */
	public void changerVueFrameApplication(String nom, Vue vue) {
		this.frameApplication.removeAllVue();
		this.frameApplication.addOrReplaceVue(nom, vue);
	}

	/**
	 * Changer la vue active du menu du frame application
	 * 
	 * @param nom Le nom de la nouvelle vue active du menu
	 * @param vue La nouvelle vue active du menu
	 */
	public void changerMenuFrameApplication(String nom, Vue vue) {
		this.frameApplication.removeAllMenu();
		this.frameApplication.addOrReplaceMenu(nom, vue);
	}
}