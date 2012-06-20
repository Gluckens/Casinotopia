package ca.uqam.casinotopia.commande.serveur.chat;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class CmdQuitterChat implements CommandeServeurControleurThread {

	private static final long serialVersionUID = -3197842345426601222L;

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionQuitterChat();
	}
}