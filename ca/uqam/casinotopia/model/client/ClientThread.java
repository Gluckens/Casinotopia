package ca.uqam.casinotopia.model.client;

import java.io.IOException;

import ca.uqam.casinotopia.commande.Commande;
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
				cmd = (Commande) this.controleur.getConnexion().getObjectInputStream().readObject();
	            if(cmd != null){
					cmd.action(this.controleur);
	            }else{
	            	System.err.println("la commande envoyé n'est pas valide");
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
