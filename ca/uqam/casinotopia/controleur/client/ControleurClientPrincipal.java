package ca.uqam.casinotopia.controleur.client;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.serveur.CmdAuthentifierClient;
import ca.uqam.casinotopia.commande.serveur.CmdJouerRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.InfoClientPrincipal;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.vue.ConnexionFrame;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.VueMenuPrincipal;
import ca.uqam.casinotopia.vue.VueRoulette;

public class ControleurClientPrincipal extends ControleurClient{

	
	/**
	 * Controleurs
	 */
	protected Map<String, ControleurClient> lstControleurs = new HashMap<String, ControleurClient>();
	/*private ControleurClientClient ctrlClientClient;
	private ControleurRouletteClient ctrlRouletteClient;
	private ControleurChatClient ctrlChatClient;*/

	/**
	 * Frames
	 */
	ConnexionFrame vueConnexionFrame;
	
	FrameApplication frameApplication;
	


	/**
	 * modele
	 */
	InfoClientPrincipal modele;
	
	
	
	boolean enReceptionDeCommande = false;
	
	
	public ControleurClientPrincipal() {
		this.modele = new InfoClientPrincipal();
		
		this.frameApplication = new FrameApplication();
		
		this.afficherInterface();
	}
	
	public void ajouterControleur(String nom, ControleurClient ctrl) {
		this.lstControleurs.put(nom, ctrl);
	}
	
	
	/*private void initControleur() {
		this.ctrlClientClient = new ControleurClientClient(this.getConnexion());
		this.ctrlRouletteClient = new ControleurRouletteClient(this.getConnexion());
	}*/
	
	/**
	 * Afficher l'interface de connexion au serveur
	 */
	private void afficherInterface() {
		this.vueConnexionFrame = new ConnexionFrame(this);
		this.vueConnexionFrame.setVisible(true);
	}
	
	public void connexionAuServeur() {
		if(!getConnexion().isConnected()){
			System.out.println("recherche de serveur...");
			this.setMessageConnexion("recherche de serveur...");
			int i = 0;
			while(this.getConnexion().isConnected() == false && i < this.modele.listeServeur.length){
				this.setConnexion(new Connexion(this.modele.listeServeur[i], 7777));
				i++;
			}
		}
		
		if(getConnexion().isConnected()) {
			setMessageConnexion("connecté!");
			
			//this.initControleur();
			//this.afficherFrameApplicationRoulette();
			//this.afficherMenuPrincipal();

			Commande cmd = new CmdAuthentifierClient(this.vueConnexionFrame.getTxtNomUtilisateur().getText(), this.vueConnexionFrame.getTxtMotDePasse().getPassword());
			this.getConnexion().envoyerCommande(cmd);
			
			
			this.receptionCommandes();
			
			System.out.println("FIN DE CONNEXION");
			
		}
	}
	
	private void receptionCommandes() {
		if(!this.enReceptionDeCommande){
			this.enReceptionDeCommande = true;
			new Thread(new ClientThread(this)).start();
		}
	}

	


	public ConnexionFrame getVueConnexionFrame() {
		return this.vueConnexionFrame;
	}




	public void setVueConnexionFrame(ConnexionFrame vueConnexionFrame) {
		this.vueConnexionFrame = vueConnexionFrame;
	}

	public void setMessageConnexionErreur(String message) {
		this.vueConnexionFrame.setMessageErreur(message);
	}
	
	public void setMessageConnexion(String message){
		this.vueConnexionFrame.setMessage(message);
	}
	
	/*public void afficherFrameApplication() {
		this.frameApplication = new FrameApplication();
		this.lstControleurs.get("ControleurChatClient") ctrlChatClient.setVue(new VueChat(ctrlChatClient));
		this.frameApplication.addOrReplace("VueChat", ctrlChatClient.getVue());
		this.frameApplication.setVisible(true);
	}*/
	
	/*public void afficherFrameApplicationRoulette() {
		VueRoulette vueRoulette = new VueRoulette(this.ctrlRouletteClient);
		this.frameApplication.addOrReplace("VueRoulette", vueRoulette);
		//this.frameApplication.changeContentPane(vueRoulette);
		EventQueue.invokeLater(this.frameApplication);
		
		this.ctrlRouletteClient.ajouterVue(vueRoulette);
	}*/
	
	public void afficherFrameApplication() {
		this.frameApplication = new FrameApplication();
		EventQueue.invokeLater(this.frameApplication);
	}
	
	public void actionAfficherMenuPrincipal() {
		if(this.frameApplication == null) {
			this.afficherFrameApplication();
		}
		this.vueConnexionFrame.dispose();
		VueMenuPrincipal vueMenuPrincipal = new VueMenuPrincipal(this);
		this.frameApplication.addOrReplace("VueMenuPrincipal", vueMenuPrincipal);
		
		this.ajouterVue(vueMenuPrincipal);
	}
	
	public void cmdJouerRoulette() {
		System.out.println("Envoyer Commande Jouer Roulette");
		
		//TODO Récupérer l'id du jeu de roulette auquel le client veut jouer.
		
		int idJeu = 2;
		
		this.connexion.envoyerCommande(new CmdJouerRoulette(idJeu));
	}
	
	public void actionAfficherJeuRoulette(ModelePartieRouletteClient modele) {
		System.out.println("AFFICHER ROULETTE CHEZ CLIENT");
		
		
		
		this.ajouterControleur("ControleurRouletteClient", new ControleurRouletteClient(this.connexion, modele, this.frameApplication));
		
		VueRoulette vueRoulette = new VueRoulette(this.lstControleurs.get("ControleurRouletteClient"), this.frameApplication);
		//VueRouletteTapis_bad vueRoulette = new VueRouletteTapis_bad(this.lstControleurs.get("ControleurRouletteClient"));
		this.frameApplication.removeAll();
		this.frameApplication.addOrReplace("VueRoulette", vueRoulette);
		//this.frameApplication.changeContentPane(vueRoulette);
		//EventQueue.invokeLater(this.frameApplication);
		
		System.out.println(this.frameApplication.getComponentMap());
		
		this.lstControleurs.get("ControleurRouletteClient").ajouterVue(vueRoulette);
	}
	


	
	/*public ControleurClientClient getCtrlClientClient() {
		return this.ctrlClientClient;
	}
	
	public ControleurRouletteClient getCtrlRouletteClient() {
		return this.ctrlRouletteClient;
	}
	
	public ControleurChatClient getCtrlChatClient() {
		return ctrlChatClient;
	}*/
	
	
}






/*
 * 	private FrameApplication frameApplication; 
		//Utile de mettre sa dans un if? si on est ici = on est connecté?
		if(this.getConnexion().isConnected()) {
			this.frameApplication = new FrameApplication();
			EventQueue.invokeLater(this.frameApplication);
		}        
		while(this.getConnexion().isConnected()){
            Commande cmd = null;
            try {
				cmd = (Commande) getConnexion().getObjectInputStream().readObject();
	            if(cmd != null){
	            	if(cmd instanceof CommandeClient) {
		            	if(cmd instanceof CommandeClientControleurClient) {
		            		cmd.action(new ControleurClientClient(this.getConnexion()), this.frameApplication);
		            	}
		            	else if(cmd instanceof CommandeClientControleurRoulette) {
		            		cmd.action(new ControleurRouletteClient(this.getConnexion()), this.frameApplication);
		            	}
	            	}
	            	else {
	            		System.err.println("Seulement des commandes destinées aux clients sont recevables!");
	            	}
	            }
	            else{
	            	System.err.println("Un problème est survenu (commande nulle).");
	            }
	            */
