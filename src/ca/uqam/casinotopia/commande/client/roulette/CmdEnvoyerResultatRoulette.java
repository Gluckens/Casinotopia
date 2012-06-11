package ca.uqam.casinotopia.commande.client.roulette;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.commande.CommandeClientControleurRoulette;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;

public class CmdEnvoyerResultatRoulette implements CommandeClientControleurRoulette {

	private static final long serialVersionUID = 3520768426950865395L;

	private Case resultat;
	private int gain;

	public CmdEnvoyerResultatRoulette(Case resultat) {
		this(resultat, 0);
	}

	public CmdEnvoyerResultatRoulette(Case resultat, int gain) {
		this.resultat = resultat;
		this.gain = gain;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurRouletteClient) controleur).actionUpdateResultat(this.resultat, this.gain);
	}
}
