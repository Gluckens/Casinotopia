package ca.uqam.casinotopia.controleur.serveur;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.objet.Clavardage;

public class ControleurChatServeur extends ControleurServeur {
	
	private Clavardage modele;

	public ControleurChatServeur(Connexion connexion, ControleurServeurThread ctrlThread, Clavardage modele) {
		super(connexion, ctrlThread);
		this.modele = modele;
	}

	/**
	 * Quitter le chat.
	 * Cette action est exécutée suite à la demande du serveur (commande)
	 */
	public void actionQuitterChat() {
		this.modele.deconnecter(this.getModeleClient());
	}

	/**
	 * Envoyer un message à un chat
	 * @param message le message à envoyer
	 * @param salle le chat auquel envoyé le message
	 */
	public void actionEnvoyerMessage(String message, String salle) {
		message = this.ctrlThread.getModeleClient().getNomUtilisateur() + " : " + message;
		ControleurPrincipalServeur.getInstance().getModele().getChat(salle).addMessage(message);
	}
	
	public Clavardage getModele() {
		return this.modele;
	}
}