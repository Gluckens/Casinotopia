package ca.uqam.casinotopia.controleur.client;

import java.sql.Date;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;

/**
 * Controleur g�rant les actions qui modifie le client.
 */
public class ControleurClientClient extends ControleurClient {

	/**
	 * @param connexion La connexion associ�e � l'utilisateur.
	 * @param client Le mod�le client de l'utilisateur
	 * @param modeleNav Le mod�le de navigation
	 */
	public ControleurClientClient(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion, client, modeleNav);
	}
	
	/**
	 * Modifier les informations du client.
	 * Cette action est ex�cut�e suite � la demande du serveur (commande)
	 * 
	 * @param prenom Nouveau pr�nom
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