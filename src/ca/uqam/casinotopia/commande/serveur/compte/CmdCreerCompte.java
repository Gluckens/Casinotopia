package ca.uqam.casinotopia.commande.serveur.compte;

import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;
import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;

public class CmdCreerCompte implements CommandeServeurControleurThread{

	private static final long serialVersionUID = -3215801310227922894L;
	
	private ModeleClientClient nouvClient;

	public CmdCreerCompte(ModeleClientClient modeleClientClient) {
		this.nouvClient = modeleClientClient;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionCreerCompte(nouvClient);
	}
}