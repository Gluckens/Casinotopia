package ca.uqam.casinotopia.controleur.client;

import java.awt.Point;

import javax.swing.JPanel;

import ca.uqam.casinotopia.commande.serveur.machine.CmdJouerMachine;
import ca.uqam.casinotopia.commande.serveur.roulette.CmdJouerRoulette;
import ca.uqam.casinotopia.commande.serveur.salle.CmdDeplacerAvatar;
import ca.uqam.casinotopia.commande.serveur.salle.CmdQuitterSalle;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.modele.client.ModeleSalleClient;
import ca.uqam.casinotopia.objet.JeuClient;
import ca.uqam.casinotopia.type.TypeJeuArgent;
import ca.uqam.casinotopia.type.TypeJeuMultijoueurs;
import ca.uqam.casinotopia.vue.salle.VueSalle;

/**
 * Controleur g�rant les actions des salles.
 */
public class ControleurSalleClient extends ControleurClient {
	
	/**
	 * La vue de la salle
	 */
	private VueSalle vue;
	
	/**
	 * Le mod�le de la salle
	 */
	private ModeleSalleClient modele;

	public ControleurSalleClient(Connexion connexion, ModeleSalleClient modele, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion, client, modeleNav);

		this.vue = new VueSalle(this, modele.getLstClients(), client.getId(), modele.getLstJeux());
		this.modele = modele;
		this.modele.ajouterObservateur(this.vue);
		
		for(ModeleClientClient c : this.modele.getLstClients().values()) {
			c.getAvatar().ajouterObservateur(this.vue);
		}
	}
	
	/**
	 * Envoyer une commande pour effectuer un d�placement de l'avatar du client.
	 * 
	 * @param position La nouvelle position de l'avatar
	 */
	public void cmdDeplacerAvatar(Point position) {
		this.connexion.envoyerCommande(new CmdDeplacerAvatar(position));
	}

	/**
	 * Afficher le d�placement de l'avatar d'un des clients de la salle
	 * 
	 * @param idClient L'id du client s'�tant d�plac�
	 * @param position Sa nouvelle position
	 */
	public void actionAfficherDeplacementAvatar(int idClient, Point position) {
		this.modele.getClient(idClient).getAvatar().setPosition(position);
	}

	/**
	 * Ajouter un nouveau client dans la salle et observer les d�placements de son avatar
	 * 
	 * @param nouveauClient Le mod�le du nouveau client
	 */
	public void actionAjouterClientSalle(ModeleClientClient nouveauClient) {
		nouveauClient.getAvatar().ajouterObservateur(this.vue);
		this.modele.ajouterClient(nouveauClient);
	}
	
	/**
	 * Retirer un client de la salle
	 * 
	 * @param idClient L'id du client � retirer
	 */
	public void actionRetirerClientSalle(int idClient) {
		this.modele.retirerClient(idClient);
	}
	
	/**
	 * Valider si un d�placement est possible, ie : pas de collision avec l'environnement.
	 * 
	 * @param position la position souhait�e
	 * @return true si le d�placement est valide, false sinon
	 */
	public boolean validerDeplacement(Point position) {
		JPanel pnlAvatars = (JPanel) this.vue.getComponentByName("pnlAvatars");
		return (pnlAvatars.getBounds().contains(this.client.getAvatar().getBounds(position))) && (this.modele.validerDeplacement(this.client.getAvatar(), position));
	}

	/**
	 * Regarder si la position de l'avatar est pr�s d'une table de jeu.
	 * Si oui, on afficher la vue de s�lection des options de jeu.
	 * Si non, on la cache.
	 * 
	 * @param position La position de l'avatar
	 */
	public void checkProximites(Point position) {
		JeuClient jeu = this.modele.checkProximites(this.client.getAvatar(), position);
		
		if(jeu != null) {
			
			this.vue.afficherSelectionOptionJeu(jeu);
		}
		else {
			this.vue.cacherSelectionOptionJeu();
		}
	}
	
	/**
	 * Envoyer une commande au serveur pour jouer � un jeu de la salle.
	 * 
	 * @param jeu Le jeu � jouer
	 * @param typeMultijoueurs Le type de jeu multijoueur recherch� par le client
	 * @param typeArgent le type de jeu d'argent recherch� par le client
	 */
	public void cmdJouerJeu(JeuClient jeu, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent) {
		switch(jeu.getType()) {
			case ROULETTE :
				this.connexion.envoyerCommande(new CmdJouerRoulette(jeu.getId(), typeMultijoueurs, typeArgent));
				break;
			case MACHINE :
				this.connexion.envoyerCommande(new CmdJouerMachine(jeu.getId()));
				break;
		}
	}

	/**
	 * Envoyer une commande au serveur pour quitter la salle
	 */
	public void cmdQuitterSalle() {
		this.connexion.envoyerCommande(new CmdQuitterSalle());
	}

	/**
	 * R�initialise le client par apport � la salle lorsque celui la quitte.
	 */
	public void quitterSalleClient() {
		this.client.getAvatar().retirerObservateur(this.vue);
		this.client.getAvatar().setPosition(new Point(0, 0));
	}
	
	public VueSalle getVue() {
		return this.vue;
	}
	
	public ModeleSalleClient getModele() {
		return this.modele;
	}
}