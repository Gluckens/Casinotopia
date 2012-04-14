package ca.uqam.casinotopia.commande.serveur;

import java.io.Serializable;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.client.AfficherMenu;
import ca.uqam.casinotopia.commande.client.EnvoyerMessage;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.serveur.MainServeur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;

public class VoirListeUtilisateur implements Commande, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 118582660292767556L;

	@Override
	public void action(Controleur controleur) {
		String liste = "";
		for (int i = 0; i < MainServeur.NUMCONNEXION; i++) {
			if(MainServeur.thread[i] != null && 
					MainServeur.thread[i].isAlive() && 
					MainServeur.serverThread[i].getModel().getUtilisateur().getNomUtilisateur() != null){
				liste += " "
					+i
					+": "
					+MainServeur.serverThread[i].getModel().getUtilisateur().getNomUtilisateur()
					+"\n";
			}
		}

		((ControleurServeurThread)controleur).getConnexion().envoyerCommand(new EnvoyerMessage("Voici la liste des usagers connecté : \n"+liste));

		((ControleurServeurThread)controleur).getConnexion().envoyerCommand(new AfficherMenu());
		
		
	}
}
