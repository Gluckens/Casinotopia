package ca.uqam.casinotopia.controleur.serveur;

import java.sql.Date;

import ca.uqam.casinotopia.commande.client.compte.CmdInformationCreationCompte;
import ca.uqam.casinotopia.commande.client.compte.CmdModifierCompte;
import ca.uqam.casinotopia.commande.client.compte.CmdModifierSolde;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

/**
 * Controleur g�rant les actions qui modifie le client.
 */
public class ControleurClientServeur extends ControleurServeur {
	
	/**
	 * Mod�le du client
	 */
	private ModeleClientServeur modele;

	public ControleurClientServeur(Connexion connexion, ControleurServeurThread ctrlThread, ModeleClientServeur modele) {
		super(connexion, ctrlThread);
		this.modele = modele;
	}
	
	/**
	 * Initier la modification des informations du client.
	 * Cette action est ex�cut�e suite � la demande du client (commande)
	 * 
	 * @param modeleClient Le mod�le temporaire contenant les nouvelles informations
	 */
	public void actionModifierCompte(ModeleClientClient modeleClient) {
		if (this.modifierInformations(modeleClient.getPrenom(), modeleClient.getNom(), modeleClient.getDateNaissance(), modeleClient.getCourriel(), modeleClient.getPrcGlobal())) {
			this.connexion.envoyerCommande(new CmdModifierCompte(modeleClient.getPrenom(), modeleClient.getNom(), modeleClient.getDateNaissance(), modeleClient.getCourriel(), modeleClient.getPrcGlobal()));
		}
		else {
			this.connexion.envoyerCommande(new CmdInformationCreationCompte("Le compte n'a pas �t� modifi�."));
		}
		
		this.modifierInformations(modeleClient.getPrenom(), modeleClient.getNom(), modeleClient.getDateNaissance(), modeleClient.getCourriel(), modeleClient.getPrcGlobal());
	}
	
	/**
	 * Modifier les informations du client
	 * 
	 * @param prenom Le nouveau pr�nom
	 * @param nom Le nouveau nom
	 * @param dateNaissance La nouvelle date de naissance
	 * @param courriel Le nouveau courriel
	 * @param prcGlobal Le nouveau pourcentage global
	 * @return
	 */
	public boolean modifierInformations(String prenom, String nom, Date dateNaissance, String courriel, int prcGlobal) {
		return this.modele.modifierInformations(prenom, nom, dateNaissance, courriel, prcGlobal);
	}
	
	/**
	 * Ajouter/retirer un montant au solde du client
	 * 
	 * @param montant Le montant � ajouter/retirer
	 */
	public void ajouterSolde(int montant) {
		this.modifierSolde(this.modele.getSolde() + montant);
	}
	
	/**
	 * Affecter une nouvelle valeur au solde
	 * 
	 * @param nouveauSolde Le nouveau solde
	 */
	public void modifierSolde(int nouveauSolde) {
		if(this.modele.modifierSolde(nouveauSolde)) {
			this.cmdModifierSolde();
		}
	}
	
	/**
	 * Envoyer une commande au client pour modifier son solde
	 */
	private void cmdModifierSolde() {
		this.connexion.envoyerCommande(new CmdModifierSolde(this.modele.getSolde()));
	}
	
	public ModeleClientServeur getModele() {
		return this.modele;
	}
}