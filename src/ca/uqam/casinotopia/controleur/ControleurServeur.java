package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.serveur.ControleurClientServeur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

/**
 * Classe abstraite représentant l'implémentation de base d'un controleur côté serveur.
 */
public abstract class ControleurServeur extends Controleur {
	
	/**
	 * Permet à tous les contrôleur serveur de communiquer entre eux.
	 */
	protected ControleurServeurThread ctrlThread;
	
	public ControleurServeur() {
		
	}

	/**
	 * 
	 * @param connexion La connexion associée au client.
	 */
	public ControleurServeur(Connexion connexion) {
		this(connexion, null);
	}

	/**
	 * 
	 * @param connexion La connexion associée au client.
	 * @param ctrlThread Le controleur pour les communications inter-controleurs
	 */
	public ControleurServeur(Connexion connexion, ControleurServeurThread ctrlThread) {
		super(connexion);
		this.ctrlThread = ctrlThread;
	}
	
	/**
	 * Récupère un controleur par son nom
	 * 
	 * @param nom Le nom du controleur à récupérer
	 * @return Le controleur demandé
	 */
	public ControleurServeur getControleur(String nom) {
		return this.ctrlThread.getControleur(nom);
	}
	
	/**
	 * Retourne le modele client de l'utilisateur.
	 * 
	 * @return Le modèle client
	 */
	public ModeleClientServeur getModeleClient() {
		ControleurClientServeur ctrlClient = (ControleurClientServeur) this.getControleur("ControleurClientServeur");
		if(ctrlClient == null) {
			return null;
		}
		
		return ctrlClient.getModele();
	}
	
	/**
	 * Fonction d'accessibilité. Permet de modifier le solde d'un client facilement.
	 * @param montant Le montant à ajouté/retirer au solde du client.
	 */
	public void ajouterSoldeClient(int montant) {
		((ControleurClientServeur) this.ctrlThread.getControleur("ControleurClientServeur")).ajouterSolde(montant);
	}
}