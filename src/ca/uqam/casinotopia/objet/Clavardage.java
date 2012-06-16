package ca.uqam.casinotopia.objet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.uqam.casinotopia.commande.client.chat.CmdAjouterMessageChat;
import ca.uqam.casinotopia.commande.client.chat.CmdEnvoyerInformationChat;
import ca.uqam.casinotopia.commande.client.chat.CmdMettreAJourUtilisateurChat;
import ca.uqam.casinotopia.connexion.Connectable;

public class Clavardage implements Connectable, Serializable {

	private static final long serialVersionUID = 1221820908161003468L;
	
	private static int MAXMESSAGE = 10;
	private String nom;
	private List<String> messages = new ArrayList<String>();
	private List<Utilisateur> participants = new ArrayList<Utilisateur>();

	public Clavardage(String nom) {
		this.nom = nom;
		this.messages.add("Serveur: Bonjour à toi!");

	}

	public List<String> getMessage() {
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
			//Déconnecter l'utilisateur des autres clavardages
			for(Connectable connectable : utilisateur.getConnectables()) {
				if(connectable instanceof Clavardage) {
					connectable.deconnecter(utilisateur);
				}
			}

			//Ajouter l'utilisateur au clavardage et le clavardage à l'utilisateur
			this.participants.add(utilisateur);
			utilisateur.ajouterConnectable(this);

			//Initialiser le chat coté client
			CmdEnvoyerInformationChat cmd = new CmdEnvoyerInformationChat(this.getParticipantsToString(), this.getMessage(), this.nom);
			utilisateur.getConnexion().envoyerCommande(cmd);

			//Mettre à jour les autres utilisateurs du chat
			for (int i = 0; i < this.participants.size(); i++) {
				if (!this.participants.get(i).equals(utilisateur)) {
					this.participants.get(i).getConnexion().envoyerCommande(new CmdMettreAJourUtilisateurChat(this.getParticipantsToString()));
				}
			}

			this.addMessage("l'utilisateur " + utilisateur.getNomUtilisateur() + " s'est connecté");
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
			this.addMessage("l'utilisateur " + utilisateur.getNomUtilisateur() + " s'est déconnecté");
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