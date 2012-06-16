package ca.uqam.casinotopia.controleur.serveur;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;

public class ControleurChatServeur extends ControleurServeur {

	public ControleurChatServeur(Connexion connexion, ControleurServeurThread ctrlThread) {
		super(connexion, ctrlThread);
	}

	public void actionQuitterChat(int idJoueur) {
		//this.modele.quitterPartie(idJoueur);
		//if(this.modele.isPartieVide()) {
		//	ControleurPrincipalServeur.getInstance().retirerPartie(this.modele);
		//}
	}

	public void actionEnvoyerMessage(String message, String salle) {
		message = this.ctrlThread.getModeleClient().getNomUtilisateur() + " : " + message;
		ControleurPrincipalServeur.getInstance().getModele().getChat(salle).addMessage(message);
	}
}