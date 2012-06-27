package ca.uqam.casinotopia.commande.serveur.machine;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class CmdJouerMachine implements CommandeServeurControleurThread {
	
	private static final long serialVersionUID = 7918681776925268223L;
	
	private int idJeu;
	
	public CmdJouerMachine(int idJeu) {
		this.idJeu = idJeu;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionJouerMachine(this.idJeu);
	}
}