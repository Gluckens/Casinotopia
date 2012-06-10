package ca.uqam.casinotopia.controleur.serveur;

import java.awt.Point;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.client.CmdAfficherDeplacementAvatar;
import ca.uqam.casinotopia.commande.client.CmdRetirerClientSalle;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleSalleServeur;

@SuppressWarnings("serial")
public class ControleurSalleServeur extends ControleurServeur {
	
	private ModeleSalleServeur modele;

	public ControleurSalleServeur(Connexion connexion, ModeleClientServeur client, ModeleSalleServeur modele) {
		super(connexion, client);
		this.modele = modele;
	}
	
	public void actionDeplacerAvatar(Point p) {
		//TODO Valider qu'il est possible de se d�placer � cet endroit?
		//Devrait etre fait du cot� client etant donn�e que seule la vue le sait?
		//System.out.println("CLIENT " + this.client.getId() + " : DEPLACEMENT SERVEUR (" + this.client.getAvatar().getPosition() + ") vers ( " + p + ")");
		this.client.getAvatar().setPosition(p);
		
		this.cmdAfficherDeplacementAvatar(p);
	}

	private void cmdAfficherDeplacementAvatar(Point p) {
		Commande cmd = new CmdAfficherDeplacementAvatar(this.client.getId(), p);
		
		//System.out.println("UPDATE DE LA POSITION DU CLIENT " + this.client.getId() + " POUR LES " + this.modele.getLstClients().size() + " CLIENTS DE LA SALLE");
		for(ModeleClientServeur client : this.modele.getLstClients()) {
			//System.out.println("UPDATE DE LA POSITION DU CLIENT " + this.client.getId() + " POUR LE CLIENT " + client.getId());
			client.getConnexion().envoyerCommande(cmd);
		}
	}
	
	public void quitterSalle() {
		//TODO On envoie l'ID du client, ou bedon on se base sur le modele client qu'on connait d�j� c�t� serveur?
		this.modele.retirerClient(this.client);
		this.client.getAvatar().setPosition(new Point(0, 0));
		
		for(ModeleClientServeur client : this.modele.getLstClients()) {
			client.getConnexion().envoyerCommande(new CmdRetirerClientSalle(this.client.getId()));
		}
	}
}
