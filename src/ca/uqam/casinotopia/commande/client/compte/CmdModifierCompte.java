package ca.uqam.casinotopia.commande.client.compte;

import java.sql.Date;

import ca.uqam.casinotopia.commande.CommandeClientControleurClient;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurClientClient;

public class CmdModifierCompte implements CommandeClientControleurClient {

	private static final long serialVersionUID = 7595553223330667858L;
	
	private String prenom;
	private String nom;
	private Date dateNaissance;
	private String courriel;
	private int prcGlobal;
	private String pathImage;

	public CmdModifierCompte(String prenom, String nom, Date dateNaissance, String courriel, int prcGlobal, String pathImage) {
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.courriel = courriel;
		this.prcGlobal = prcGlobal;
		this.pathImage = pathImage;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurClientClient) controleur).actionModifierCompte(this.prenom, this.nom, this.dateNaissance, this.courriel, this.prcGlobal, this.pathImage);
	}
}