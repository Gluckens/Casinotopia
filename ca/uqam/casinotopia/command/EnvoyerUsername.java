package ca.uqam.casinotopia.command;

import java.io.Serializable;

import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.serveur.ServerThread;

public class EnvoyerUsername implements Serializable, Command {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3765448763609654187L;
	private String utilisateur;
	
	public EnvoyerUsername(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public void action(Controleur controleur) {
		System.out.println("le client a envoyer le username "+utilisateur+"!");
		((ServerThread)controleur).getModel().getUtilisateur().setNomUtilisateur(utilisateur);

		controleur.getConnexion().envoyerCommand(new AfficherMenu());

	}

	
	public String getUtilisateur() {
		return utilisateur;
	}

}
