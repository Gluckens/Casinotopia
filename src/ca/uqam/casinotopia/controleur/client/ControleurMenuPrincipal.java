package ca.uqam.casinotopia.controleur.client;

import ca.uqam.casinotopia.commande.serveur.machine.CmdJouerMachine;
import ca.uqam.casinotopia.commande.serveur.roulette.CmdJouerRoulette;
import ca.uqam.casinotopia.commande.serveur.salle.CmdJoindreSalle;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.type.TypeJeuArgent;
import ca.uqam.casinotopia.type.TypeJeuMultijoueurs;
import ca.uqam.casinotopia.vue.navigation.VueMenuPrincipal;

/**
 * Controleur gérant les actions du menu principal.
 */
public class ControleurMenuPrincipal extends ControleurClient {
	
	/**
	 * La vue du menu principal
	 */
	private VueMenuPrincipal vue;

	public ControleurMenuPrincipal(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNavigation) {
		super(connexion, client, modeleNavigation);
		this.vue = new VueMenuPrincipal(this);
	}

	//TODO Enlever le bouton et obliger de passer par la salle pour jouer
	public void cmdJouerRoulette() {
		int idJeu = 1;

		this.connexion.envoyerCommande(new CmdJouerRoulette(idJeu, TypeJeuMultijoueurs.INCONNUS, TypeJeuArgent.ARGENT));
	}

	public VueMenuPrincipal getVue() {
		return this.vue;
	}

	/**
	 * Afficher la vue du chat.
	 * 
	 * @param modeleChatClient Le modèle du chat à joindre
	 */
	public void actionAfficherChat(ModeleChatClient modeleChatClient) {
		((ControleurPrincipalClient) this.modeleNav.getControleur("ControleurPrincipalClient")).actionAfficherChat(new ModeleChatClient());
	}
	
	/**
	 * Envoyer une commande au serveur pour joindre et afficher une salle.
	 * 
	 * @param idSalle L'id de la salle à joindre
	 */
	public void cmdJoindreSalle(int idSalle) {
		this.connexion.envoyerCommande(new CmdJoindreSalle(idSalle));
	}
	
	/**
	 * Envoyer une commande au serveur pour joueur à la machine à sous.
	 */
	public void cmdJouerMachine() {
		int idJeu = 5;
		this.connexion.envoyerCommande(new CmdJouerMachine(idJeu));
	}
}