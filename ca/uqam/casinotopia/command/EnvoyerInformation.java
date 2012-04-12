package ca.uqam.casinotopia.command;

import java.io.Serializable;
import java.util.Arrays;

import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.serveur.ServerThread;

public class EnvoyerInformation implements Serializable, Command {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5362523380508232907L;
	private Utilisateur utilisateur;
	
	public EnvoyerInformation(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public void action(Controleur controleur) {
		System.out.println("le client a envoyer le username "+utilisateur.getNomUtilisateur()+"!");
		
		if(Arrays.equals (utilisateur.getMotDePasse(), utilisateur.getNomUtilisateur().toCharArray())){
			
			((ServerThread)controleur).getModel().setUtilisateur(utilisateur);
			controleur.getConnexion().envoyerCommand(new AfficherMenu());
			
		}else{
			controleur.getConnexion().envoyerCommand(new InformationNomValide("les données sont incorrecte"));
		}
		 

	}

	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

}
