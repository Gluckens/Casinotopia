package ca.uqam.casinotopia.commande.client;

import java.util.List;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeClientControleurChat;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

public class MettreAJourUtilisateurChat implements CommandeClientControleurChat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7566185973021199708L;
	private List<String> listeUtilisateur;
	
	public MettreAJourUtilisateurChat(List<String> listeUtilisateur) {
		this.listeUtilisateur = listeUtilisateur;
	}
	
	@Override
	public void action(Controleur controleur) {
		((ControleurClientPrincipal)controleur).setChatUtilisateur(listeUtilisateur);

	}

}
