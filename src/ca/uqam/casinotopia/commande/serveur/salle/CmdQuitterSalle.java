package ca.uqam.casinotopia.commande.serveur.salle;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class CmdQuitterSalle implements CommandeServeurControleurThread {

	private static final long serialVersionUID = 6178184152147628993L;

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionQuitterSalle();
	}
}