package ca.uqam.casinotopia.commande.client;

import java.io.Serializable;

import ca.uqam.casinotopia.client.MainClient;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeClientControleurClient;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

public class InformationNomValide implements CommandeClientControleurClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8911438224598458768L;
	String message = "";
	
	public InformationNomValide(String message) {
		this.message = message;
	}
	
	@Override
	public void action(Controleur controleur) {
		
		((ControleurClientPrincipal)controleur).setMessageConnexionErreur(message);
	}

}
