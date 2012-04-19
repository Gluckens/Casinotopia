package ca.uqam.casinotopia.commande.client;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeClientControleurChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurChatClient;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

public class AjouterMessageChat implements CommandeClientControleurChat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2404901490335214036L;
	private String message;
	
	public AjouterMessageChat(String message) {
		this.message = message;
	}
	
	@Override
	public void action(Controleur controleur) {
		((ControleurChatClient)controleur).ajouterMessageChat(message);

	}

}
