package ca.uqam.casinotopia.controleur.client;

import ca.uqam.casinotopia.commande.serveur.CmdJoindreSalle;
import ca.uqam.casinotopia.commande.serveur.CmdJouerRoulette;
import ca.uqam.casinotopia.commande.serveur.machine.CmdJouerMachine;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.vue.VueMenuPrincipal;

public class ControleurMenuPrincipal extends ControleurClient {

	private static final long serialVersionUID = -3188096152156233418L;
	
	private VueMenuPrincipal vue;

	public ControleurMenuPrincipal(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNavigation) {
		super(connexion, client, modeleNavigation);
		this.vue = new VueMenuPrincipal(this);
	}

	public void cmdJouerRoulette() {
		// TODO Récupérer l'id du jeu de roulette auquel le client veut jouer.

		int idJeu = 2;

		this.connexion.envoyerCommande(new CmdJouerRoulette(idJeu, null, null));
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
	
	public void cmdJoindreSalle(String nomSalle) {
		this.connexion.envoyerCommande(new CmdJoindreSalle(nomSalle));
		
	}
	
	public void actionJouerMachine() {
		this.connexion.envoyerCommande(new CmdJouerMachine());
		
	}

	/*public void afficherSalle(String nomSalle) {
		ControleurSalleClient ctrlSalle = new ControleurSalleClient(this.connexion, new ModeleSalleClient(nomSalle), this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurSalleClient", ctrlSalle);
		this.modeleNav.cacherFrameConnexion();
		this.modeleNav.changerVueFrameApplication("VueSalle", ctrlSalle.getVue());
		//ctrlSalle.getVue().loopMouvement();
	}*/
}
