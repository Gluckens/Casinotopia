package ca.uqam.casinotopia.objet;

import java.util.ArrayList;
import java.util.List;

import ca.uqam.casinotopia.commande.client.chat.CmdAjouterMessageChat;
import ca.uqam.casinotopia.commande.client.chat.CmdEnvoyerInformationChat;
import ca.uqam.casinotopia.commande.client.chat.CmdMettreAJourUtilisateurChat;
import ca.uqam.casinotopia.connexion.Connectable;

public class Clavardage implements Connectable {

	private static final long serialVersionUID = -2661913218185990338L;

	/**
	 * nombre maximum de message sauvegard� dans la m�moire
	 */
	private static int MAXMESSAGE = 10;

	/**
	 * le titre du chat
	 */
	private String nom;

	/**
	 * la liste des message envoy� au chat
	 */
	private List<String> messages = new ArrayList<String>();

	/**
	 * liste des participant au chat
	 */
	private List<Utilisateur> participants = new ArrayList<Utilisateur>();

	/**
	 * constructeur
	 * @param nom titre du chat
	 */
	public Clavardage(String nom) {
		this.nom = nom;
		this.messages.add("Serveur: Bonjour � toi!");
	}

	/**
	 * r�cup�re les dernier messages
	 * @return liste des messages
	 */
	public List<String> getMessage() {
		return this.messages;
	}

	/**
	 * ajoute un message a la liste des messages
	 * enl�ve le message le plus ancien si on d�passe le maximum de message
	 * indique a tous les participant qu'un nouveau message s'est ajout�
	 * @param message le message � ajouter
	 */
	public void addMessage(String message) {
		while (this.messages.size() >= MAXMESSAGE) {
			this.messages.remove(0);
		}
		if (!message.isEmpty() && message != null) {
			this.messages.add(message);
		}

		for (int i = 0; i < this.participants.size(); i++) {
			this.participants.get(i).getConnexion().envoyerCommande(new CmdAjouterMessageChat(message));
		}
	}


	@Override
	public void connecter(Utilisateur utilisateur) {
		if (!this.participants.contains(utilisateur)) {
			// d�connect� l'utilisateur des autres clavardages
			for (int i = 0; i < utilisateur.getConnectables().size(); i++) {
				if (utilisateur.getConnectables().get(i) instanceof Clavardage) {
					utilisateur.getConnectables().get(i).deconnecter(utilisateur);
				}
			}

			// ajouter l'utilisateur au clavardage et le clavardage a
			// l'utilisateur
			this.participants.add(utilisateur);
			utilisateur.getConnectables().add(this);

			// initialis� le chat cot� client
			CmdEnvoyerInformationChat cmd = new CmdEnvoyerInformationChat(this.getParticipantsToString(), this.getMessage(), this.nom);
			utilisateur.getConnexion().envoyerCommande(cmd);

			// mettre a jour les autres utilisateurs du chat
			for (int i = 0; i < this.participants.size(); i++) {
				if (!this.participants.get(i).equals(utilisateur)) {
					this.participants.get(i).getConnexion().envoyerCommande(new CmdMettreAJourUtilisateurChat(this.getParticipantsToString()));
				}
			}

			this.addMessage("l'utilisateur " + utilisateur.getNomUtilisateur() + " s'est connect�");
		}

	}

	@Override
	public void deconnecter(Utilisateur utilisateur) {
		if (this.participants.contains(utilisateur)) {
			this.participants.remove(utilisateur);
			for (int i = 0; i < this.participants.size(); i++) {
				if (!this.participants.get(i).equals(utilisateur)) {
					//indique au autre participant que l'utilisateur a �t� d�connect�
					this.participants.get(i).getConnexion().envoyerCommande(new CmdMettreAJourUtilisateurChat(this.getParticipantsToString()));
				}
			}
			//ajout une message au chat
			this.addMessage("l'utilisateur " + utilisateur.getNomUtilisateur() + " s'est d�connect�");
		}
	}

	/**
	 * retourne le nom des participants sous une arrayliste
	 * @return liste des participant
	 */
	public List<String> getParticipantsToString() {
		ArrayList<String> liste = new ArrayList<String>();

		for (int i = 0; i < this.participants.size(); i++) {
			liste.add(this.participants.get(i).getNomUtilisateur());
		}
		return liste;
	}
}