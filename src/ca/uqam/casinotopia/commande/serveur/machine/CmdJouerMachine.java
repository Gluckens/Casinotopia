package ca.uqam.casinotopia.commande.serveur.machine;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class CmdJouerMachine implements CommandeServeurControleurThread {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5503591568620208794L;

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).lancerPartieMachine();

	}

}
