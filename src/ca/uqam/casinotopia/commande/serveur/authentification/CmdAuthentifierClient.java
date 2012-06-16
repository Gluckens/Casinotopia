package ca.uqam.casinotopia.commande.serveur.authentification;

import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;
import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;

public class CmdAuthentifierClient implements CommandeServeurControleurThread {
	
	private static final long serialVersionUID = 5809372922945650142L;
	
	private String nomUtilisateur;
	private char[] motDePasse;

	public CmdAuthentifierClient(String nomUtilisateur, char[] motDePasse) {
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasse = motDePasse;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionAuthentifierClient(this.nomUtilisateur, this.motDePasse);
	}
}