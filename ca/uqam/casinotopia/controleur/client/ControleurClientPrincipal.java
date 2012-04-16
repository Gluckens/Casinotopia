package ca.uqam.casinotopia.controleur.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.serveur.*;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.model.client.ClientThread;
import ca.uqam.casinotopia.model.client.ModelClientPrincipal;
import ca.uqam.casinotopia.vue.ConnexionFrame;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.PanelChat;
import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.serveur.AuthentifierClient;
import ca.uqam.casinotopia.controleur.ControleurClient;

public class ControleurClientPrincipal extends ControleurClient{


	/**
	 * vues
	 */
	ConnexionFrame vueConnexionFrame;
	
	FrameApplication vueFrameApplication;
	PanelChat pnlChat;
	


	/**
	 * model
	 */
	ModelClientPrincipal model;
	
	
	
	boolean enReceptionDeCommande = false;
	
	
	public ControleurClientPrincipal() {
		super(null);
		
		initModel();
		
		afficherInterface();
		
	}


	
	
	private void initModel() {
		model = new ModelClientPrincipal();
		
	}
	
	/**
	 * Afficher l'interface de connexion au serveur
	 */
	private void afficherInterface(){
		vueConnexionFrame = new ConnexionFrame(this);
		vueConnexionFrame.setVisible(true);
		
	}
	
	public void connexionAuServeur(){

        
		if(!getConnexion().isConnected()){
			System.out.println("recherche de serveur...");
			setMessageConnexion("recherche de serveur...");
			int i = 0;
			while(getConnexion().isConnected() == false && i < model.listeServeur.length){
				setConnexion(new Connexion(model.listeServeur[i], 7777));
				i++;
			}
		}
		if(getConnexion().isConnected()){
			setMessageConnexion("connecté!");

			Utilisateur utilisateur = new Utilisateur(vueConnexionFrame.getTxtNomUtilisateur().getText(),vueConnexionFrame.getTxtMotDePasse().getPassword());
			Commande cmd = new AuthentifierClient(utilisateur);
			this.getConnexion().envoyerCommand(cmd);
			
			receptionCommandes();
			
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

	public void setMessageConnexionErreur(String message){
		this.vueConnexionFrame.setMessageErreur(message);
	}
	public void setMessageConnexion(String message){
		this.vueConnexionFrame.setMessage(message);
	}
	
	
	public void afficherFrameApplication(){
		this.vueFrameApplication = new FrameApplication();
		pnlChat = new PanelChat(this);
		this.vueFrameApplication.addOrReplace(pnlChat, "chat");
		this.vueFrameApplication.setVisible(true);
	}
	
	public PanelChat getPnlChat() {
		return pnlChat;
	}


	public void setPnlChat(PanelChat pnlChat) {
		this.pnlChat = pnlChat;
	}


	public void setPnlChatList(List<String> listeUtilisateur, List<String> listeMessages){
		DefaultListModel model = (DefaultListModel) pnlChat.lstConnecte.getModel();
		model.clear();
		for (int i = 0; i < listeUtilisateur.size(); i++) {
			model.add(i, listeUtilisateur.get(i));
		}
		
		String messages = "";
		for (int i = 0; i < listeMessages.size(); i++) {
			if(!listeMessages.get(i).isEmpty()){
				messages += listeMessages.get(i)+"\n";
			}
		}
		pnlChat.txtChat.setText(messages);

		JScrollBar jsb = this.pnlChat.scrollPane.getVerticalScrollBar() ;
		jsb.setValue(jsb.getMaximum());
	}




	public void seConnecterAuChat() {
		connexion.envoyerCommand(new SeConnecterAuChat());
	}
	
	public void envoyerMessageChat(String message){
		if(!message.isEmpty()){
			connexion.envoyerCommand(new EnvoyerMessageChat(message));
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
