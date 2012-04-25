package ca.uqam.casinotopia.controleur.client;

import ca.uqam.casinotopia.commande.serveur.CmdJouerRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.vue.VueMenuPrincipal;

public class ControleurMenuPrincipal extends ControleurClient {

	private static final long serialVersionUID = -223658182174135843L;

	private VueMenuPrincipal vue;

	public ControleurMenuPrincipal(Connexion connexion, ModelePrincipalClient modeleNavigation) {
		super(connexion, modeleNavigation);
		this.vue = new VueMenuPrincipal(this);
	}

	public void cmdJouerRoulette() {
		System.out.println("Envoyer Commande Jouer Roulette");

		// TODO Récupérer l'id du jeu de roulette auquel le client veut jouer.

		int idJeu = 2;

		this.connexion.envoyerCommande(new CmdJouerRoulette(idJeu));
	}

	/**
	 * @return the vue
	 */
	public VueMenuPrincipal getVue() {
		return this.vue;
	}
}
