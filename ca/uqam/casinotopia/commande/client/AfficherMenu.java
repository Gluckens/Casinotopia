package ca.uqam.casinotopia.commande.client;

import java.io.Serializable;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.serveur.VoirListeUtilisateur;
import ca.uqam.casinotopia.console.CInput;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;

public class AfficherMenu implements Commande, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7886452110163001763L;

	@Override
	public void action(Controleur controleur) {
		System.out.println("Que veut tu faire\n 1. afficher les autres users");
		int choix = -1;
		while(choix != 0){
			choix = Integer.parseInt(CInput.readline());
			switch (choix) {
			case 1:
				((ControleurClientPrincipal)controleur).getConnexion().envoyerCommand(new VoirListeUtilisateur());
				break;
	
			default:
				System.out.println("choix indisponible");
				break;
			}
		}
		
	}


}
