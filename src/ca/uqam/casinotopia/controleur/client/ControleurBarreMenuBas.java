package ca.uqam.casinotopia.controleur.client;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.vue.navigation.VueBarreMenuBas;

/**
 * Controleur g�rant les actions de la barre de menu au bas de l'�cran.
 */
public class ControleurBarreMenuBas extends ControleurClient {
	
	/**
	 * La vue de la barre de menu
	 */
	private VueBarreMenuBas vue;

	/**
	 * @param client Le mod�le client de l'utilisateur
	 * @param modeleNav Le mod�le de navigation
	 */
	public ControleurBarreMenuBas(ModeleClientClient client, ModelePrincipalClient modeleNav) {
		this(new Connexion(), client, modeleNav);
	}

	/**
	 * @param connexion La connexion associ�e � l'utilisateur.
	 * @param client Le mod�le client de l'utilisateur
	 * @param modeleNav Le mod�le de navigation
	 */
	public ControleurBarreMenuBas(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion, client, modeleNav);

		this.vue = new VueBarreMenuBas(this);
		this.client.ajouterObservateur(this.vue);
	}
	
	public VueBarreMenuBas getVue() {
		return this.vue;
	}

	/**
	 * Affiche la vue de gestion de compte.
	 */
	public void afficherGestionCompte() {
		ControleurPrincipalClient controleur = (ControleurPrincipalClient) modeleNav.getControleur("ControleurPrincipalClient");
		controleur.afficherModificationCompte();
	}
}