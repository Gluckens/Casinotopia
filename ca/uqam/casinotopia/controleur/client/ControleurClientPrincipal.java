package ca.uqam.casinotopia.controleur.client;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.serveur.AuthentifierClient;
import ca.uqam.casinotopia.commande.serveur.CmdJouerRoulette;
import ca.uqam.casinotopia.commande.serveur.EnvoyerMessageChat;
import ca.uqam.casinotopia.commande.serveur.SeConnecterAuChat;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.InfoClientPrincipal;
import ca.uqam.casinotopia.vue.ConnexionFrame;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.VueChat;
import ca.uqam.casinotopia.vue.VueMenuPrincipal;
import ca.uqam.casinotopia.vue.VueRoulette;

public class ControleurClientPrincipal extends ControleurClient{

	
	/**
	 * Controleurs
	 */
	private ControleurClientClient ctrlClientClient;
	private ControleurRouletteClient ctrlRouletteClient;
	private ControleurChatClient ctrlChatClient;

	/**
	 * vues
	 */
	ConnexionFrame vueConnexionFrame;
	
	FrameApplication frameApplication;
	


	/**
	 * modele
	 */
	InfoClientPrincipal modele;
	
	
	
	boolean enReceptionDeCommande = false;
	
	
	public ControleurClientPrincipal() {
		this.initModele();
		
		this.frameApplication = new FrameApplication();
		
		this.afficherInterface();
	}
	
	
	private void initControleur() {
		this.ctrlClientClient = new ControleurClientClient(this.getConnexion());
		this.ctrlRouletteClient = new ControleurRouletteClient(this.getConnexion());
		this.ctrlChatClient = new ControleurChatClient(this.getConnexion());
		
	}
	
	
	private void initModele() {
		modele = new InfoClientPrincipal();
	}
	
	/**
	 * Afficher l'interface de connexion au serveur
	 */
	private void afficherInterface() {
		vueConnexionFrame = new ConnexionFrame(this);
		vueConnexionFrame.setVisible(true);
	}
	
	public void connexionAuServeur() {
		if(!getConnexion().isConnected()){
			System.out.println("recherche de serveur...");
			setMessageConnexion("recherche de serveur...");
			int i = 0;
			while(getConnexion().isConnected() == false && i < modele.listeServeur.length){
				setConnexion(new Connexion(modele.listeServeur[i], 7777));
				i++;
			}
		}
		
		if(getConnexion().isConnected()) {
			setMessageConnexion("connecté!");
			
			this.initControleur();
			//this.afficherFrameApplicationRoulette();
			this.afficherMenuPrincipal();

			Commande cmd = new AuthentifierClient(vueConnexionFrame.getTxtNomUtilisateur().getText(),vueConnexionFrame.getTxtMotDePasse().getPassword());
			this.getConnexion().envoyerCommande(cmd);
			
			
			receptionCommandes();
			
			System.out.println("FIN DE CONNEXION");
			
		}
	}
	
	private void receptionCommandes() {
		if(!enReceptionDeCommande){
			enReceptionDeCommande = true;
			new Thread(new ClientThread(this)).start();
		}
	}

	


	public ConnexionFrame getVueConnexionFrame() {
		return vueConnexionFrame;
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
	
	public void afficherFrameApplication() {
		this.frameApplication = new FrameApplication();
		ctrlChatClient.setVue(new VueChat(ctrlChatClient));
		this.frameApplication.addOrReplace("chat", ctrlChatClient.getVue());
		this.frameApplication.setVisible(true);
	}
	
	public void afficherFrameApplicationRoulette() {
		VueRoulette vueRoulette = new VueRoulette(this.ctrlRouletteClient);
		this.frameApplication.addOrReplace("VueRoulette", vueRoulette);
		//this.frameApplication.changeContentPane(vueRoulette);
		EventQueue.invokeLater(this.frameApplication);
		
		this.ctrlRouletteClient.ajouterVue(vueRoulette);
	}
	
	public void afficherMenuPrincipal() {
		this.vueConnexionFrame.dispose();
		VueMenuPrincipal vueMenuPrincipal = new VueMenuPrincipal(this);
		this.frameApplication.addOrReplace("VueMenuPrincipal", vueMenuPrincipal);
		//this.frameApplication.changeContentPane(vueRoulette);
		EventQueue.invokeLater(this.frameApplication);
		
		this.ctrlRouletteClient.ajouterVue(vueMenuPrincipal);
	}
	
	public void cmdJouerRoulette() {
		System.out.println("Envoyer Commande Jouer Roulette");
		this.connexion.envoyerCommande(new CmdJouerRoulette());
	}
	
	public void actionAfficherJeuRoulette(int idPartie) {
		System.out.println("AFFICHER ROULETTE CHEZ CLIENT");
		
		VueRoulette vueRoulette = new VueRoulette(this.ctrlRouletteClient);
		this.frameApplication.removeAll();
		this.frameApplication.addOrReplace("VueRoulette", vueRoulette);
		//this.frameApplication.changeContentPane(vueRoulette);
		//EventQueue.invokeLater(this.frameApplication);
		
		System.out.println(this.frameApplication.getComponentMap());
		
		this.ctrlRouletteClient.ajouterVue(vueRoulette);
	}
	


	
	public ControleurClientClient getCtrlClientClient() {
		return this.ctrlClientClient;
	}
	
	public ControleurRouletteClient getCtrlRouletteClient() {
		return this.ctrlRouletteClient;
	}
	
	public ControleurChatClient getCtrlChatClient() {
		return ctrlChatClient;
	}
	
	
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
