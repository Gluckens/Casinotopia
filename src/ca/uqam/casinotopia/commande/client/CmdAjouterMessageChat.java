package ca.uqam.casinotopia.commande.client;

import ca.uqam.casinotopia.commande.CommandeClientControleurChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurChatClient;

//TODO Pas le bon controleur
public class CmdAjouterMessageChat implements CommandeClientControleurChat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2404901490335214036L;

	private String message;

	public CmdAjouterMessageChat(String message) {
		this.message = message;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurChatClient) controleur).actionAjouterMessageChat(this.message);
	}
}