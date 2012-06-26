package ca.uqam.casinotopia.commande.client.machine;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;
import ca.uqam.casinotopia.modele.client.ModelePartieMachineClient;

public class CmdAfficherJeuMachine implements CommandeClientControleurPrincipal {

	private static final long serialVersionUID = 8314399057813220106L;
	
	private ModelePartieMachineClient modele;
	
	public CmdAfficherJeuMachine(ModelePartieMachineClient modele) {
		this.modele = modele;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurPrincipalClient) controleur).actionAfficherJeuMachine(this.modele);
	}
}