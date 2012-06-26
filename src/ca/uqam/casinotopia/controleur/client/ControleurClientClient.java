package ca.uqam.casinotopia.controleur.client;

import java.sql.Date;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;

/**
 * Controleur gérant les actions qui modifie le client.
 */
public class ControleurClientClient extends ControleurClient {

	/**
	 * @param connexion La connexion associée à l'utilisateur.
	 * @param client Le modèle client de l'utilisateur
	 * @param modeleNav Le modèle de navigation
	 */
	public ControleurClientClient(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion, client, modeleNav);
	}
	
	/**
	 * Modifier les informations du client.
	 * Cette action est exécutée suite à la demande du serveur (commande)
	 * 
	 * @param prenom Nouveau prénom
	 * @param nom Nouveau nom
	 * @param dateNaissance Nouvelle date de naissance
	 * @param courriel Nouveau courriel
	 * @param prcGlobal Nouveau prcGLobal
	 */
	public void actionModifierCompte(String prenom, String nom, Date dateNaissance, String courriel, int prcGlobal) {
		this.client.modifierCompte(prenom, nom, dateNaissance, courriel, prcGlobal);
	}
	
	/**
	 * Modifier le solde du client
	 * @param nouveauSolde Le nouveau solde du client
	 */
	public void modifierSolde(int nouveauSolde) {
		this.client.setSolde(nouveauSolde);
	}
}