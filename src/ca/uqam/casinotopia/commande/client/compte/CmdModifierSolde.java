package ca.uqam.casinotopia.commande.client.compte;

import ca.uqam.casinotopia.commande.CommandeClientControleurClient;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurClientClient;

public class CmdModifierSolde implements CommandeClientControleurClient {
	
	private static final long serialVersionUID = -7652296837007011914L;
	
	private int nouveauSolde;
	
	public CmdModifierSolde(int nouveauSolde) {
		this.nouveauSolde = nouveauSolde;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurClientClient) controleur).modifierSolde(this.nouveauSolde);
	}
}
