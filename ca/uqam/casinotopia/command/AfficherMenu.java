package ca.uqam.casinotopia.command;

import java.io.Serializable;

import ca.uqam.casinotopia.console.CInput;
import ca.uqam.casinotopia.controleur.Controleur;

public class AfficherMenu implements Command, Serializable {




	/**
	 * 
	 */
	private static final long serialVersionUID = 4336497254946189604L;

	@Override
	public void action(Controleur controleur) {
		System.out.println("Que veut tu faire\n 1. afficher les autres users");
		
		int choix = Integer.parseInt(CInput.readline());
		switch (choix) {
		case 1:
			controleur.getConnexion().envoyerCommand(new VoirListeUtilisateur());
			break;

		default:
			System.out.println("choix indisponible");
			break;
		}
		
		
	}


}
