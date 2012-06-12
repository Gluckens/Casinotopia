package ca.uqam.casinotopia.commande.serveur.compte;

import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurClientServeur;
import ca.uqam.casinotopia.commande.CommandeServeurControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;

public class CmdModifierCompte implements CommandeServeurControleurClient {

	private static final long serialVersionUID = 4957505453240402020L;
	
	private ModeleClientClient nouvClient;

	public CmdModifierCompte(ModeleClientClient modeleClientClient) {
		this.nouvClient = modeleClientClient;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurClientServeur) controleur).actionModifierCompte(nouvClient);
	}
}