package ca.uqam.casinotopia.commande.client;

import java.util.List;
import ca.uqam.casinotopia.commande.CommandeClientControleurChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurChatClient;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

//TODO Pas le bon controleur
public class CmdMettreAJourUtilisateurChat implements CommandeClientControleurChat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7566185973021199708L;
	private List<String> listeUtilisateur;
	
	public CmdMettreAJourUtilisateurChat(List<String> listeUtilisateur) {
		this.listeUtilisateur = listeUtilisateur;
	}
	
	@Override
	public void action(Controleur controleur) {
		((ControleurChatClient)controleur).setChatUtilisateur(listeUtilisateur);

	}

}
