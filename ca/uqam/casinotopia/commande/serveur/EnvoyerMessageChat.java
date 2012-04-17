package ca.uqam.casinotopia.commande.serveur;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.client.AjouterMessageChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.serveur.MainServeur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class EnvoyerMessageChat implements Commande {

	/**
	 * 
	 */
	private static final long serialVersionUID = 472294044259912411L;
	private String message; 
	
	public EnvoyerMessageChat(String message) {
		this.message = message;
	}

	@Override
	public void action(Controleur controleur) {
		MainServeur.model.getChat().addMessage(message);
		((ControleurServeurThread)controleur).envoyerCommandeATous(new AjouterMessageChat(message));
	}

}
