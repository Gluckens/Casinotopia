package ca.uqam.casinotopia.objet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.uqam.casinotopia.commande.client.chat.CmdAjouterMessageChat;
import ca.uqam.casinotopia.commande.client.chat.CmdEnvoyerInformationChat;
import ca.uqam.casinotopia.commande.client.chat.CmdMettreAJourUtilisateurChat;
import ca.uqam.casinotopia.connexion.Connectable;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;

/**
 * Modèle de chat coté serveur
 */
public class Clavardage implements Connectable, Serializable {
	
	private static final long serialVersionUID = 1221820908161003468L;
	
	/**
	 * nombre maximum de messages sauvegardés dans la mémoire
	 */
	private static int MAXMESSAGE = 10;
	/**
	 * le titre du chat
	 */
	private String nom;
	/**
	 * la liste des messages envoyés au chat
	 */
	private List<String> messages = new ArrayList<String>();
	/**
	 * liste des participants au chat
	 */
	private List<Utilisateur> participants = new ArrayList<Utilisateur>();

	/**
	 * constructeur
	 * @param nom titre du chat
	 */
	public Clavardage(String nom) {
		this.nom = nom;
		this.messages.add("Serveur: Bonjour à toi!");
	}
	
	public ModeleChatClient creerModeleClient() {
		return new ModeleChatClient(
				this.nom,
				this.messages,
				this.getParticipantsToString()
		);
	}

	/**
	 * récupère les derniers messages
	 * @return liste des messages
	 */
	public List<String> getMessage() {
		return this.messages;
	}

	/**
	 * ajoute un message à la liste des messages
	 * enlève le message le plus ancien si on dépasse le maximum de messages
	 * indique à tous les participants qu'un nouveau message s'est ajouté
	 * @param message le message à ajouter
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
					//indique aux autres participants que l'utilisateur a été déconnecté
					this.participants.get(i).getConnexion().envoyerCommande(new CmdMettreAJourUtilisateurChat(this.getParticipantsToString()));
				}
			}
			//ajoute un message au chat
			this.addMessage("l'utilisateur " + utilisateur.getNomUtilisateur() + " s'est déconnecté");
		}
	}

	/**
	 * retourne le nom des participants sous une arrayliste
	 * @return liste des participants
	 */
	public List<String> getParticipantsToString() {
		ArrayList<String> liste = new ArrayList<String>();

		for (int i = 0; i < this.participants.size(); i++) {
			liste.add(this.participants.get(i).getNomUtilisateur());
		}
		return liste;
	}
}