package ca.uqam.casinotopia.controleur.client;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.serveur.*;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.InfoClientPrincipal;
import ca.uqam.casinotopia.vue.ConnexionFrame;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.PanelChat;
import ca.uqam.casinotopia.vue.VueMenuPrincipal;
import ca.uqam.casinotopia.vue.VueRoulette;
import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.commande.serveur.CmdAuthentifierClient;

public class ControleurClientPrincipal extends ControleurClient{

	
	/**
	 * Controleurs
	 */
	private ControleurClientClient ctrlClientClient;
	private ControleurRouletteClient ctrlRouletteClient;

	/**
	 * vues
	 */
	ConnexionFrame vueConnexionFrame;
	
	FrameApplication frameApplication;
	PanelChat pnlChat;
	//TODO a mettre dans un modele
	public String salle;
	


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
		
		System.out.println("OS ctrlRoulette : ");
		System.out.println(this.ctrlRouletteClient.getConnexion().getObjectOutputStream());
	}
	
	
	private void initModele() {
		this.modele = new InfoClientPrincipal();
	}
	
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
			
			this.initControleur();
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
	
	public void afficherFrameApplication() {
		this.frameApplication = new FrameApplication();
		this.pnlChat = new PanelChat(this);
		this.frameApplication.addOrReplace("chat", this.pnlChat);
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
		
		//TODO Récupérer l'id du jeu de roulette auquel le client veut jouer.
		
		int idJeu = 2;
		
		this.connexion.envoyerCommande(new CmdJouerRoulette(idJeu));
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
	
	public PanelChat getPnlChat() {
		return pnlChat;
	}


	public void setPnlChat(PanelChat pnlChat) {
		this.pnlChat = pnlChat;
	}


	public void setChatList(List<String> listeUtilisateur, List<String> listeMessages, String salle){
		this.setChatUtilisateur(listeUtilisateur);
		
		this.setChatMessages(listeMessages);
		this.pnlChat.lblTitre.setText(salle);
	}


	public void setChatUtilisateur(List<String> listeUtilisateur){

		DefaultListModel model = (DefaultListModel) pnlChat.lstConnecte.getModel();
		model.clear();
		for (int i = 0; i < listeUtilisateur.size(); i++) {
			model.add(i, listeUtilisateur.get(i));
		}
	}
	

	public void setChatMessages(List<String> listeMessages){
		String messages = "";
		for (int i = 0; i < listeMessages.size(); i++) {
			if(!listeMessages.get(i).isEmpty()){
				messages += listeMessages.get(i);
				if(i != listeMessages.size()-1){
					messages += "\n";
				}
			}
		}
		this.pnlChat.txtChat.setText(messages);

		JScrollBar jsb = this.pnlChat.scrollPane.getVerticalScrollBar();
		jsb.setValue(jsb.getMaximum());
	}

		

	public void seConnecterAuChat() {
		if(this.pnlChat.txtSeConnecterA.getText().isEmpty()) {
			this.pnlChat.txtSeConnecterA.setText("entrez un nom de salle ici");
		}
		else {
			this.salle = this.pnlChat.txtSeConnecterA.getText();
			this.connexion.envoyerCommande(new CmdSeConnecterAuChat(this.salle));
		}
	}
	
	public void envoyerMessageChat(String message) {
		if(!message.isEmpty()) {
			this.connexion.envoyerCommande(new CmdEnvoyerMessageChat(message, this.salle));
			this.pnlChat.txtMessage.setText("");
			this.pnlChat.txtMessage.setFocusable(true);
		}
	}




	public void ajouterMessageChat(String message) {
		this.pnlChat.txtChat.setText(this.pnlChat.txtChat.getText()+"\n"+message);
		this.pnlChat.txtChat.setCaretPosition(this.pnlChat.txtChat.getText().length());
		JScrollBar jsb = this.pnlChat.scrollPane.getVerticalScrollBar();
		jsb.setValue(jsb.getMaximum());

	}	
	
	public ControleurClientClient getCtrlClientClient() {
		return this.ctrlClientClient;
	}
	
	public ControleurRouletteClient getCtrlRouletteClient() {
		return this.ctrlRouletteClient;
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
