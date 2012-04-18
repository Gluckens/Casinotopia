package ca.uqam.casinotopia.commande.serveur;

import java.io.Serializable;
import java.util.Arrays;

import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.commande.client.AfficherPagePrincipal;
import ca.uqam.casinotopia.commande.client.InformationNomValide;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;
import ca.uqam.casinotopia.commande.CommandeServeur;
import ca.uqam.casinotopia.commande.CommandeServeurControleurPrincipal;

public class AuthentifierClient implements CommandeServeurControleurPrincipal {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4957505453240402020L;
	
	private String nomUtilisateur;
	private char[] motDePasse;
	
	
	public AuthentifierClient(String nomUtilisateur, char[] motDePasse) {
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasse = motDePasse;
	}
	
	

	@Override
	public void action(Controleur controleur) {
		int no = ((ControleurServeurThread)controleur).getModel().number;
		System.out.println("le client "+no+" a envoyer le username "+this.nomUtilisateur+"!");
		
		if(Arrays.equals(this.motDePasse, this.nomUtilisateur.toCharArray())){
			((ControleurServeurThread)controleur).getModel().setUtilisateur(new Utilisateur(this.nomUtilisateur,((ControleurServeurThread)controleur).getConnexion()));
			((ControleurServeurThread)controleur).getConnexion().envoyerCommande(new AfficherPagePrincipal());
		}else{
			((ControleurServeurThread)controleur).getConnexion().envoyerCommande(new InformationNomValide("les données sont incorrecte"));
		}
		 

	}


}
