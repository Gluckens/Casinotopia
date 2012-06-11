package ca.uqam.casinotopia;

import java.util.ArrayList;
import java.util.List;

import ca.uqam.casinotopia.commande.client.chat.CmdAjouterMessageChat;
import ca.uqam.casinotopia.commande.client.chat.CmdEnvoyerInformationChat;
import ca.uqam.casinotopia.commande.client.chat.CmdMettreAJourUtilisateurChat;
import ca.uqam.casinotopia.connexion.Connectable;

public class Clavardage implements Connectable {

	private static final long serialVersionUID = -2661913218185990338L;

	private static int MAXMESSAGE = 10;

	private String nom;

	private List<String> messages = new ArrayList<String>();

	private List<Utilisateur> participants = new ArrayList<Utilisateur>();

	public Clavardage(String nom) {
		this.nom = nom;
		this.messages.add("Serveur: Bonjour � toi!");

	}

	public List<String> getMessage() {
		// TODO Auto-generated method stub
		return this.messages;
	}

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
					this.participants.get(i).getConnexion().envoyerCommande(new CmdMettreAJourUtilisateurChat(this.getParticipantsToString()));
				}
			}
			this.addMessage("l'utilisateur " + utilisateur.getNomUtilisateur() + " s'est d�connect�");
		}
	}

	public List<String> getParticipantsToString() {
		ArrayList<String> liste = new ArrayList<String>();

		for (int i = 0; i < this.participants.size(); i++) {
			liste.add(this.participants.get(i).getNomUtilisateur());
		}
		return liste;
	}
}