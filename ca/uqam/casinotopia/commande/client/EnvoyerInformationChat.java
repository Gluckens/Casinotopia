package ca.uqam.casinotopia.commande.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeClientControleurChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurChatClient;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

public class EnvoyerInformationChat implements CommandeClientControleurChat {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1882263609311781756L;
	List<String> listeUtilisateurs = new ArrayList<String>();
	List<String> messages = new ArrayList<String>();
	String nom;
	
	public EnvoyerInformationChat(List<String> listeUtilisateurs, List<String> messages, String nom) {
		this.listeUtilisateurs = listeUtilisateurs;
		this.messages = messages;
		this.nom = nom;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurChatClient)controleur).setChatList(listeUtilisateurs,messages,nom);

	}

}
