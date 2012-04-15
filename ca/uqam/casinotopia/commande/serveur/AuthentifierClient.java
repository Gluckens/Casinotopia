package ca.uqam.casinotopia.commande.serveur;

import java.io.Serializable;
import java.util.Arrays;

import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.commande.client.AfficherPagePrincipal;
import ca.uqam.casinotopia.commande.client.InformationNomValide;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;
import ca.uqam.casinotopia.commande.CommandeServeur;

public class AuthentifierClient implements Serializable, CommandeServeur {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4957505453240402020L;
	
	private Utilisateur utilisateur;
	
	public AuthentifierClient(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public AuthentifierClient(String nomUtilisateur, char[] motDePasse) {
		this.utilisateur = new Utilisateur(nomUtilisateur, motDePasse);
	}
	
	

	@Override
	public void action(Controleur controleur) {
		int no = ((ControleurServeurThread)controleur).getModel().number;
		System.out.println("le client "+no+" a envoyer le username "+utilisateur.getNomUtilisateur()+"!");
		
		if(Arrays.equals(utilisateur.getMotDePasse(), utilisateur.getNomUtilisateur().toCharArray())){
			((ControleurServeurThread)controleur).getModel().setUtilisateur(utilisateur);
			((ControleurServeurThread)controleur).getConnexion().envoyerCommand(new AfficherPagePrincipal());
		}else{
			((ControleurServeurThread)controleur).getConnexion().envoyerCommand(new InformationNomValide("les données sont incorrecte"));
		}
		 

	}

	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


}
