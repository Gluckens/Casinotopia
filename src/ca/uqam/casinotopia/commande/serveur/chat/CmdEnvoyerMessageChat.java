package ca.uqam.casinotopia.commande.serveur.chat;

import ca.uqam.casinotopia.commande.CommandeServeurControleurChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurChatServeur;

public class CmdEnvoyerMessageChat implements CommandeServeurControleurChat {
	
	private static final long serialVersionUID = -8919163426514030923L;

	/**
	 * le message à envoyer au serveur
	 */
	private String message;
	
	/**
	 * la salle de chat dans laquelle envoyer le message
	 */
	private String salle;

	public CmdEnvoyerMessageChat(String message, String salle) {
		this.message = message;
		this.salle = salle;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurChatServeur) controleur).actionEnvoyerMessage(this.message, this.salle);
	}
}