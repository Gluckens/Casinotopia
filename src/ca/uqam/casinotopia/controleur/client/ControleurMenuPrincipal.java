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

public class ControleurMenuPrincipal extends ControleurClient {
	
	private VueMenuPrincipal vue;

	public ControleurMenuPrincipal(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNavigation) {
		super(connexion, client, modeleNavigation);
		this.vue = new VueMenuPrincipal(this);
	}

	//TODO Enlever le bouton et obliger de passer par la salle pour joueur
	public void cmdJouerRoulette() {
		// TODO Récupérer l'id du jeu de roulette auquel le client veut jouer.

		int idJeu = 2;

		this.connexion.envoyerCommande(new CmdJouerRoulette(idJeu, TypeJeuMultijoueurs.INCONNUS, TypeJeuArgent.ARGENT));
	}

	/**
	 * @return the vue
	 */
	public VueMenuPrincipal getVue() {
		return this.vue;
	}

	public void actionAfficherChat(ModeleChatClient modeleChatClient) {
		((ControleurPrincipalClient) this.modeleNav.getControleur("ControleurPrincipalClient")).actionAfficherChat(new ModeleChatClient());
	}
	
	public void cmdJoindreSalle(int idSalle) {
		this.connexion.envoyerCommande(new CmdJoindreSalle(idSalle));
	}
		
	public void actionJouerMachine() {
		this.connexion.envoyerCommande(new CmdJouerMachine());
	}
}