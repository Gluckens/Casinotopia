package ca.uqam.casinotopia.commande.client.chat;

import java.util.ArrayList;
import java.util.List;
import ca.uqam.casinotopia.commande.CommandeClientControleurChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurChatClient;

public class CmdEnvoyerInformationChat implements CommandeClientControleurChat {


	private static final long serialVersionUID = 1076996766584946499L;
	
	/**
	 * liste des utilisateurs couramment connect�s
	 */
	private List<String> listeUtilisateurs = new ArrayList<String>();
	
	/**
	 * les derniers messages du chat
	 */
	private List<String> messages = new ArrayList<String>();
	
	/**
	 * nom/salle du chat
	 */
	private String nom;

	
	public CmdEnvoyerInformationChat(List<String> listeUtilisateurs, List<String> messages, String nom) {
		this.listeUtilisateurs = listeUtilisateurs;
		this.messages = messages;
		this.nom = nom;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurChatClient)controleur).initChat(this.listeUtilisateurs, this.messages, this.nom);
	}
}