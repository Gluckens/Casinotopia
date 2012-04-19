package ca.uqam.casinotopia.commande.serveur;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeServeurControleurPrincipal;
import ca.uqam.casinotopia.commande.client.AjouterMessageChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurPrincipal;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class EnvoyerMessageChat implements CommandeServeurControleurPrincipal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2768135797514657051L;
	private String message; 
	private String salle;
	
	public EnvoyerMessageChat(String message, String salle) {
		this.message = message;
		this.salle = salle;
	}

	@Override
	public void action(Controleur controleur) {
		message = ((ControleurServeurThread)controleur).getModel().getUtilisateur().getNomUtilisateur()+": "+message;
		ControleurServeurPrincipal.model.getChat(this.salle).addMessage(message);
	}

}
