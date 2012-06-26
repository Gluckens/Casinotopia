package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.serveur.ControleurClientServeur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

/**
 * Classe abstraite repr�sentant l'impl�mentation de base d'un controleur c�t� serveur.
 */
public abstract class ControleurServeur extends Controleur {
	
	/**
	 * Permet � tous les contr�leur serveur de communiquer entre eux.
	 */
	protected ControleurServeurThread ctrlThread;
	
	public ControleurServeur() {
		
	}

	/**
	 * 
	 * @param connexion La connexion associ�e au client.
	 */
	public ControleurServeur(Connexion connexion) {
		this(connexion, null);
	}

	/**
	 * 
	 * @param connexion La connexion associ�e au client.
	 * @param ctrlThread Le controleur pour les communications inter-controleurs
	 */
	public ControleurServeur(Connexion connexion, ControleurServeurThread ctrlThread) {
		super(connexion);
		this.ctrlThread = ctrlThread;
	}
	
	/**
	 * R�cup�re un controleur par son nom
	 * 
	 * @param nom Le nom du controleur � r�cup�rer
	 * @return Le controleur demand�
	 */
	public ControleurServeur getControleur(String nom) {
		return this.ctrlThread.getControleur(nom);
	}
	
	/**
	 * Retourne le modele client de l'utilisateur.
	 * 
	 * @return Le mod�le client
	 */
	public ModeleClientServeur getModeleClient() {
		ControleurClientServeur ctrlClient = (ControleurClientServeur) this.getControleur("ControleurClientServeur");
		if(ctrlClient == null) {
			return null;
		}
		
		return ctrlClient.getModele();
	}
	
	/**
	 * Fonction d'accessibilit�. Permet de modifier le solde d'un client facilement.
	 * @param montant Le montant � ajout�/retirer au solde du client.
	 */
	public void ajouterSoldeClient(int montant) {
		((ControleurClientServeur) this.ctrlThread.getControleur("ControleurClientServeur")).ajouterSolde(montant);
	}
}