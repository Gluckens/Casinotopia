package ca.uqam.casinotopia.commande.serveur;

import java.util.ArrayList;

import ca.uqam.casinotopia.Clavardage;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeServeurControleurPrincipal;
import ca.uqam.casinotopia.commande.client.EnvoyerInformationChat;
import ca.uqam.casinotopia.commande.client.MettreAJourUtilisateurChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurPrincipal;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class SeConnecterAuChat implements CommandeServeurControleurPrincipal {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2161362831188083377L;
	private String salle;
	
	public SeConnecterAuChat(String salle) {
		this.salle = salle;
	}
	
	@Override
	public void action(Controleur controleur) {

		Clavardage chat = ControleurServeurPrincipal.model.getChat(salle);
		chat.connect(((ControleurServeurThread)controleur).getModel().getUtilisateur());
		
	}

}
