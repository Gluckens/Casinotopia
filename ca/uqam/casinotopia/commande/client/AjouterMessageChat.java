package ca.uqam.casinotopia.commande.client;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

public class AjouterMessageChat implements Commande {

	private String message;
	
	public AjouterMessageChat(String message) {
		this.message = message;
	}
	
	@Override
	public void action(Controleur controleur) {
		((ControleurClientPrincipal)controleur).ajouterMessageChat(message);

	}

}
