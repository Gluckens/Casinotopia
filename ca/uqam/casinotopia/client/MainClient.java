package ca.uqam.casinotopia.client;

import java.io.IOException;

import ca.uqam.casinotopia.command.Command;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.Controleur;

public class MainClient extends Controleur {

	//private Connexion connexion;

	
	public static void main(String[] args) {
		new MainClient();
	}
	
	public MainClient() {
		
		System.out.println("connection au serveur...");
        
		setConnexion(new Connexion("oli.dnsd.me", 7777));
		
    
        while(getConnexion().isConnected()){
            Command cmd = null;
            try {
				cmd = (Command) getConnexion().getObjectInputStream().readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if(cmd != null){
				cmd.action(this);
            }else{
            	System.err.println("la commande envoyé n'est pas valide");
            }
        }
        
        System.out.println("fermeture...");
	}

}
