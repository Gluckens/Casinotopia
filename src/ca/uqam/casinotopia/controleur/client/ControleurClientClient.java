package ca.uqam.casinotopia.controleur.client;

import java.sql.Date;

import ca.uqam.casinotopia.commande.serveur.compte.CmdModifierCompte;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;

public class ControleurClientClient extends ControleurClient {

	private static final long serialVersionUID = 2062988942063244845L;

	public ControleurClientClient(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNavigation) {
		super(connexion, client, modeleNavigation);
	}
	
	public void cmdModifierCompte() {
		this.connexion.envoyerCommande(new CmdModifierCompte(this.client));
	}
	
	public void actionModifierCompte(String prenom, String nom, Date dateNaissance, String courriel, int prcGlobal) {
		this.client.modifierCompte(prenom, nom, dateNaissance, courriel, prcGlobal);
	}
	
	public void modifierSolde(int nouveauSolde) {
		this.client.setSolde(nouveauSolde);
	}
}
