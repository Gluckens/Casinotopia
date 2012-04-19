package ca.uqam.casinotopia.commande.serveur;

import ca.uqam.casinotopia.Clavardage;
import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;
import ca.uqam.casinotopia.serveur.MainServeur;

public class CmdSeConnecterAuChat implements CommandeServeurControleurThread {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2161362831188083377L;
	private String salle;
	
	public CmdSeConnecterAuChat(String salle) {
		this.salle = salle;
	}
	
	@Override
	public void action(Controleur controleur) {

		Clavardage chat = MainServeur.modele.getChat(salle);
		chat.connect(((ControleurServeurThread)controleur).getModele().getUtilisateur());
		
	}

}
