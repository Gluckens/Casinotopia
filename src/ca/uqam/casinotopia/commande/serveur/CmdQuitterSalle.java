package ca.uqam.casinotopia.commande.serveur;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class CmdQuitterSalle implements CommandeServeurControleurThread {

	private static final long serialVersionUID = -1064729897920097958L;

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionQuitterSalle();
	}
}
