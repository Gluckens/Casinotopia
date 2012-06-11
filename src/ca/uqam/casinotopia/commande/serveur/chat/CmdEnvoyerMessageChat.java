package ca.uqam.casinotopia.commande.serveur.chat;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurPrincipalServeur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class CmdEnvoyerMessageChat implements CommandeServeurControleurThread {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2768135797514657051L;

	private String message;
	private String salle;

	public CmdEnvoyerMessageChat(String message, String salle) {
		this.message = message;
		this.salle = salle;
	}

	@Override
	public void action(Controleur controleur) {
		this.message = ((ControleurServeurThread) controleur).getModele().getNomUtilisateur() + ": " + this.message;
		ControleurPrincipalServeur.getInstance().getModele().getChat(this.salle).addMessage(this.message);
	}
}