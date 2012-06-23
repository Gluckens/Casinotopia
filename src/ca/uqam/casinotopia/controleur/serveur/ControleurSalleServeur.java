package ca.uqam.casinotopia.controleur.serveur;

import java.awt.Point;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.client.salle.CmdAfficherDeplacementAvatar;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleSalleServeur;

public class ControleurSalleServeur extends ControleurServeur {
	
	private ModeleSalleServeur modele;

	public ControleurSalleServeur(Connexion connexion, ControleurServeurThread ctrlThread, ModeleSalleServeur modele) {
		super(connexion, ctrlThread);
		this.modele = modele;
	}
	
	public void actionDeplacerAvatar(Point p) {
		this.getModeleClient().getAvatar().setPosition(p);
		
		this.cmdAfficherDeplacementAvatar(p);
	}

	private void cmdAfficherDeplacementAvatar(Point p) {
		Commande cmd = new CmdAfficherDeplacementAvatar(this.getModeleClient().getId(), p);
		
		for(ModeleClientServeur client : this.modele.getLstClients().values()) {
			client.getConnexion().envoyerCommande(cmd);
		}
	}
	
	public void quitterSalle() {
		this.modele.deconnecter(this.getModeleClient());
	}
}