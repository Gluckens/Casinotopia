package ca.uqam.casinotopia.model.client;

import java.io.IOException;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeClient;
import ca.uqam.casinotopia.commande.CommandeClientControleurClient;
import ca.uqam.casinotopia.commande.CommandeClientControleurRoulette;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

public class ClientThread implements Runnable {

	private ControleurClientPrincipal controleur;
	
	public ClientThread(ControleurClientPrincipal controleur) {
		this.controleur = controleur;
	}
	
	
	@Override
	public void run() {

    	System.out.println("enReceptionDeCommande");
        while(this.controleur.getConnexion().isConnected()){
            Commande cmd = null;
            try {
            	System.out.println("ATTENTE DE COMMANDE DU SERVEUR");
				cmd = (Commande) this.controleur.getConnexion().getObjectInputStream().readObject();
				System.out.println("COMMANDE SERVEUR OBTENUE");
				if(cmd != null){
	            	if(cmd instanceof CommandeClient) {
		            	if(cmd instanceof CommandeClientControleurClient) {
		            		cmd.action(this.controleur.getCtrlClientClient());
		            	}
		            	else if(cmd instanceof CommandeClientControleurRoulette) {
		            		cmd.action(this.controleur.getCtrlRouletteClient());
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
            	System.err.println("le serveur ne répond plus");
            	this.controleur.getConnexion().close();
			}
        }

	}

}
