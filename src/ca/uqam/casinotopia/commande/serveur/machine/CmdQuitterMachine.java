package ca.uqam.casinotopia.commande.serveur.machine;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class CmdQuitterMachine implements CommandeServeurControleurThread {
	
	private static final long serialVersionUID = -6433520199884086996L;
	
	private int idJoueur;
	
	public CmdQuitterMachine(int idJoueur) {
		this.idJoueur = idJoueur;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionQuitterMachine(this.idJoueur);
	}

}
