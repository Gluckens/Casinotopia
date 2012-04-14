package ca.uqam.casinotopia.client;

import java.awt.EventQueue;
import java.io.IOException;

import ca.uqam.casinotopia.command.Command;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeClient;
import ca.uqam.casinotopia.commande.CommandeClientControleurClient;
import ca.uqam.casinotopia.commande.CommandeClientControleurRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.ControleurClientClient;
import ca.uqam.casinotopia.controleur.ControleurRouletteClient;
import ca.uqam.casinotopia.vue.ConnexionFrame;
import ca.uqam.casinotopia.vue.FrameApplication;

public class MainClient extends Controleur {

	//private Connexion connexion;
	
	private FrameApplication frameApplication; 

	ConnexionFrame connexionFrame;
	public static void main(String[] args) {
		new MainClient();
	}
	
	public MainClient() {
		
		System.out.println("recherche de serveur...");
        
		String[] serveurs = {"localhost","oli.dnsd.me","dan.dnsd.me"};
		int i = 0;
		while(this.getConnexion().isConnected() == false && i < 3){
			this.setConnexion(new Connexion(serveurs[i], 7777));
			i++;
		}
		
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
	       
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
            	System.err.println("Le serveur ne répond plus");
            	this.getConnexion().close();
			}
        }
		
		this.getConnexion().close();
        
        System.out.println("Fermeture du programme...");
	}

	
	public ConnexionFrame getConnexionFrame() {
		return this.connexionFrame;
	}

	public void setConnexionFrame(ConnexionFrame connexionFrame) {
		this.connexionFrame = connexionFrame;
	}
}
