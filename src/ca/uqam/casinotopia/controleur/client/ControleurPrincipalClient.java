package ca.uqam.casinotopia.controleur.client;

import java.awt.EventQueue;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.serveur.CmdAuthentifierClient;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.vue.VueBarreMenuBas;

public class ControleurPrincipalClient extends ControleurClient {

	private static final long serialVersionUID = 7304176355439547634L;

	private String[] listeServeur;

	private boolean enReceptionDeCommande = false;

	// private ModelePrincipalClient modele;

	public ControleurPrincipalClient() {
		super(new ModeleClientClient(1), new ModelePrincipalClient());
		// this.modele = new ModelePrincipalClient();
		this.modeleNav.ajouterControleur("ControleurPrincipalClient", this);
		this.modeleNav.initFrame();
		this.listeServeur = new String[] { "localhost", "oli.dnsd.me", "dan.dnsd.me" };
		this.enReceptionDeCommande = false;
		this.afficherConnexion();
	}

	/**
	 * Afficher l'interface de connexion au serveur
	 */
	private void afficherConnexion() {
		EventQueue.invokeLater(this.modeleNav.getFrameConnexion());
	}

	public void afficherFrameApplication() {
		ControleurBarreMenuBas ctrl = new ControleurBarreMenuBas(this.connexion, this.client, this.getModeleNav());
		this.modeleNav.ajouterControleur("ControleurBarreMenuBas", ctrl);
		this.modeleNav.changerMenuFrameApplication("VueBarreMenuBas", ctrl.getVue());
		EventQueue.invokeLater(this.modeleNav.getFrameApplication());
	}

	public void cmdConnexionAuServeur(String nomUtilisateur, char[] motDePasse) {
		if (!this.connexion.isConnected()) {
			System.out.println("recherche de serveur...");
			this.setMessageConnexion("recherche de serveur...");
			int i = 0;
			while (this.connexion.isConnected() == false && i < this.listeServeur.length) {
				this.setConnexion(new Connexion(this.listeServeur[i], 7777));
				i++;
			}
		}

		if (this.connexion.isConnected()) {
			this.setMessageConnexion("connecté!");

			Commande cmd = new CmdAuthentifierClient(nomUtilisateur, motDePasse);
			this.connexion.envoyerCommande(cmd);

			this.receptionCommandes();

			System.out.println("FIN DE CONNEXION");

		}
	}

	private void receptionCommandes() {
		if (!this.enReceptionDeCommande) {
			this.enReceptionDeCommande = true;
			new Thread(new ClientThread(this)).start();
		}
	}

	/*
	 * public FrameConnexion getFrameConnexion() { return this.frameConnexion; }
	 */

	/*
	 * public void setVueFrameConnexion(FrameConnexion vueConnexionFrame) {
	 * this.frameConnexion = vueConnexionFrame; }
	 */

	public void setMessageConnexionErreur(String message) {
		this.modeleNav.getFrameConnexion().setMessageErreur(message);
	}

	public void setMessageConnexion(String message) {
		this.modeleNav.getFrameConnexion().setMessage(message);
	}

	public void actionAfficherMenuPrincipal() {
		if(this.modeleNav.getFrameApplication() == null) {
			this.afficherFrameApplication();
		}

		this.afficherMenuPrincipal();
	}
	
	private void afficherMenuPrincipal() {
		ControleurMenuPrincipal ctrlMenuPrincipal = new ControleurMenuPrincipal(this.connexion, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurMenuPrincipal", ctrlMenuPrincipal);
		this.modeleNav.cacherFrameConnexion();
		this.modeleNav.changerVueFrameApplication("VueChat", ctrlMenuPrincipal.getVue());
	}

	public void actionAfficherChat(ModeleChatClient modele) {
		ControleurChatClient ctrlChatClient = new ControleurChatClient(this.connexion, modele, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurChatClient", ctrlChatClient);
		this.modeleNav.changerVueFrameApplication("VueChat", ctrlChatClient.getVue());
	}

	public void actionAfficherJeuRoulette(ModelePartieRouletteClient modele) {
		
		
		
		System.out.println("AFFICHER ROULETTE CHEZ CLIENT");

		ControleurRouletteClient ctrlRouletteClient = new ControleurRouletteClient(this.connexion, modele, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurRouletteClient", ctrlRouletteClient);
		this.modeleNav.changerVueFrameApplication("VueRoulette", ctrlRouletteClient.getVue());
	}

	public void actionQuitterPartieRouletteClient() {
		//TODO Permet de coder des choses a faire dans al vue avant de quitter (et d'enlever le controleur)
		//Si on voudrait appeler directement la cmd AfficherMenuPrincipal, il faudrait envoyer en param le nom du controleur a enlever (et on perd la customisation avant le quittage)
		this.modeleNav.retirerControleur("ControleurRouletteClient");
		this.afficherMenuPrincipal();
	}

}

/*
 * private FrameApplication frameApplication; //Utile de mettre sa dans un if?
 * si on est ici = on est connecté? if(this.getConnexion().isConnected()) {
 * this.frameApplication = new FrameApplication();
 * EventQueue.invokeLater(this.frameApplication); }
 * while(this.getConnexion().isConnected()){ Commande cmd = null; try { cmd =
 * (Commande) getConnexion().getObjectInputStream().readObject(); if(cmd !=
 * null){ if(cmd instanceof CommandeClient) { if(cmd instanceof
 * CommandeClientControleurClient) { cmd.action(new
 * ControleurClientClient(this.getConnexion()), this.frameApplication); } else
 * if(cmd instanceof CommandeClientControleurRoulette) { cmd.action(new
 * ControleurRouletteClient(this.getConnexion()), this.frameApplication); } }
 * else { System.err.println(
 * "Seulement des commandes destinées aux clients sont recevables!"); } } else{
 * System.err.println("Un problème est survenu (commande nulle)."); }
 */
