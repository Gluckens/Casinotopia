package ca.uqam.casinotopia.commande.client.chat;

import ca.uqam.casinotopia.commande.CommandeClientControleurChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurChatClient;

public class CmdAjouterMessageChat implements CommandeClientControleurChat {
	
	private static final long serialVersionUID = -2254681516450936470L;
	
	/**
	 * message à ajouter
	 */
	private String message;

	
	public CmdAjouterMessageChat(String message) {
		this.message = message;
	}

	
	@Override
	public void action(Controleur controleur) {
		((ControleurChatClient) controleur).actionAjouterMessageChat(this.message);
	}
}