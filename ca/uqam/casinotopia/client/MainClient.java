package ca.uqam.casinotopia.client;

import java.io.IOException;

import ca.uqam.casinotopia.command.Command;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.vue.ConnexionFrame;

public class MainClient extends Controleur {

	//private Connexion connexion;

	ConnexionFrame connexionFrame;
	public static void main(String[] args) {
		new MainClient();
	}
	
	public MainClient() {
		
		System.out.println("recherche de serveur...");
        
		String[] serveurs = {"localhost","oli.dnsd.me","dan.dnsd.me"};
		int i = 0;
		while(getConnexion().isConnected() == false && i < 3){
			setConnexion(new Connexion(serveurs[i], 7777));
			i++;
		}
    
        while(getConnexion().isConnected()){
            Command cmd = null;
            try {
				cmd = (Command) getConnexion().getObjectInputStream().readObject();
	            if(cmd != null){
					cmd.action(this);
	            }else{
	            	System.err.println("la commande envoyé n'est pas valide");
	            }
	       
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
            	System.err.println("le serveur ne répond plus");
            	getConnexion().close();
			}
        }
        
        System.out.println("fermeture du programme...");
	}

	
	public ConnexionFrame getConnexionFrame() {
		return connexionFrame;
	}

	public void setConnexionFrame(ConnexionFrame connexionFrame) {
		this.connexionFrame = connexionFrame;
	}


}
