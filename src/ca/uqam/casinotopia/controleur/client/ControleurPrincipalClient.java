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
import ca.uqam.casinotopia.modele.client.ModeleSalleClient;

public class ControleurPrincipalClient extends ControleurClient {

	private static final long serialVersionUID = 8686179456363168242L;

	private String[] listeServeur;

	private boolean enReceptionDeCommande = false;

	// private ModelePrincipalClient modele;

	public ControleurPrincipalClient() {
		super(new ModelePrincipalClient());
		// this.modele = new ModelePrincipalClient();
		this.modeleNav.ajouterControleur("ControleurPrincipalClient", this);
		this.modeleNav.initFrame();
		this.listeServeur = new String[] { "192.168.1.82", "localhost", "dan.dnsd.me", "oli.dnsd.me" };
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
		this.modeleNav.initFrameApplication();
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

	public void setMessageConnexionErreur(String message) {
		this.modeleNav.getFrameConnexion().setMessageErreur(message);
	}

	public void setMessageConnexion(String message) {
		this.modeleNav.getFrameConnexion().setMessage(message);
	}

	public void actionInitClient(ModeleClientClient modele) {
		this.client = modele;
		
		this.afficherFrameApplication();
		this.afficherMenuPrincipal();
	}

	public void actionAfficherMenuPrincipal() {
		/*if(this.modeleNav.getFrameApplication() == null) {
			this.afficherFrameApplication();
		}*/

		this.afficherMenuPrincipal();
	}
	
	private void afficherMenuPrincipal() {
		ControleurMenuPrincipal ctrlMenuPrincipal = new ControleurMenuPrincipal(this.connexion, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurMenuPrincipal", ctrlMenuPrincipal);
		this.modeleNav.cacherFrameConnexion();
		this.modeleNav.changerVueFrameApplication("VueMenuPrincipal", ctrlMenuPrincipal.getVue());
	}

	public void actionAfficherSalle(ModeleSalleClient modele) {
		//TODO Est-ce que le modeleSalleClient a besoin d'avoir la liste des autres clients?
		//Ou alors il lui faut seulement une reference vers le client lui-même?
		modele.ajouterClient(this.client);
		ControleurSalleClient ctrlSalle = new ControleurSalleClient(this.connexion, modele, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurSalleClient", ctrlSalle);
		this.modeleNav.changerVueFrameApplication("VueSalle", ctrlSalle.getVue());
		
		ctrlSalle.getVue().demarrerMondeVirtuel();
		
		/*Thread threadMondeVirtuel = new Thread(ctrlSalle.getVue());
		threadMondeVirtuel.start();*/
	}
	
	/*public void afficherSalle(String nomSalle) {
		ControleurSalleClient ctrlSalle = new ControleurSalleClient(this.connexion, new ModeleSalleClient(nomSalle), this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurSalleClient", ctrlSalle);
		this.modeleNav.cacherFrameConnexion();
		this.modeleNav.changerVueFrameApplication("VueSalle", ctrlSalle.getVue());
	}*/

	public void actionAfficherChat(ModeleChatClient modele) {
		ControleurChatClient ctrlChatClient = new ControleurChatClient(this.connexion, modele, this.client, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurChatClient", ctrlChatClient);
		this.modeleNav.changerVueFrameApplication("VueChat", ctrlChatClient.getVue());
	}

	public void actionAfficherJeuRoulette(ModelePartieRouletteClient modele) {
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
