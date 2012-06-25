package ca.uqam.casinotopia.controleur.client;

import java.awt.EventQueue;
import javax.swing.JOptionPane;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.serveur.authentification.CmdAuthentifierClient;
import ca.uqam.casinotopia.commande.serveur.compte.CmdCreerCompte;
import ca.uqam.casinotopia.commande.serveur.compte.CmdModifierCompte;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePartieMachineClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.modele.client.ModeleSalleClient;

/**
 * Controleur principal c�t� client.
 * C'est lui qui cr�e les autres controleur, au besoin.
 */
public class ControleurPrincipalClient extends ControleurClient {

	private String[] listeServeur;

	private boolean enReceptionDeCommande = false;

	public ControleurPrincipalClient() {
		super(new ModelePrincipalClient());
		this.modeleNav.ajouterControleur("ControleurPrincipalClient", this);
		this.modeleNav.initFrameConnexion();
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

	/**
	 * Afficher l'interface de base de l'application
	 */
	public void afficherFrameApplication() {
		this.modeleNav.initFrameApplication();
		ControleurBarreMenuBas ctrl = new ControleurBarreMenuBas(this.connexion, this.client, this.getModeleNav());
		this.modeleNav.ajouterControleur("ControleurBarreMenuBas", ctrl);
		this.modeleNav.changerMenuFrameApplication("VueBarreMenuBas", ctrl.getVue());
		EventQueue.invokeLater(this.modeleNav.getFrameApplication());
	}

	/**
	 * Envoyer une commande au serveur pour authentifier l'utilisateur
	 * 
	 * @param nomUtilisateur Nom d'utilisateur de l'utilisateur
	 * @param motDePasse Mot de passe de l'utilisateur
	 */
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
			this.setMessageConnexion("connect�!");

			this.connexion.envoyerCommande(new CmdAuthentifierClient(nomUtilisateur, motDePasse));

			this.receptionCommandes();

			System.out.println("FIN DE CONNEXION");
		}
	}

	/**
	 * Cr�er un thread pour g�rer la r�ception de commandes provenant du serveur
	 */
	private void receptionCommandes() {
		if (!this.enReceptionDeCommande) {
			this.enReceptionDeCommande = true;
			new Thread(new ClientThread(this)).start();
		}
	}

	/**
	 * Afficher un message d'erreur dans le frame de connexion
	 * 
	 * @param message Le message d'erreur � afficher
	 */
	public void setMessageConnexionErreur(String message) {
		this.modeleNav.getFrameConnexion().setMessageErreur(message);
	}

	/**
	 * Afficher un message dans le frame de connexion
	 * 
	 * @param message Le message � afficher
	 */
	public void setMessageConnexion(String message) {
		this.modeleNav.getFrameConnexion().setMessage(message);
	}

	/**
	 * Initialiser l'environnement client suite � son authentification.
	 * Cette action est ex�cut�e suite � la demande du serveur (commande)
	 * 
	 * @param modele Le mod�le du client
	 */
	public void actionInitClient(ModeleClientClient modele) {
		this.client = modele;
		
		ControleurClientClient ctrlClient = new ControleurClientClient(this.connexion, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurClientClient", ctrlClient);
		
		this.afficherFrameApplication();
		this.afficherMenuPrincipal();
	}

	/**
	 * Afficher la vue de cr�ation de compte.
	 */
	public void cmdAfficherCreationCompte() {
		modeleNav.initFrameGestionCompte(this, true);
		EventQueue.invokeLater(this.modeleNav.getFrameGestionCompte());	
	}

	/**
	 * Envoyer une commande au serveur pour proc�der � la cr�ation d'un compte.
	 * 
	 * @param modeleClientClient Le mod�le client contenant les informations du compte � cr�er.
	 */
	public void cmdCreerCompte(ModeleClientClient modeleClientClient) {
		if (!this.connexion.isConnected()) {
			this.setMessageConnexion("recherche de serveur...");
			int i = 0;
			while (this.connexion.isConnected() == false && i < this.listeServeur.length) {
				this.setConnexion(new Connexion(this.listeServeur[i], 7777));
				i++;
			}
		}
		
		ControleurClientClient ctrlClient = new ControleurClientClient(this.connexion, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurClientClient", ctrlClient);
		
		Commande cmd = new CmdCreerCompte(modeleClientClient);
		this.connexion.envoyerCommande(cmd);
		//TODO Pourquoi il faut r�initialiser la r�ception? Sa cr�e un autre thread...?
		//this.receptionCommandes();
	}

	/**
	 * Afficher un popup au client
	 * 
	 * @param message Le message � afficher dans le popup
	 */
	public void setMessageInformationCreationCompte(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void afficherModificationCompte() {
		modeleNav.initFrameGestionCompte(this, false);
		EventQueue.invokeLater(this.modeleNav.getFrameGestionCompte());	
	}

	/**
	 * Envoyer une commande au serveur pour proc�der � la modification du compte du client.
	 * Si les modifications r�ussisent c�t� serveur, une commande sera retourn� au client
	 * pour mettre � jour son propre mod�le client.
	 * 
	 * @param modeleClientClient Le mod�le client temporaire contenant les modifications � valider c�t� serveur
	 */
	public void cmdModifierCompte(ModeleClientClient modeleClientClient) {
		ControleurClientClient ctrlClient = new ControleurClientClient(this.connexion, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurClientClient", ctrlClient);
		
		Commande cmd = new CmdModifierCompte(modeleClientClient);
		this.connexion.envoyerCommande(cmd);
		//TODO Pourquoi il faut r�initialiser la r�ception? Sa cr�e un autre thread...?
		//this.receptionCommandes();
	}

	/**
	 * Afficher la vue du menu principal.
	 * Cette action est ex�cut�e suite � la demande du serveur (commande)
	 */
	public void actionAfficherMenuPrincipal() {
		this.afficherMenuPrincipal();
	}
	
	/**
	 * Initialise et affiche le menu principal
	 */
	private void afficherMenuPrincipal() {
		ControleurMenuPrincipal ctrlMenuPrincipal = new ControleurMenuPrincipal(this.connexion, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurMenuPrincipal", ctrlMenuPrincipal);
		this.modeleNav.cacherFrameConnexion();
		this.modeleNav.changerVueFrameApplication("VueMenuPrincipal", ctrlMenuPrincipal.getVue());
	}

	/**
	 * Afficher une salle et d�marrer le monde virtuel (avatars)
	 * Cette action est ex�cut�e suite � la demande du serveur (commande)
	 * 
	 * @param modele Le mod�le de la salle � afficher
	 */
	public void actionAfficherSalle(ModeleSalleClient modele) {
		modele.ajouterClient(this.client);
		ControleurSalleClient ctrlSalle = new ControleurSalleClient(this.connexion, modele, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurSalleClient", ctrlSalle);
		this.modeleNav.changerVueFrameApplication("VueSalle", ctrlSalle.getVue());
		
		ctrlSalle.getVue().demarrerMondeVirtuel();
	}

	/**
	 * Afficher le chat.
	 * 
	 * @param modele Le mod�le du chat � afficher
	 */
	public void actionAfficherChat(ModeleChatClient modele) {
		ControleurChatClient ctrlChatClient = new ControleurChatClient(this.connexion, modele, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurChatClient", ctrlChatClient);
		this.modeleNav.changerVueFrameApplication("VueChat", ctrlChatClient.getVue());
	}

	/**
	 * Informer le client qu'il est en attente de recherche d'une partie.
	 */
	public void actionAfficherAttentePartie() {
		//TODO faire une vue/fenetre/popup
		System.out.println("En attente d'autres joueurs...");
	}

	/**
	 * Afficher le jeu de roulette
	 * Cette action est ex�cut�e suite � la demande du serveur (commande)
	 * 
	 * @param modele Le modele de la partie de roulette
	 */
	public void actionAfficherJeuRoulette(ModelePartieRouletteClient modele) {
		ControleurRouletteClient ctrlRouletteClient = new ControleurRouletteClient(this.connexion, modele, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurRouletteClient", ctrlRouletteClient);
		this.modeleNav.changerVueFrameApplication("VueRoulette", ctrlRouletteClient.getVue());
		//TODO Forcer le refresh via le pattern observeur?
		ctrlRouletteClient.actionUpdateTableJeu(modele.getTableJeu().getCases());
	}

	/**
	 * Afficher le jeu de machine � sous
	 * Cette action est ex�cut�e suite � la demande du serveur (commande)
	 * 
	 * @param modele Le modele de la partie de machine � sous
	 */
	public void actionAfficherJeuMachine(ModelePartieMachineClient modele) {
		ControleurMachineClient ctrlMachineClient = new ControleurMachineClient(this.connexion, modele, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurMachineClient", ctrlMachineClient);
		this.modeleNav.changerVueFrameApplication("VueRoulette", ctrlMachineClient.getVue());
	}

	/**
	 * Quitter une partie de roulette et retourner � la salle.
	 * Supprime le controleur roulette de l'environnement du client.
	 */
	public void actionQuitterPartieRouletteClient() {
		ControleurRouletteClient ctrlRoulette = (ControleurRouletteClient) this.modeleNav.getControleur("ControleurRouletteClient");
		ControleurMenuPrincipal ctrlMenu = (ControleurMenuPrincipal) this.modeleNav.getControleur("ControleurMenuPrincipal");
		
		this.modeleNav.retirerControleur("ControleurRouletteClient");
		
		ctrlMenu.cmdJoindreSalle(ctrlRoulette.getModele().getInfoJeu().getIdSalle());
	}
	
	/**
	 * Quitter une partie de machine � sous et retourner � la salle.
	 * Supprime le controleur machine de l'environnement du client.
	 */
	public void actionQuitterPartieMachineClient() {
		ControleurMachineClient ctrlMachine = (ControleurMachineClient) this.modeleNav.getControleur("ControleurMachineClient");
		ControleurMenuPrincipal ctrlMenu = (ControleurMenuPrincipal) this.modeleNav.getControleur("ControleurMenuPrincipal");
		
		this.modeleNav.retirerControleur("ControleurMachineClient");

		ctrlMenu.cmdJoindreSalle(ctrlMachine.getModele().getInfoJeu().getIdSalle());
	}
	
	/**
	 * Quitter le chat principal et retourner au menu principal.
	 * Supprime le controleur chat de l'environnement du client.
	 */
	public void actionQuitterChatClient() {
		this.modeleNav.retirerControleur("ControleurChatClient");
		this.afficherMenuPrincipal();
	}

	/**
	 * Quitter une salle et retourner au menu principal.
	 * Supprime le controleur salle de l'environnement du client.
	 */
	public void actionQuitterSalleClient() {
		ControleurSalleClient ctrlSalle = (ControleurSalleClient) this.modeleNav.getControleur("ControleurSalleClient");
		ctrlSalle.quitterSalleClient();
		
		this.modeleNav.retirerControleur("ControleurSalleClient");
		this.afficherMenuPrincipal();
	}
}