package ca.uqam.casinotopia.controleur.serveur;

import java.sql.Date;

import ca.uqam.casinotopia.commande.client.compte.CmdInformationCreationCompte;
import ca.uqam.casinotopia.commande.client.compte.CmdModifierCompte;
import ca.uqam.casinotopia.commande.client.compte.CmdModifierSolde;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public class ControleurClientServeur extends ControleurServeur {
	
	private ModeleClientServeur modele;

	public ControleurClientServeur(Connexion connexion, ControleurServeurThread ctrlThread, ModeleClientServeur modele) {
		super(connexion, ctrlThread);
		this.modele = modele;
	}
	
	public void actionModifierCompte(ModeleClientClient modeleClient) {
		if (this.modifierInformations(modeleClient.getPrenom(), modeleClient.getNom(), modeleClient.getDateNaissance(), modeleClient.getCourriel(), modeleClient.getPrcGlobal())) {
			this.connexion.envoyerCommande(new CmdModifierCompte(modeleClient.getPrenom(), modeleClient.getNom(), modeleClient.getDateNaissance(), modeleClient.getCourriel(), modeleClient.getPrcGlobal()));
		}
		else {
			this.connexion.envoyerCommande(new CmdInformationCreationCompte("Le compte n'a pas été modifié."));
		}
		
		this.modifierInformations(modeleClient.getPrenom(), modeleClient.getNom(), modeleClient.getDateNaissance(), modeleClient.getCourriel(), modeleClient.getPrcGlobal());
	}
	
	public boolean modifierInformations(String prenom, String nom, Date dateNaissance, String courriel, int prcGlobal) {
		return this.modele.modifierInformations(prenom, nom, dateNaissance, courriel, prcGlobal);
	}
	
	public void ajouterSolde(int montant) {
		this.modifierSolde(this.modele.getSolde() + montant);
	}
	
	public void modifierSolde(int nouveauSolde) {
		if(this.modele.modifierSolde(nouveauSolde)) {
			this.cmdModifierSolde();
		}
	}
	
	private void cmdModifierSolde() {
		this.connexion.envoyerCommande(new CmdModifierSolde(this.modele.getSolde()));
	}
	
	public ModeleClientServeur getModele() {
		return this.modele;
	}
}
