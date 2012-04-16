package ca.uqam.casinotopia.commande.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

public class EnvoyerInformationChat implements Commande {


	/**
	 * 
	 */
	private static final long serialVersionUID = -814982804652861588L;
	List<String> listeUtilisateurs = new ArrayList<String>();
	List<String> messages = new ArrayList<String>();
	
	public EnvoyerInformationChat(List<String> listeUtilisateurs, List<String> messages) {
		this.listeUtilisateurs = listeUtilisateurs;
		this.messages = messages;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurClientPrincipal)controleur).setChatList(listeUtilisateurs,messages);

	}

}
