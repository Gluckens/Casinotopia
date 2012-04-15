package ca.uqam.casinotopia.commande.serveur;

import java.util.ArrayList;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.client.EnvoyerInformationChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;
import ca.uqam.casinotopia.serveur.MainServeur;

public class SeConnecterAuChat implements Commande {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7826595255979222031L;

	@Override
	public void action(Controleur controleur) {

		EnvoyerInformationChat cmd = new EnvoyerInformationChat(((ControleurServeurThread)controleur).getAllUtilisateurs(),MainServeur.model.getChat().getMessage());
		
		for (int i = 0; i < MainServeur.NUMCONNEXION; i++) {
			if(MainServeur.thread[i] != null && 
					MainServeur.thread[i].isAlive() && 
					MainServeur.serverThread[i].getModel().getUtilisateur().getNomUtilisateur() != null){
				MainServeur.serverThread[i].getConnexion().envoyerCommand(cmd);
			}
		}
		
	}

}
