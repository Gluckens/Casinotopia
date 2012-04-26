package ca.uqam.casinotopia.commande.serveur;

import java.util.Arrays;

import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.commande.client.CmdAfficherMenuPrincipal;
import ca.uqam.casinotopia.commande.client.CmdInformationInvalide;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;
import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;

public class CmdAuthentifierClient implements CommandeServeurControleurThread {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4957505453240402020L;

	private String nomUtilisateur;
	private char[] motDePasse;

	public CmdAuthentifierClient(String nomUtilisateur, char[] motDePasse) {
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasse = motDePasse;
	}

	@Override
	public void action(Controleur controleur) {
		ControleurServeurThread ctrl = (ControleurServeurThread) controleur;

		int no = ctrl.getModele().number;
		System.out.println("le client " + no + " a envoyer le username " + this.nomUtilisateur + "!");

		if (Arrays.equals(this.motDePasse, this.nomUtilisateur.toCharArray())) {
			ctrl.getModele().setUtilisateur(new Utilisateur(this.nomUtilisateur, ctrl.getConnexion()));
			ctrl.getConnexion().envoyerCommande(new CmdAfficherMenuPrincipal());
			// ctrl.getConnexion().envoyerCommande(new
			// CmdAfficherPagePrincipal());
		}
		else {
			ctrl.getConnexion().envoyerCommande(new CmdInformationInvalide("les données sont incorrectes"));
		}
	}
}
