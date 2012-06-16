package ca.uqam.casinotopia.commande.client.roulette;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;

public class CmdAfficherJeuRoulette implements CommandeClientControleurPrincipal {
	
	private static final long serialVersionUID = 4073024275483273131L;
	
	private ModelePartieRouletteClient modele;

	public CmdAfficherJeuRoulette(ModelePartieRouletteClient modele) {
		this.modele = modele;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurPrincipalClient) controleur).actionAfficherJeuRoulette(this.modele);
	}
}