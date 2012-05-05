package ca.uqam.casinotopia.controleur.serveur;

import java.awt.Point;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.client.CmdAfficherDeplacementAvatar;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
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
		//TODO Valider qu'il est possible de se déplacer à cet endroit?
		//Devrait etre fait du coté client etant donnée que seule la vue le sait?
		System.out.println("CLIENT " + this.client.getId() + " : DEPLACEMENT SERVEUR (" + this.client.getAvatar().getPosition() + ") vers ( " + p + ")");
		this.client.getAvatar().setPosition(p);
		
		this.cmdAfficherDeplacementAvatar(p);
	}

	private void cmdAfficherDeplacementAvatar(Point p) {
		Commande cmd = new CmdAfficherDeplacementAvatar(this.client.getId(), p);
		
		System.out.println("UPDATE DE LA POSITION DU CLIENT " + this.client.getId() + " POUR LES " + this.modele.getLstClients().size() + " CLIENTS DE LA SALLE");
		for(ModeleClientServeur client : this.modele.getLstClients()) {
			System.out.println("UPDATE DE LA POSITION DU CLIENT " + this.client.getId() + " POUR LE CLIENT " + client.getId());
			client.getConnexion().envoyerCommande(cmd);
		}
		
		//this.connexion.envoyerCommande(new CmdAfficherDeplacementAvatar(p));
	}
}
