package ca.uqam.casinotopia.commande.client.chat;

import java.util.List;
import ca.uqam.casinotopia.commande.CommandeClientControleurChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurChatClient;

public class CmdMettreAJourUtilisateurChat implements CommandeClientControleurChat {

	private static final long serialVersionUID = 8303784817286488858L;
	
	/**
	 * liste des utilisateur courant du chat
	 */
	private List<String> listeUtilisateur;

	public CmdMettreAJourUtilisateurChat(List<String> listeUtilisateur) {
		this.listeUtilisateur = listeUtilisateur;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurChatClient) controleur).setChatUtilisateur(this.listeUtilisateur);
	}
}