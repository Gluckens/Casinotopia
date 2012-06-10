package ca.uqam.casinotopia.commande.serveur;

import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;
import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;

public class CmdModifierCompte implements CommandeServeurControleurThread {

	private static final long serialVersionUID = 4957505453240402020L;
	
	private ModeleClientClient nouvClient;

	public CmdModifierCompte(ModeleClientClient modeleClientClient) {
		this.nouvClient = modeleClientClient;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionModifierCompte(nouvClient);
	}
}