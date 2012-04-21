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
import ca.uqam.casinotopia.modele.client.ModeleChatClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.vue.FrameConnexion;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.VueChat;
import ca.uqam.casinotopia.vue.VueMenuPrincipal;
import ca.uqam.casinotopia.vue.roulette.VueRoulette;

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
	FrameConnexion frameConnexion;
	
	FrameApplication frameApplication;
	


	/**
	 * modele
	 */
	InfoClientPrincipal modele;
	
	
	
	boolean enReceptionDeCommande = false;
	
	
	public ControleurClientPrincipal() {
		this.modele = new InfoClientPrincipal();
		
		this.afficherInterface();
	}
	
	public void ajouterControleur(String nom, ControleurClient ctrl) {
		this.lstControleurs.put(nom, ctrl);
	}
	
	public void afficherFrameApplication() {
		this.frameApplication = new FrameApplication();
		EventQueue.invokeLater(this.frameApplication);
	}
	
	
	/*private void initControleur() {
		this.ctrlClientClient = new ControleurClientClient(this.getConnexion());
		this.ctrlRouletteClient = new ControleurRouletteClient(this.getConnexion());
	}*/
	
	/**
	 * Afficher l'interface de connexion au serveur
	 */
	private void afficherInterface() {
		this.frameConnexion = new FrameConnexion(this);
		EventQueue.invokeLater(this.frameConnexion);
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

			Commande cmd = new CmdAuthentifierClient(this.frameConnexion.getTxtNomUtilisateur().getText(), this.frameConnexion.getTxtMotDePasse().getPassword());
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

	public FrameConnexion getFrameConnexion() {
		return this.frameConnexion;
	}


	/*public void setVueFrameConnexion(FrameConnexion vueConnexionFrame) {
		this.frameConnexion = vueConnexionFrame;
	}*/

	public void setMessageConnexionErreur(String message) {
		this.frameConnexion.setMessageErreur(message);
	}
	
	public void setMessageConnexion(String message){
		this.frameConnexion.setMessage(message);
	}
	
	public void actionAfficherMenuPrincipal() {
		if(this.frameApplication == null) {
			this.afficherFrameApplication();
		}
		this.frameConnexion.dispose();
		VueMenuPrincipal vueMenuPrincipal = new VueMenuPrincipal(this);
		this.frameApplication.addOrReplace("VueMenuPrincipal", vueMenuPrincipal);
		
		this.ajouterVue(vueMenuPrincipal);
	}
	
	public void actionAfficherChat(ModeleChatClient modele) {
		this.ajouterControleur("ControleurChatClient", new ControleurChatClient(this.connexion, modele));
		
		VueChat vueChat = new VueChat(this.lstControleurs.get("ControleurChatClient"));
		this.frameApplication.removeAll();
		this.frameApplication.addOrReplace("VueChat", vueChat);
		
		this.lstControleurs.get("ControleurChatClient").ajouterVue(vueChat);
	}
	
	public void cmdJouerRoulette() {
		System.out.println("Envoyer Commande Jouer Roulette");
		
		//TODO Récupérer l'id du jeu de ca.uqam.casinotopia.vue.roulette auquel le client veut jouer.
		
		int idJeu = 2;
		
		this.connexion.envoyerCommande(new CmdJouerRoulette(idJeu));
	}
	
	public void actionAfficherJeuRoulette(ModelePartieRouletteClient modele) {
		System.out.println("AFFICHER ROULETTE CHEZ CLIENT");
		
		
		this.ajouterControleur("ControleurRouletteClient", new ControleurRouletteClient(this.connexion, modele, this.frameApplication));
		
		
		VueRoulette vueRoulette = new VueRoulette(this.lstControleurs.get("ControleurRouletteClient"), this.frameApplication);
		((ModelePartieRouletteClient)this.lstControleurs.get("ControleurRouletteClient").getModele("ModelePartieRouletteClient")).ajouterObservateur(vueRoulette);
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
