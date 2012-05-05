package ca.uqam.casinotopia.controleur.client;

import java.awt.Point;

import ca.uqam.casinotopia.commande.serveur.CmdDeplacerAvatar;
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

		this.vue = new VueSalle(this, modele.getLstClients(), client.getId());
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
		//System.out.println("DEPLACEMENT CLIENT ###############################################################");
		if(this.client.getId() == 2 && idClient == 1) {
			//System.out.println("DEPLACEMENT CLIENT ###############################################################");
		}
		if(this.client.getId() == 1) {
			//System.out.println("DEPLACEMENT CLIENT ###############################################################");
		}
		
		//TODO DEBUGUER STE LIGNE LA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		this.modele.getClient(idClient).getAvatar().setPosition(position);
		//this.client.getAvatar().setPosition(position);
	}

	public void actionAjouterClientSalle(ModeleClientClient nouveauClient) {
		nouveauClient.getAvatar().ajouterObservateur(this.vue);
		this.modele.ajouterClient(nouveauClient);
	}
	
	public VueSalle getVue() {
		return this.vue;
	}
	
	public ModeleSalleClient getModele() {
		return this.modele;
	}

}
