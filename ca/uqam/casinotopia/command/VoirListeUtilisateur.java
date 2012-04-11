package ca.uqam.casinotopia.command;

import java.io.Serializable;

import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.serveur.MainServeur;

public class VoirListeUtilisateur implements Command, Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -2628883463099137399L;

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

		controleur.getConnexion().envoyerCommand(new EnvoyerMessage("Voici la liste des usagers connecté : \n"+liste));

		controleur.getConnexion().envoyerCommand(new AfficherMenu());
		
		
	}
}
