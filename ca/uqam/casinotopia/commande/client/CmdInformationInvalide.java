package ca.uqam.casinotopia.commande.client;

import ca.uqam.casinotopia.commande.CommandeClientControleurClient;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

//TODO Pas le bon controleur
public class CmdInformationInvalide implements CommandeClientControleurClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8911438224598458768L;
	
	String message = "";
	
	public CmdInformationInvalide(String message) {
		this.message = message;
	}
	
	@Override
	public void action(Controleur controleur) {
		
		((ControleurClientPrincipal)controleur).setMessageConnexionErreur(message);
	}
}
