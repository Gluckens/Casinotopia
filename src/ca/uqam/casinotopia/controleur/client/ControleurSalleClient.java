package ca.uqam.casinotopia.controleur.client;

import java.awt.Point;

import ca.uqam.casinotopia.JeuClient;
import ca.uqam.casinotopia.TypeJeuArgent;
import ca.uqam.casinotopia.TypeJeuMultijoueurs;
import ca.uqam.casinotopia.commande.serveur.CmdDeplacerAvatar;
import ca.uqam.casinotopia.commande.serveur.CmdJouerRoulette;
import ca.uqam.casinotopia.commande.serveur.CmdQuitterSalle;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.modele.client.ModeleSalleClient;
import ca.uqam.casinotopia.vue.VueSalle;

public class ControleurSalleClient extends ControleurClient {
	
	private static final long serialVersionUID = 1861469835865268658L;
	
	private VueSalle vue;
	private ModeleSalleClient modele;

	public ControleurSalleClient(Connexion connexion, ModeleSalleClient modele, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion, client, modeleNav);

		this.vue = new VueSalle(this, modele.getLstClients(), client.getId(), modele.getLstJeux());
		this.modele = modele;
		this.modele.ajouterObservateur(this.vue);
		
		/*for(ModeleClientClient c : this.modele.getLstClients()) {
			c.getAvatar().ajouterObservateur(this.vue);
		}*/
		
		for(ModeleClientClient c : this.modele.getLstClients().values()) {
			c.getAvatar().ajouterObservateur(this.vue);
		}
	}

	public void demarrerMondeVirtuel() {
		/*MondeVirtuel mondeVirtuel = new MondeVirtuel(this.client.getAvatar());
		
		Thread threadMondeVirtuel = new Thread(mondeVirtuel);
		threadMondeVirtuel.start();*/
	}
	
	public void cmdDeplacerAvatar(Point position) {
		this.connexion.envoyerCommande(new CmdDeplacerAvatar(position));
	}

	public void actionAfficherDeplacementAvatar(int idClient, Point position) {
		this.modele.getClient(idClient).getAvatar().setPosition(position);
	}

	public void actionAjouterClientSalle(ModeleClientClient nouveauClient) {
		nouveauClient.getAvatar().ajouterObservateur(this.vue);
		this.modele.ajouterClient(nouveauClient);
	}
	
	public void actionRetirerClientSalle(int idClient) {
		this.modele.retirerClient(idClient);
	}
	
	/*public void actionRetirerClientSalle(ModeleClientClient clientRetire) {
		clientRetire.getAvatar().retirerObservateur(this.vue);
		this.modele.retirerClient(clientRetire);
	}*/

	public void cmdQuitterSalle() {
		this.connexion.envoyerCommande(new CmdQuitterSalle());
	}
	
	public boolean validerDeplacement() {
		return this.modele.validerDeplacement(this.client.getAvatar());
	}
	
	public boolean validerDeplacement(Point position) {
		return (this.vue.getBounds().contains(this.client.getAvatar().getBounds(position))) && (this.modele.validerDeplacement(this.client.getAvatar(), position));
	}

	public void checkProximites(Point position) {
		JeuClient jeu = this.modele.checkProximites(this.client.getAvatar(), position);
		
		if(jeu != null) {
			//System.out.println("À PROXIMITÉ DU JEU : " + jeu.getId());
			
			this.vue.afficherSelectionOptionJeu(jeu);
		}
		else {
			//System.out.println("AUCUN JEU A PROXIMITÉ");
			this.vue.cacherSelectionOptionJeu();
		}
		
		
		//return this.modele.checkProximites(this.client.getAvatar(), position);
	}
	
	public void cmdJouerJeu(JeuClient jeu, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent) {
		switch(jeu.getType()) {
			case ROULETTE :
				this.connexion.envoyerCommande(new CmdJouerRoulette(jeu.getId(), typeMultijoueurs, typeArgent));
				break;
		}
	}
	
	public VueSalle getVue() {
		return this.vue;
	}
	
	public ModeleSalleClient getModele() {
		return this.modele;
	}
}
