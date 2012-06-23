package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;

/**
 * Classe abstraite repr�sentant l'impl�mentation de base d'un controleur c�t� client.
 */
public abstract class ControleurClient extends Controleur {
	
	/**
	 * Permet � tous les controleurs client de naviguer dans l'interface et de communiquer entre eux.
	 */
	protected ModelePrincipalClient modeleNav;
	
	/**
	 * Permet aux controleurs de connaitre les d�tails du client.
	 */
	protected ModeleClientClient client;
	
	/**
	 * 
	 * @param modeleNav Le modele principal du client, utilis� pour la navigation.
	 */
	public ControleurClient(ModelePrincipalClient modeleNav) {
		this(new Connexion(), null, modeleNav);
	}

	/**
	 * 
	 * @param client Le mod�le client.
	 * @param modeleNav Le modele principal du client, utilis� pour la navigation.
	 */
	public ControleurClient(ModeleClientClient client, ModelePrincipalClient modeleNav) {
		this(new Connexion(), client, modeleNav);
	}

	/**
	 * 
	 * @param connexion La connexion associ�e au client.
	 * @param client Le mod�le client.
	 * @param modeleNav Le modele principal du client, utilis� pour la navigation.
	 */
	public ControleurClient(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion);
		this.client = client;
		this.modeleNav = modeleNav;
	}

	/**
	 * 
	 * @return Le mod�le de navigation
	 */
	public ModelePrincipalClient getModeleNav() {
		return this.modeleNav;
	}
	
	/**
	 * 
	 * @return Le mod�le du client
	 */
	public ModeleClientClient getModeleClient() {
		return this.client;
	}
}