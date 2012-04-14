package ca.uqam.casinotopia.controleur.client;

import java.io.IOException;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.serveur.*;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.model.client.ClientThread;
import ca.uqam.casinotopia.model.client.ModelClientPrincipal;
import ca.uqam.casinotopia.vue.ConnexionFrame;
import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.serveur.AuthentifierClient;
import ca.uqam.casinotopia.controleur.ControleurClient;

public class ControleurClientPrincipal extends ControleurClient{


	/**
	 * vue
	 */
	ConnexionFrame vueConnexionFrame;

	/**
	 * model
	 */
	ModelClientPrincipal model;
	
	
	
	boolean enReceptionDeCommande = false;
	
	
	public ControleurClientPrincipal() {
		
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
	
}
