package ca.uqam.casinotopia.controleur.client;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;

import ca.uqam.casinotopia.commande.CmdMiserRoulette;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.serveur.*;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.ControleurClientClient;
import ca.uqam.casinotopia.controleur.ControleurRouletteClient;
import ca.uqam.casinotopia.model.client.ClientThread;
import ca.uqam.casinotopia.model.client.ModelClientPrincipal;
import ca.uqam.casinotopia.vue.ConnexionFrame;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.PanelChat;
import ca.uqam.casinotopia.vue.VueRoulette;
import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.commande.serveur.AuthentifierClient;

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
	//TODO a mettre dans un model
	public String salle;
	


	/**
	 * model
	 */
	ModelClientPrincipal model;
	
	
	
	boolean enReceptionDeCommande = false;
	
	
	public ControleurClientPrincipal() {
		
		this.initModel();
		
		this.afficherInterface();
		
	}
	
	
	private void initControleur() {
		this.ctrlClientClient = new ControleurClientClient(this.getConnexion());
		this.ctrlRouletteClient = new ControleurRouletteClient(this.getConnexion());
	}
	
	
	private void initModel() {
		model = new ModelClientPrincipal();
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
			while(getConnexion().isConnected() == false && i < model.listeServeur.length){
				setConnexion(new Connexion(model.listeServeur[i], 7777));
				i++;
			}
		}
		
		if(getConnexion().isConnected()) {
			setMessageConnexion("connecté!");
			
			this.initControleur();

			Commande cmd = new AuthentifierClient(vueConnexionFrame.getTxtNomUtilisateur().getText(),vueConnexionFrame.getTxtMotDePasse().getPassword());
			this.getConnexion().envoyerCommande(cmd);
			
			
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

	public void setMessageConnexionErreur(String message) {
		this.vueConnexionFrame.setMessageErreur(message);
	}
	
	public void setMessageConnexion(String message){
		this.vueConnexionFrame.setMessage(message);
	}
	
	
	public void afficherFrameApplication() {
		this.frameApplication = new FrameApplication();
		pnlChat = new PanelChat(this);
		this.frameApplication.addOrReplace("chat", pnlChat);
		this.frameApplication.setVisible(true);
	}
	
	public void afficherFrameApplicationRoulette() {
		this.frameApplication = new FrameApplication();
		VueRoulette vueRoulette = new VueRoulette();
		//this.frameApplication.addOrReplace("VueRoulette", vueRoulette);
		this.frameApplication.changeContentPane(vueRoulette);
		EventQueue.invokeLater(this.frameApplication);
		
		this.ctrlRouletteClient.ajouterVue(vueRoulette);
	}
	
	public PanelChat getPnlChat() {
		return pnlChat;
	}


	public void setPnlChat(PanelChat pnlChat) {
		this.pnlChat = pnlChat;
	}


	public void setChatList(List<String> listeUtilisateur, List<String> listeMessages, String salle){
		setChatUtilisateur(listeUtilisateur);
		
		setChatMessages(listeMessages);
		pnlChat.lblTitre.setText(salle);
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
		pnlChat.txtChat.setText(messages);

		JScrollBar jsb = this.pnlChat.scrollPane.getVerticalScrollBar();
		jsb.setValue(jsb.getMaximum());
	}

		

	public void seConnecterAuChat() {
		if(this.pnlChat.txtSeConnecterA.getText().isEmpty()){
			this.pnlChat.txtSeConnecterA.setText("entrez un nom de salle ici");
		}else{
			this.salle = this.pnlChat.txtSeConnecterA.getText();
			connexion.envoyerCommande(new SeConnecterAuChat(this.salle));
		}
	}
	
	public void envoyerMessageChat(String message) {
		if(!message.isEmpty()) {
			connexion.envoyerCommande(new EnvoyerMessageChat(message, this.salle));
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
	
	
	public void envoyerCommandeTest1() {
		Map<Integer, Map<Case, Integer>> mises = new HashMap<Integer, Map<Case, Integer>>();
		
		int joueurId = 4;
		
		Map<Case, Integer> misesCases = new HashMap<Case, Integer>();
		
		misesCases.put(new Case(1, "noire", false), 5);
		misesCases.put(new Case(2, "rouge", true), 2);
		misesCases.put(new Case(3, "rouge", false), 8);
		misesCases.put(new Case(4, "noire", true), 8);
		misesCases.put(new Case(5, "noire", false), 1);
		misesCases.put(new Case(6, "rouge", true), 3);
		
		mises.put(joueurId, misesCases);
		
		this.getConnexion().envoyerCommande(new CmdMiserRoulette(mises));
	}
	
	public void envoyerCommandeTest2() {
		
		
		Map<Integer, Map<Case, Integer>> mises = new HashMap<Integer, Map<Case, Integer>>();
		
		int joueurId2 = 9;
		
		Map<Case, Integer> misesCases = new HashMap<Case, Integer>();
		
		misesCases.put(new Case(1, "noire", false), 2);
		misesCases.put(new Case(2, "rouge", true), 7);
		misesCases.put(new Case(5, "noire", false), 6);
		
		mises.put(joueurId2, misesCases);
		
		this.getConnexion().envoyerCommande(new CmdMiserRoulette(mises));
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
