package ca.uqam.casinotopia.client;

import java.io.IOException;

import ca.uqam.casinotopia.command.Command;
import ca.uqam.casinotopia.connexion.Connexion;

public class MainClient {
	
	private static Connexion connexion;

	
	public static void main(String[] args) {
		System.out.println("connection au serveur...");
        
		connexion = new Connexion("localhost", 7777);
		
	    System.out.println("Connecté");
    
        while(true){
            Command cmd = null;
            try {
				cmd = (Command) connexion.getObjectInputStream().readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if(cmd != null){
				cmd.action();
				cmd.repondre(connexion.getObjectOutputStream());
            }else{
            	System.err.println("la commande envoyé n'est pas valide");
            }
        }

	}

}
